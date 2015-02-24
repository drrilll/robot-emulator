CON

  _clkmode = xtal1 + pll16x
  _xinfreq = 5_000_000
  leftServoStoppedValue = 744
  rightServoStoppedValue = 749
  headCenterPitch = 134
  headDownPitch = 115
  headCenterYaw = 142
  leftGripperStraight = 180
  rightGripperStraight = 123
  MaxLeftSpeed = 25
  MaxRightSpeed = 25
  LeftTurnLeft = -10
  LeftTurnRight = 10
  RightTurnLeft = 10
  RightTurnRight = -10
  NOTURN = 0
  LEFT = 1
  RIGHT = 2
  STOP = 3
  STOPTWO = 4
  SPINLEFT = 5
  SPINRIGHT = 6
  THREESIXTY = 7
  TURN = 8
  LIMIT = 3
  DEBUG = true
  

VAR
  
  byte turningToAvoidObstacle
  byte obstacleDetected
  long rand
  byte isStopped
  long currentLeftSpeed, currentRightSpeed
  byte turnCount
  long extraTurn
  byte collisionPriority
  byte navigatePriority
  byte currentPriority
  byte extraTurnOn
  byte slowCount
  byte getPose
  byte spin
  long temp
  byte dataIn[4]
  byte dataout[6]
  byte flagData[1]
  byte instruction
  
OBJ
  
  RBC: "RBC"
  Beeper: "Beeper"
  Control: "ServoControl"
  IRSensors: "IR8SensorArray"
  Encoders: "Encoders"
  F: "Float32Full"
  FS: "FloatString"
  Sonar:   "PingSensor"
  Dirrs: "DirrsSensor"

PUB main
'initialize variables here, so they are easy to find
'I use powers of 2 for priority. Not strictly necessary
'how I do it here, but I may change my approach in the future

  currentPriority := 0
  
  collisionPriority := 4
  navigatePriority := 2
 
  extraTurnOn := 1
  spin := 0

  
  'code start

  Beeper.Startup
  RBC.Init          'connect to pc and wait
 
  Control.Start(leftServoStoppedValue, rightServoStoppedValue,true, true, true, true)
  Control.SetHeadPitch(headCenterPitch)
  Control.SetHeadYaw(headCenterYaw)
  Control.SetLeftGripper(leftGripperStraight)
  Control.SetRightGripper(rightGripperStraight)

  F.Start

  Encoders.Start
  Encoders.ResetCounters
  
  Beeper.OK
  currentLeftSpeed := 0
  currentRightSpeed := 0
  extraTurn := 0
  
  
  RBC.DebugClear
  RBC.DebugStrCr(string("WTF?"))

  currentPriority := 0
  repeat
    
    if (collisionPriority => currentPriority)
      avoidCollisions
    else
      'trying to keep the timing intact if we need to skip this function
      'waitCnt(10_000_000 + cnt)
    if (navigatePriority => currentPriority)
      receiveDirections
    Control.setSpeeds (currentLeftSpeed, currentRightSpeed)
    

    'debug code
    
    RBC.DebugStrCr(string("Speeds"))
    RBC.DebugLongCr(currentLeftSpeed)
    RBC.DebugLongCr(currentRightSpeed)

PUB receiveDirections | degrees
  currentPriority := navigatePriority
  RBC.DebugStrCr(string("before RBC receive data"))
  RBC.ReceiveData(@dataIn)
  RBC.DebugStrCr(string("after receive data"))
  instruction := dataIn[1]
  RBC.DebugStrCr(string("data length"))
  RBC.DebugLongCr(dataIn[0])
  instruction := dataIn[1]
  RBC.DebugStrCr(string("instruction and angle: "))
  RBC.DebugLongCr(instruction)
  degrees := (dataIn[2] * 256) + dataIn[3]
  RBC.DebugLongCr(degrees)
  if (instruction == 255)
    return false
  case (instruction)
    NOTURN:
      MaxSpeed
    RIGHT:
      turnRight
    LEFT:
      turnLeft
    STOP:
      StopMoving
    STOPTWO:
      repeat
       Control.setSpeeds(0,0)
    SPINLEFT:
      PivotLeft(0)
      'Pivot(LEFT, degrees)
    SPINRIGHT:
      PivotRight(0)
      'Pivot(RIGHT, degrees)
    THREESIXTY:
      'for the other commands its not as important, because they are
      'redundant, but for this we tell the computer we got it in
      'case the transmission gets lost
      RBC.SendDataToPc(@flagData, 1, RBC#OUTPUT_TO_NONE)
      RBC.DebugStrCr(string("Spinning!!"))
      Pivot(RIGHT, 360)   
  
  return true 


PUB avoidCollisions
  IRSensors.capture 'Do this to get latest sensor readings
   'Check for front collision
  if (IRSensors.Detect(1) OR IRSensors.Detect(2) OR IRSensors.Detect(3))
    if (IRSensors.Detect(1))
      RBC.DebugStrCr(string("sensor1"))
    if (IRSensors.Detect(2))
      RBC.DebugStrCr(string("sensor2"))
    if (IRSensors.Detect(3))
      RBC.DebugStrCr(string("sensor3"))
    RBC.DebugStrCr(string("obstacledetected"))
    'take control of priority variable
    currentPriority := collisionPriority
    if (obstacleDetected == 0)
      obstacleDetected := 1
      turningToAvoidObstacle := STOP
      'set this for later
      RBC.DebugStrCr(string("setting extra turn"))
      extraTurn := random(15)+5  
    else
      'obstacle has been detected and is still detecting
      if (turningToAvoidObstacle == STOP)
        if (Stopped)
          turningToAvoidObstacle := NOTURN
      elseif (turningToAvoidObstacle == NOTURN)
        'choose a direction
        'this is the case if both R and L sensors detect,
        'or both don't detect (which means only the middle detects)
        'so we choose a random direction
        if ((IRSensors.Detect(1) AND (IRSensors.Detect(3)) OR  (NOT(IRSensors.Detect(1) OR (IRSensors.Detect(3))))))
           turningToAvoidObstacle := random(2) + 1
           RBC.DebugLongCr(~turningToAvoidObstacle)
        elseif (IRSensors.Detect(3))
           turningToAvoidObstacle := RIGHT
        elseif (IRSensors.Detect(1))
           turningToAvoidObstacle := LEFT

    'translate flags into desired speed
    'The use of flags here may be redundant, but
    'its not broken, so i am not going to fix it
    
    case (turningToAvoidObstacle)
      RIGHT:
        PivotRight(0)
      LEFT:
        PivotLeft(0)
      STOP:
        StopMoving

        'keep an eye out for buggy behaviour here. I took
        'out the NOTURN clause, which shouldn't matter, but
        'who knows

  elseif (extraTurnOn == false) OR (extraTurn == 0)
    If (currentPriority == collisionPriority)
      repeat until (Stopped)
        StopMoving
      currentPriority := 0
      obstacleDetected := 0
      turningToAvoidObstacle := NOTURN
        
    else
      MaxSpeed
    
  else
    RBC.DebugStrCr(string("extraTurn?"))
    extraTurn --
   
  
 
PRI Pivot (dir, xangle)| pivotCount, cout
  ResetCount
  xangle := F.FFLOAT(xangle)
  pivotCount := F.FROUND(F.FDIV(F.RADIANS(xangle), 0.01892))
  RBC.DebugStrCr(string("pivotCount"))
  RBC.DebugLongCr(pivotCount)
  repeat until((Encoders.GetLeftCount + Encoders.GetRightCount) => pivotCount)
    cout := Encoders.GetLeftCount + Encoders.GetRightCount
    RBC.DebugStrCr(string("encoder count"))
    RBC.DebugLongCr(cout)
    sendSensorReadings
    if (dir == RIGHT)
      Control.SetSpeeds(LeftTurnRight, RightTurnRight)
    else
      Control.SetSpeeds(LeftTurnLeft, RightTurnLeft)
  Control.SetSpeeds(0,0)
  ResetCount

PRI sendSensorReadings | t
    t := Dirrs.DistanceCM
    RBC.DebugStr(string("Dirrs: "))
    RBC.DebugLongCr(t)
    if (t =< 20) OR (t=>80)
    'if (t=>80)
      RBC.DebugStrCr(string("Bad data"))
      t := -1
    dataout[0] := t / 256
    dataout[1] := t // 256
    
    t := Sonar.DistanceCM
    RBC.DebugStr(string("Sonar: "))
    RBC.DebugLongCr(t)
    if (t =< 20) OR (t=>300)
    'if (t=>300)
      RBC.DebugStrCr(string("Bad data"))
      t := -1
    dataout[2] := t / 256
    dataout[3] := t // 256
    
    IRSensors.capture
    t := IRSensors.Detect(6)
    RBC.DebugStr(string("IR: "))
    RBC.DebugLongCr(t)
    dataout[4] := t / 256
    dataout[5] := t // 256

    

    RBC.SendDataToPc(@dataout, 6, RBC#OUTPUT_TO_NONE)  
PRI ResetCount
  'leftWheelCount := 0
  'rightWheelCount := 0
  Encoders.ResetCounters
         
PRI random (modValue) : retValue
  rand := cnt
  rand := ?rand
  ||rand
  retValue := rand // modValue
  
PRI Stopped
  if (Control.WheelsAreStopped == 1)
    return 1

PRI moveForward
  Control.setSpeeds(MaxLeftSpeed,MaxRightSpeed)
  waitCnt(500000000 + cnt)
  Control.setSpeeds(0,0)
  
PRI backUpAndTurn
  'we want a little bit of specific behaviour here,
  'so I hijack the priority algorithm and just run
  'through the behaviour I want by taking direct control
  'of the servos. Basically a big hack

  Control.setSpeeds(-20,-20)
  waitCnt(100000000 + cnt)
    'beep beep beep
    'back up
  if (random(2) == 1)
    Control.setSpeeds(LeftTurnLeft, RightTurnLeft)
  else
    Control.setSpeeds(LeftTurnRight, RightTurnRight)
  waitCnt(100000000 + cnt)
  Control.setSpeeds(0,0)

PRI StopMoving
  currentRightSpeed := 0
  currentLeftSpeed := 0
  Control.SetSpeeds(0,0)


PRI MaxSpeed
  currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := MaxRightSpeed

PRI turnRight
  RBC.DebugStrCr(string("turning right"))
  
  currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := MaxRightSpeed - 7
  'currentLeftSpeed := LeftTurnRight
  spin := RIGHT
 

PRI turnLeft
  RBC.DebugStrCr(string("turning left"))
  currentRightSpeed := MaxRightSpeed
  'currentRightSpeed := RightTurnLeft
  currentLeftSpeed := MaxLeftSpeed - 7
  
  spin := LEFT

PRI PivotRight (speed)
  RBC.DebugStrCr(string("pivoting right"))
  
  'currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := RightTurnRight
  currentLeftSpeed := LeftTurnRight
  spin := RIGHT
 

PRI PivotLeft (speed)
  RBC.DebugStrCr(string("pivoting left"))
  'currentRightSpeed := MaxRightSpeed
  currentRightSpeed := RightTurnLeft
  currentLeftSpeed := LeftTurnLeft
  
  spin := LEFT
 
