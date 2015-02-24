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
  MaxLeftSpeed = 22
  MaxRightSpeed = 20
  LeftTurnLeft = -7
  LeftTurnRight = 7
  RightTurnLeft = 7
  RightTurnRight = -7
  NOTURN = 0
  LEFT = 1
  RIGHT = 2
  STOP = 3
  STOPTWO = 4
  SPINLEFT = 5
  SPINRIGHT = 6
  WCOUNT = 150
  LIMIT = 3
  DEBUG = true
  

VAR
  
  byte turningToAvoidObstacle
  byte obstacleDetected
  long rand
  byte wanderDirection
  byte wanderFlag
  byte isStopped
  long currentLeftSpeed, currentRightSpeed
  long wanderCount
  byte turnCount
  long extraTurn
  byte collisionPriority
  byte wanderPriority
  byte navigatePriority
  byte currentPriority
  byte extraTurnOn
  byte slowCount
  byte getPose
  byte spin
  long temp
  byte dataIn[2]
  byte dout[6]
  byte instruction

OBJ
  
  RBC: "RBC"
  Beeper: "Beeper"
  Control: "ServoControl"
  IRSensors: "IR8SensorArray"
  BlockSensor: "BlockSensor"
  Cam: "CMUCam"
  Encoders: "Encoders"
  F: "Float32Full"
  FS: "FloatString"

PUB main
'initialize variables here, so they are easy to find
'I use powers of 2 for priority. Not strictly necessary
'how I do it here, but I may change my approach in the future

  currentPriority := 0
  wanderPriority := 1
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
  Beeper.OK
  wanderCount := 0
  currentLeftSpeed := 0
  currentRightSpeed := 0
  extraTurn := 0
  
  
  RBC.DebugClear
  RBC.DebugStrCr(string("WTF?"))

  'occasionally we get bad data, so repeat until its good
 ' repeat until (receiveDirections)

  'The timing works with the debug values, so i left them in

  'I explicitly check both values of a boolean flag
  'to increase readability (sometimes)

  currentPriority := 0
  repeat
    
    if (collisionPriority => currentPriority)
      avoidCollisions
    else
      'trying to keep the timing intact if we need to skip this function
      waitCnt(10_000_000 + cnt)
    if (navigatePriority => currentPriority)
      receiveDirections
    if (wanderPriority => currentPriority)
      wander
    else
      'if someone else is pulling rank, we want to reset the
      'wandering, or we could continually wander into the same
      'obstacle
      wanderDirection := NOTURN
    Control.setSpeeds (currentLeftSpeed, currentRightSpeed)
    

    'debug code
    
    RBC.DebugStrCr(string("Speeds"))
    RBC.DebugLongCr(currentLeftSpeed)
    RBC.DebugLongCr(currentRightSpeed)

PUB receiveDirections
  currentPriority := navigatePriority
  RBC.DebugStrCr(string("before RBC receive data"))
  RBC.ReceiveData(@dataIn)
  RBC.DebugStrCr(string("after receive data"))
  instruction := dataIn[1]
  if (instruction == 255)
    return false
  case (instruction)
    NOTURN:
      MaxSpeed

    'the idea was to have a natural turn while in transit and
    'to spin once we got to our destination. But I never got around
    'to it so RIGHT and LEFT are not used, only SPINLEFT and
    'SPINRIGHT are

    RIGHT:
      turnRight
    LEFT:
      turnLeft
    STOP:
      StopMoving
    STOPTWO:
    'means we are done
      repeat
       Control.setSpeeds(0,0)
    SPINLEFT:
      PivotLeft(0)
    SPINRIGHT:
      PivotRight(0)
  
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
      StopMoving
      if (Stopped)
        currentPriority := 0
        obstacleDetected := 0
        turningToAvoidObstacle := NOTURN
        moveForward
        
    else
     
     'nothing was detected but if we are coming out of
     'a turn then turn a little more anyway
     'just to avoid the wall-hugging behaviour.
     'The amount of the extra turn is done randomly when
     'an abstacle is first detected, this way it acts as
     'both flag and counter

     'I found the extra turn behaviour would sometimes get me stuck,
     'I turn it off for simple collision avoidance, and back on
     'for block deliver behaviour to keep it more in the center
     'of the environment

   
      'we just finished turning so reset the
      'counters so we can drive straight
      
      'normalize the speeds
      MaxSpeed
    
  else
    RBC.DebugStrCr(string("extraTurn?"))
    extraTurn --
   
  
 
PUB wander

     'RBC.DebugStrCr(string("WTFwanderCount"))
     'RBC.DebugLongCr(wanderCount)    
     if (wanderCount == 0)
       RBC.DebugStrCr(string("inside wanderCount"))

       if (wanderFlag == 1)
         RBC.DebugStrCr(string("wanderFlag == 1"))

         'we are getting a new direction
         case (wanderDirection)
           STOP,STOPTWO:
             if (Stopped)
               if (wanderDirection == STOPTWO)
                 wanderDirection := NOTURN
                 
                 wanderCount := random(WCOUNT) + WCOUNT
                 wanderFlag := 0
                 
               else
                 wanderDirection := random(2) + 1
             else
               StopMoving
                 
           RIGHT:
             PivotRight(0)
             turnCount --
           LEFT:
             PivotLeft(0)
             turnCount --
           NOTURN:
             'its possible to be overridden by collision and still have stuff left
             'unfinished, so we clean up a bit
             
             wanderFlag := 0
             wanderCount := random(WCOUNT) + WCOUNT 
         if (turnCount == 0)
          wanderDirection := STOPTWO  
       else
         'RBC.DebugStrCr(string("Are we ever here?"))
         if (random(3) == 1)
           'RBC.DebugStrCr(string("Are we ever here?"))

           wanderDirection := STOP
           wanderFlag := 1
           turnCount := Random(10) +10
         else
           wanderCount := random(WCOUNT) + WCOUNT
           RBC.DebugStrCr(string("***************************************"))

       
     else 
     'default behaviour
       MaxSpeed
       wanderCount--
       


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
  RBC.DebugStrCr(string("pivoting right"))
  
  currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := RightTurnRight
  'currentLeftSpeed := LeftTurnRight
  spin := RIGHT
 

PRI turnLeft
  RBC.DebugStrCr(string("pivoting left"))
  currentRightSpeed := MaxRightSpeed
  'currentRightSpeed := RightTurnLeft
  currentLeftSpeed := LeftTurnLeft
  
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
 
