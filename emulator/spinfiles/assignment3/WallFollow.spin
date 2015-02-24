CON

  _clkmode = xtal1 + pll16x
  _xinfreq = 5_000_000
  leftServoStoppedValue = 747
  rightServoStoppedValue = 748
  headCenterPitch = 134
  headDownPitch = 115
  headCenterYaw = 145
  leftGripperStraight = 180
  rightGripperStraight = 123
  MaxLeftSpeed = 24
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
  WCOUNT = 150
  LIMIT = 3
  DEBUG = true
  PositionTolerance = 5
  AngleTolerance = 5
  

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
  byte currentPriority
  byte pathPriority
  byte wallFollowPriority
  byte extraTurnOn
  byte slowCount
  long leftWheelCount
  long rightWheelCount
  byte getPose
  long x
  long y
  long xpos[5], ypos[5]
  long angle
  long ricc
  long pr
  long pl
  long theta
  byte spin
  long temp
  long TWOPI
  byte dataIn[23]
  byte dout[6]
  byte destination
  long distance
  long pathCounter
  byte wallFound
  byte aligned

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
  pathPriority := 2
  collisionPriority := 4
  wallFollowPriority := 8
  pathCounter := 0
 
  extraTurnOn := 1
  leftWheelCount := 0
  rightWheelCount := 0
  wallFound := 0
  aligned :=0

  x := 0
  y := 0
  angle := 0.0
  getPose := 1
  spin := 0
  destination := 0

  
  'code start

  Beeper.Startup
  RBC.Init          'connect to pc and wait
  Encoders.Start
  Encoders.ResetCounters
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

  RBC.DebugClear  

  'occasionally we get bad data, so repeat until its good
 ' repeat until (receivePose)
  RBC.DebugStrCr(string("WTF?"))


  'The timing works with the debug values, so i left them in

  'I explicitly check both values of a boolean flag
  'to increase readability (sometimes)

  currentPriority := 0
  repeat
    if (wallFollowPriority => currentPriority)
      wallFollow
    if (pathPriority => currentPriority)
      followPath
    if (collisionPriority => currentPriority)
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
    if (getPose)
      calculatePose
    Control.setSpeeds (currentLeftSpeed, currentRightSpeed)
    

    'debug code
    
    RBC.DebugStrCr(string("Speeds"))
    RBC.DebugLongCr(currentLeftSpeed)
    RBC.DebugLongCr(currentRightSpeed)

PUB wallFollow

     currentPriority := wallFollowPriority
     RBC.DebugStrCr(string("Wall following"))
     if (wallFound == 0)
       RBC.DebugStrCr(string("finding wall"))
       findWall
     elseif (aligned == 0)
       RBC.DebugStrCr(string("aligning"))
       alignWithWall
     else
       'quite the chain of logic
       IRSensors.capture
       if (IRSensors.Detect(2) OR (IRSensors.Detect(3)))
         pivotLeft(0)
       elseif (IRSensors.Detect(1))
         turnLeft(8)
       elseif (NOT(IRSensors.Detect(7))AND (IRSensors.Detect(6)))
         turnRight (8)
       elseif (NOT(IRSensors.Detect(6))AND (NOT(IRSensors.Detect(7))))
         turnRight(15)       
       elseif (IRSensors.Detect(7))
         MaxSpeed
    
PUB alignWithWall
  
  IRSensors.capture
  if (IRSensors.Detect(7))
    RBC.DebugStrCr(string("IR7 detects!!"))
    aligned := 1
  elseif (IRSensors.Detect(2) OR IRSensors.Detect(3))
    RBC.DebugStrCr(string("pivot left"))
    PivotLeft(0) 
  elseif (IRSensors.Detect(1))
    RBC.DebugStrCr(string("setspeed 15"))
    setSpeed(15)
  else
    'lost the wall
    RBC.DebugStrCr(string("lost the wall"))
    MaxSpeed
    wallFound := 0
      
PUB findWall
  IRSensors.capture
  if (IRSensors.Detect(1) OR IRSensors.Detect(2) OR IRSensors.Detect(3))
    RBC.DebugStrCr(string("Pow! a wall"))
    StopMoving
    wallFound := 1
  else
    MaxSpeed
  
PUB followPath | deltax, deltay, deltaAngle

  'at this point we are only overriding the wander behaviour
  currentPriority := pathPriority

  'are we close enough? if so stop and decide where to next
  'calculate the direction we need to go
  'if its beyond a certain tolerance we stop and turn

  'pathCounter is the distance we were supposed to travel
  'to reach our mark. So now we check our new location, or
  'get a bearing on our first coordinate if this is the start

  if (pathCounter == 0)
    deltax := F.FTRUNC( F.FSUB(xpos[destination] , x))
    deltay := F.FTRUNC(F.FSUB(ypos[destination], y))
    RBC.DebugStrCr(string("deltax"))
    RBC.DebugLongCr(deltax*3)
    RBC.DebugStrCr(string("deltay"))
    RBC.DebugLongCr(deltay*3)
  
    if (F.FROUND(F.FABS(F.FFLOAT(deltax))) < PositionTolerance) AND (F.FROUND(F.FABS(F.FFLOAT(deltay))) < PositionTolerance)
      'we got to a destination. Yay!
      destination ++
      RBC.DebugStrCr(string("**************************************"))
      RBC.DebugLongCr(destination)
      if (destination > 4)
        'we are back home so park it
        repeat
          StopMoving        
    else
       'calculate if we are on the path to the target
       'we check this angle a little later in the code
       deltaAngle := F.DEGREES(F.ATAN2(F.FFLOAT(deltay),F.FFloat(deltax)))

       'putting add followed by mod gets rid of negatives
       'puts it in the 360 range no matter what the number

       deltaAngle := F.FADD(deltaAngle, 360.0)
       deltaAngle := F.FMOD(deltaAngle, 360.0)
       RBC.DebugStrCr(string("pure deltaAngle"))
       RBC.DebugStrCr(FS.FloatToString(deltaAngle))
       RBC.DebugStrCr(string("angle"))
       RBC.DebugStrCr(FS.FloatToString(angle))
       deltaAngle := F.FSUB(deltaAngle, angle)
       deltaAngle := F.FADD(deltaAngle, 360.0)
       deltaAngle := F.FMOD(deltaAngle, 360.0)
       RBC.DebugStrCr(string("added deltaAngle"))
       RBC.DebugStrCr(FS.FloatToString(deltaAngle))
       RBC.DebugStrCr(string("istopped value"))
       RBC.DebugLongCr(isStopped)

       'I noticed on the video that there was a bit of a stutter
       'when the robot stopped. If I had more time I would tighten
       'things up. Maybe over reading week.

       'We enter this when the angle to the target is off.
       'We fully stop first, hence "isStopped"
       if (isStopped)
         RBC.DebugStrCr(string("in istopped"))
         Pivot(deltaAngle)

         'presumably we are at a good angle now
         'I don't check. Once in a blue moon, when we are tight
         'to the target (or too close to obstacles) this angle
         'will be noticably off, but its rare

         repeat until (Stopped)
           StopMoving
         getPose := 1
         isStopped := 0

         'get the distance to the target in cm
         distance := F.FSQR(F.FADD(F.FFLOAT(deltax * deltax), F.FFLOAT(deltay * deltay)))
         RBC.DebugStrCr(string("distance"))
         RBC.DebugStrCr(FS.FloatToString(distance))

         'convert the distance to encoder ticks
         pathCounter := F.FROUND(F.FDIV(distance, 0.1684))
         RBC.DebugStrCr(string("path counter"))
         RBC.DebugStrCr(FS.FloatToString(F.FFLOAT(pathCounter)))

       elseif (F.FROUND(F.FABS(deltaAngle)) > angleTolerance)

         'Our angle is off. Lets stop and fix it!
         RBC.DebugStrCr(string("in tolerance angle check"))

         'I loop back around before pivoting. It allows us to collect a pose
         'So I just set flags that I check next time around
         repeat until (Stopped)
           StopMoving
         getPose := 1
         isStopped := 1
       else

         'angle is still good but not in range
         'recalculate distance. This almost never executes.

         distance := F.FSQR(F.FADD(F.FMUL(deltax, deltax), F.FMUL(deltay, deltay)))
         pathCounter := F.FROUND(F.FDIV(distance, 0.1684))
  else
    'check if we are where we are supposed to be
    if (pathCounter < Encoders.GetLeftCount)
      repeat until(Stopped)
        StopMoving
      getPose := 1
      pathCounter := 0


   
PUB calculatePose

  pr := Encoders.GetLeftCount
  pl := Encoders.GetRightCount
  if (spin <> 0)

    theta := F.Degrees(F.FMUL(0.01892,F.FFLOAT(pr+pl))) 
    if (spin == RIGHT)
      theta :=  F.FNEG(theta)
    RBC.DebugStrCr(string("theta"))
    RBC.DebugStrCr(FS.FloatToString(theta))
    
    theta := F.FMOD(theta,360.0)
    RBC.DebugStrCr(string("modded theta"))
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
    

   'decided to go with the "straight path" code
  
   'if (pr == pl)
    if(true) 
      'going straight (ish)

      x := F.FADD(x,F.FMUL(0.1684, F.FMUL(F.FFLOAT(pr),F.COS(F.RADIANS(angle)))))
      'throwing away negative values. If they are negative then accuracy is already
      'comprimised anyway, and they really mess up the math
      x := F.FMAX(0.0,x)
      y := F.FADD(y,F.FMUL(0.1684, F.FMUL(F.FFLOAT(pr),F.SIN(F.RADIANS(angle)))))
      y := F.FMAX(0.0,y)
    else

      ricc := F.FADD(F.FMUL(8.9, F.FDIV(F.FFLOAT(pl), F.FFLOAT(pr-pl))), 4.45)
      RBC.DebugStrCr(string("ricc"))
      RBC.DebugStrCr(FS.FloatToString(ricc))
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
      
  RBC.DebugStrCr(string("Strike a Pose"))
  RBC.DebugLongCr(pr)
  RBC.DebugLongCr(pl)
  RBC.DebugStrCr(FS.FloatToString(F.FMUL(x, 3.0)))
  RBC.DebugStrCr(FS.FloatToString(F.FMUL(y, 3.0)))
  RBC.DebugStrCr(FS.FloatToString(angle))
  RBC.DebugStrCr(string("Vogue"))
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
       
PUB receivePose
  RBC.ReceiveData(@dataIn)
  x := dataIn[1]*256 + dataIn[2]
  y := dataIn[3]*256 + dataIn[4]
  angle := dataIn[5]*256 + dataIn[6]
  if (x == 255) AND ( y == 255) AND (angle == 255)
    return false
  x := F.FDIV(F.FFLOAT(x),3.0)
  y := F.FDIV(F.FFLOAT(y),3.0)
  'remember our initial position
  xpos[4] := x
  ypos[4] := y
  'get the additional data and convert to cm
  xpos[0] := dataIn[7]*256 + dataIn[8]
  ypos[0] := dataIn[9]*256 + dataIn[10]
  xpos[1] := dataIn[11]*256 + dataIn[12]
  ypos[1] := dataIn[13]*256 + dataIn[14]
  xpos[2] := dataIn[15]*256 + dataIn[16]
  ypos[2] := dataIn[17]*256 + dataIn[18]
  xpos[3] := dataIn[19]*256 + dataIn[20]
  ypos[3] := dataIn[21]*256 + dataIn[22]
  xpos[0] := F.FDIV(F.FFLOAT(xpos[0]),3.0)
  ypos[0] := F.FDIV(F.FFLOAT(ypos[0]),3.0)
  xpos[1] := F.FDIV(F.FFLOAT(xpos[1]),3.0)
  ypos[1] := F.FDIV(F.FFLOAT(ypos[1]),3.0)
  xpos[2] := F.FDIV(F.FFLOAT(xpos[2]),3.0)
  ypos[2] := F.FDIV(F.FFLOAT(ypos[2]),3.0)
  xpos[3] := F.FDIV(F.FFLOAT(xpos[3]),3.0)
  ypos[3] := F.FDIV(F.FFLOAT(ypos[3]),3.0)
  
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
  RBC.DebugStr(string("("))
  RBC.DebugStr(FS.FloatToString(F.FMUL(xpos[0],3.0)))
  RBC.DebugStr(string(","))
  RBC.DebugStr(FS.FloatToString(F.FMUL(ypos[0], 3.0)))
  RBC.DebugStrCr(string(")"))
  RBC.DebugStrCr(string("pos 2"))
  RBC.DebugStr(string("("))
  RBC.DebugStr(FS.FloatToString(F.FMUL(xpos[1],3.0)))
  RBC.DebugStr(string(","))
  RBC.DebugStr(FS.FloatToString(F.FMUL(ypos[1], 3.0)))
  RBC.DebugStrCr(string(")"))
  RBC.DebugStrCr(string("pos 3"))
  RBC.DebugStr(string("("))
  RBC.DebugStr(FS.FloatToString(F.FMUL(xpos[2],3.0)))
  RBC.DebugStr(string(","))
  RBC.DebugStr(FS.FloatToString(F.FMUL(ypos[2], 3.0)))
  RBC.DebugStrCr(string(")"))
  RBC.DebugStrCr(string("home"))
  RBC.DebugStr(string("("))
  RBC.DebugStr(FS.FloatToString(F.FMUL(xpos[3],3.0)))
  RBC.DebugStr(string(","))
  RBC.DebugStr(FS.FloatToString(F.FMUL(ypos[3], 3.0)))
  RBC.DebugStrCr(string(")"))
  
  destination := 0
  
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
  
PRI Stopped
  if (Control.WheelsAreStopped == 1)
    RBC.DebugStrCr(string("getPose"))
    getPose := 1
    return 1

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
  Control.SetSpeeds(0,0)


PRI MaxSpeed
  currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := MaxRightSpeed
  {{if (leftWheelCount == 0)
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
      currentRightSpeed ++    }}

PRI Pivot (deltaAngle) | pivotCount , xangle
  ResetCount
  xangle := F.FADD(deltaAngle, 360.0) 
  xangle := F.FMOD(xangle,360.0)
  if (F.FROUND(xangle)>180)
    xangle := F.FABS(F.FSUB(xangle, 360.0))
    spin := RIGHT
  else
    xangle := deltaAngle
    spin := LEFT
  pivotCount := F.FROUND(F.FDIV(F.RADIANS(xangle), 0.01892))
  RBC.DebugStrCr(string("pivotCount"))
  RBC.DebugLongCr(pivotCount)
  repeat until((Encoders.GetLeftCount + Encoders.GetRightCount) => pivotCount)
    if (spin == RIGHT)
      RBC.DebugStrCr(string("pivot right"))
      Control.SetSpeeds(LeftTurnRight, RightTurnRight)
    else
      RBC.DebugStrCr(string("pivot left"))
      Control.SetSpeeds(LeftTurnLeft, RightTurnLeft)
  Control.SetSpeeds(0,0)
       
PRI PivotRight (speed)
  RBC.DebugStrCr(string("pivoting right"))
  currentRightSpeed := RightTurnRight - speed
  currentLeftSpeed := LeftTurnRight  + speed
  spin := RIGHT
 

PRI PivotLeft (speed)
  RBC.DebugStrCr(string("pivoting left"))
  currentRightSpeed := RightTurnLeft + speed
  currentLeftSpeed := LeftTurnLeft - speed
  spin := LEFT

PRI turnLeft (turn)
  currentLeftSpeed := MaxLeftSpeed - turn
  currentRightSpeed := MaxRightSpeed

PRI turnRight (turn)
  currentLeftSpeed := MaxLeftSpeed
  currentRightSpeed := MaxRightSpeed - turn

PRI setSpeed (speed)
  currentLeftSpeed := speed
  currentRightSpeed := speed

