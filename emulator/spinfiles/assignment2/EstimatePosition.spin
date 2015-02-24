CON

  _clkmode = xtal1 + pll16x
  _xinfreq = 5_000_000
  leftServoStoppedValue = 747
  rightServoStoppedValue = 748
  headCenterPitch = 134
  headDownPitch = 115
  headCenterYaw = 148
  leftGripperStraight = 180
  rightGripperStraight = 123
  MaxLeftSpeed = 30
  MaxRightSpeed = 28
  LeftTurnLeft = -15
  LeftTurnRight = 15
  RightTurnLeft = 15
  RightTurnRight = -15
  NOTURN = 0
  LEFT = 1
  RIGHT = 2
  STOP = 3
  STOPTWO = 4 
  WCOUNT = 150
{{  RED = 205
  BLUE = 16
  GREEN = 16
  Sensitivity = 30
  confidence = 20   }}
  LIMIT = 3
  DEBUG = true
  

VAR
  
  byte turningToAvoidObstacle
  byte obstacleDetected
  long rand
  byte wanderDirection
  byte wanderFlag
  long currentLeftSpeed, currentRightSpeed
  long wanderCount
  byte turnCount
  long extraTurn
  byte collisionPriority
  byte wanderPriority
  byte currentPriority
  byte extraTurnOn
  byte slowCount
  long leftWheelCount
  long rightWheelCount
  byte getPose
  long x
  long y
  long angle
  long ricc
  long pr
  long pl
  long theta
  byte spin
  long temp
  long TWOPI
  byte dataIn[7]
  byte dout[6]
  long cout
  long cin

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
  debug: "debugger"

PUB main
'initialize variables here, so they are easy to find
'I use powers of 2 for priority. Not strictly necessary
'how I do it here, but I may change my approach in the future

  currentPriority := 0
  wanderPriority := 1
  collisionPriority := 2
 
  extraTurnOn := 1
  leftWheelCount := 0
  rightWheelCount := 0

  x := 0
  y := 0
  angle := 0.0
  getPose := 1
  spin := 0

  
  'code start

  Beeper.Startup
  RBC.Init          'connect to pc and wait
  Encoders.Start
  Encoders.ResetCounters
  'Cam.Start
  'Cam.SetTrackColor(RED,GREEN,BLUE, Sensitivity)
  Control.Start(leftServoStoppedValue, rightServoStoppedValue,true, true, true, true)
  Control.SetHeadPitch(headCenterPitch)
  Control.SetHeadYaw(headCenterYaw)
  Control.SetLeftGripper(leftGripperStraight)
  Control.SetRightGripper(rightGripperStraight)
  F.Start
  Beeper.OK
  wanderCount := 0
  currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := MaxRightSpeed
  extraTurn := 0
  TWOPI := F.FMUL(2.0,pi)
  
  
  RBC.DebugClear
  RBC.DebugStrCr(string("WTF?"))
  'occasionally we get bad data, so repeat until its good
  repeat until (receivePose)
  RBC.DebugStrCr(string("WTF?"))


  'The timing works with the debug values, so i left them in

  'I explicitly check both values of a boolean flag
  'to increase readability (sometimes)

  currentPriority := 0
  repeat
    if (collisionPriority => currentPriority)
      'RBC.DebugStrCr(string("avoidcollsions"))
      avoidCollisions
    else
      'trying to keep the timing intact if we need to skip this function
      waitCnt(10_000_000 + cnt)
    if (wanderPriority => currentPriority)
      wander
    else
      'if someone else is pulling rank, we want to reset the
      'wandering, or we could continually wander into the same
      'obstacle
      wanderDirection := NOTURN

    'this is set every time the robot stops

    if (getPose)
      calculatePose
    Control.setSpeeds (currentLeftSpeed, currentRightSpeed)
    

    'debug code
    
    RBC.DebugStrCr(string("Speeds"))
    RBC.DebugLongCr(currentLeftSpeed)
    RBC.DebugLongCr(currentRightSpeed)   
    cout ++
    if cout > 20
       'debug.memorymap(1)     
       cout:=0
   
PUB calculatePose

  pr := Encoders.GetLeftCount
  pl := Encoders.GetRightCount

  {{I count the encodings and adjust wheel speed in order to get the
  robot to travel in a straight line. Here I am checking how frequently
  the encoder values are equal each other, which would render the
  arc code useless.  Ultimately I didn't end up using the arc code, as
  using the straight line formulas seemed to work fine.  I think the
  actual frequency they were equal was around 1 in 8 or so, but thats
  an estimate}}

  if (pr == pl)
    RBC.DebugStrCr(string("uh oh"))
    RBC.DebugLongCr(pr)

  'any time the robot pivots I set s flag to the direction
  'of the pivot

  if (spin <> 0)
    theta := F.Degrees(F.FMUL(0.01892,F.FFLOAT(pr+pl))) 
    if (spin == RIGHT)
      theta :=  F.FNEG(theta)
    rbc.debugstrcr(string("change in angle"))
    RBC.DebugStrCr(FS.FloatToString(theta))
    theta := F.FMOD(theta,360.0)
    rbc.debugstrcr(string("change in angle adjusted"))
    RBC.DebugStrCr(FS.FloatToString(theta))
    angle := F.FADD(angle, theta)
    'gets rid on negative values. If its not negative it
    'gets modded out anyway
    angle := F.FADD(angle, 360.0) 
    angle := F.FMOD(angle,360.0)
    RBC.DebugStrCr(string("final angle value"))  
    RBC.DebugStrCr(FS.FloatToString(angle))
    spin := 0  
  else

{{i was going to only use this code if the encoder values were equal, but the
encoders were generally only off by at the very most 5 clicks, but generally less
so I just used the "straight line code" exclusively}}

   'if (pr == pl)
    if(true) 
      'going straight (ish)
      rbc.debugstrcr(string("using straight line formula"))
      x := F.FADD(x,F.FMUL(0.1684, F.FMUL(F.FFLOAT(pr),F.COS(F.RADIANS(angle)))))
      'throwing away negative values. If they are negative then accuracy is already
      'comprimised anyway, and they really mess up the math
      x := F.FMAX(0.0,x)
      y := F.FADD(y,F.FMUL(0.1684, F.FMUL(F.FFLOAT(pr),F.SIN(F.RADIANS(angle)))))
      y := F.FMAX(0.0,y)

    'I didn't end up using this code. There is still a bug in it,
    '(the angles were right but the distances were really small)
    'but the straight line code was working and it was a busy week
    'so I left it

    else

      ricc := F.FADD(F.FMUL(8.9, F.FDIV(F.FFLOAT(pl), F.FFLOAT(pr-pl))), 4.45)
      RBC.DebugStrCr(string("ricc"))
      RBC.DebugStrCr(FS.FloatToString(ricc))

      'I was still learning the Float Math, and didn't
      'realize there was a FDEGREES conversion,
      'so this is a home-made conversion
      'I think InverseKinematics only uses FDEGREES

      theta := F.FMUL(F.FMUL(0.01892,F.FFLOAT(pr-pl)), TWOPI)
      theta := F.FADD(theta,360.0)
      theta := F.FMOD(theta, 360.0)
      RBC.DebugStrCr(string("theta"))
      RBC.DebugStrCr(FS.FloatToString(theta))
      y := F.FADD(y, F.FMUL(ricc, F.FSUB(F.COS(F.RADIANS(angle)), F.COS(F.RADIANS(F.FADD(angle,theta))))))
      x := F.FADD(x, F.FMUL(ricc, F.FSUB(F.SIN(F.RADIANS(F.FADD(angle, theta))), F.SIN(F.RADIANS(angle)))))
    
     ' temp := F.FSUB(F.COS(F.RADIANS(angle)),F.COS(F.RADIANS(F.FADD(angle, theta))))
     ' y := F.FADD(y, F.FMUL(ricc, temp))
     ' temp := F.FSUB(F.SIN(F.RADIANS(F.FADD(angle, theta))), F.SIN(F.RADIANS(angle)))
     ' x:= F.FADD(x, F.FMUL(ricc, temp))
      angle := F.FADD(angle,theta)
      angle := F.FADD(angle,360.0)
      angle := F.FMOD(angle,360.0)    
      
  RBC.DebugStrCr(string("Pose"))
  rbc.debugstr(string("pr: "))
  RBC.DebugLongCr(pr)
  rbc.debugstr(string("pL: "))
  RBC.DebugLongCr(pl)
  rbc.debugstr(string("x: "))
  RBC.DebugStrCr(FS.FloatToString(F.FMUL(x, 3.0)))
  rbc.debugstr(string("y: "))
  RBC.DebugStrCr(FS.FloatToString(F.FMUL(y, 3.0)))
  rbc.debugstr(string("angle: "))
  RBC.DebugStrCr(FS.FloatToString(angle))
  RBC.DebugStrCr(string("End pose"))
  'reset the pose flag
  getPose := 0
  temp := F.FMUL(x, 3.0)    'get back into pixels
  dout[0] := F.FROUND(temp) / 256
  dout[1] := F.FROUND(temp) // 256
  temp := F.FMUL(y, 3.0)
  dout[2] := F.FROUND(temp) / 256
  dout[3] := F.FROUND(temp) // 256
  dout[4] := F.FROUND(angle) / 256
  dout[5] := F.FROUND(angle) // 256
  RBC.SendDataToPc(@dout, 6, RBC#OUTPUT_TO_NONE)
  'reset the encoder values and our encoder variables
  ResetCount

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
      
      currentPriority := 0
      obstacleDetected := 0
      turningToAvoidObstacle := NOTURN
        
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
      rbc.debugstrcr(string("maxspeed@normalize speeds"))
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
               StopMoving
               if (wanderDirection == STOPTWO)
                 wanderDirection := NOTURN
                 
                 wanderCount := random(WCOUNT) + WCOUNT
                 wanderFlag := 0
                 
               else
                 wanderDirection := random(2) + 1
             
               
                 
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
       rbc.debugstrcr(string("maxspeed@default behaviour"))
       MaxSpeed
       wanderCount--
PUB receivePose
  RBC.ReceiveData(@dataIn)
  rbc.debugstrcr(string("data in:"))
  repeat cin from 0 to 7
     rbc.debuglongcr(dataIn[cin])
  x := dataIn[1]*256 + dataIn[2]
  y := dataIn[3]*256 + dataIn[4]
  angle := dataIn[5]*256 + dataIn[6]
  if (x == 255) AND ( y == 255) AND (angle == 255)
    return false
  x := F.FDIV(F.FFLOAT(x),3.0)
  y := F.FDIV(F.FFLOAT(y),3.0)
  angle := F.FFLOAT(angle)
  RBC.DebugStr(string("("))
  RBC.DebugLong(dataIn[1]*256 + dataIn[2])
  RBC.DebugStr(string(","))
  RBC.DebugLong(dataIn[3]*256 + dataIn[4])
  RBC.DebugStr(string(") angle: "))
  RBC.DebugLongCr(dataIn[5]*256 + dataIn[6])
  RBC.DebugStr(string("("))
  RBC.DebugStr(FS.FloatToString(x))
  RBC.DebugStr(string(","))
  RBC.DebugStr(FS.FloatToString(y))
  RBC.DebugStr(string(") angle: "))
  RBC.DebugStrCr(FS.FloatToString(angle))
  
  return true 

PRI ResetCount
  leftWheelCount := 0
  rightWheelCount := 0
  Encoders.ResetCounters

PRI random (modValue) : retValue
  rand := cnt
  rand := ?rand
  ||rand
  retValue := rand // modValue
  

PRI edgeForward
  Control.setSpeeds(10,10)
  waitCnt(100000000 + cnt)
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
  RBC.DebugStrCr(string("getPose"))
  getPose := 1


PRI MaxSpeed
  if (leftWheelCount == 0)
    currentLeftSpeed := MaxLeftSpeed
    currentRightSpeed := MaxRightSpeed
  leftWheelCount := Encoders.GetLeftCount
  rightWheelCount := Encoders.GetRightCount
  if (rightWheelCount>leftWheelCount)
    'slow down right wheel
    if ((MaxRightSpeed - currentRightSpeed) < LIMIT)
      currentRightSpeed --
  elseif (leftWheelCount>rightWheelCount)
    'speed up right wheel
    if ((currentRightSpeed - MaxRightSpeed) < LIMIT)
      rbc.debugstrcr(string("speeding up right"))
      currentRightSpeed ++


PRI PivotRight (speed)
  RBC.DebugStrCr(string("pivoting right"))
  currentRightSpeed := RightTurnRight
  currentLeftSpeed := LeftTurnRight
  spin := RIGHT
 

PRI PivotLeft (speed)
  RBC.DebugStrCr(string("pivoting left"))
  currentRightSpeed := RightTurnLeft
  currentLeftSpeed := LeftTurnLeft
  spin := LEFT
 
