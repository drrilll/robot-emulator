{{
  BlockGame

  This code causes the robot to look for orange blocks and push them to a wall without bumping into other robots.
}}


CON
  'These constants are required for proper timing.
  'They should appear at the top of every cog program you write.
  _clkmode   = xtal1 + pll16x
  _xinfreq   = 5_000_000
                      
VAR
   long leftTotal     
   long rightTotal    

   long wanderLeft    
   long wanderRight   

   long avoidLeft     
   long avoidRight

   long targetLeft
   long targetRight
   long targetStraight

  ' long backup

   long random
   long turnCounter
   long haveSeenBlock
   long haveBlock

   long actualLeftSpeed
   long actualRightSpeed
   long desiredLeftSpeed
   long desiredRightSpeed
   
CON
   wanderPriority     = 2
   avoidPriority      = 8
   goalPriority       = 4

   RED_TRACK = 187
   GREEN_TRACK = 16
   BLUE_TRACK = 16
   SENSITIVITY = 30

   ACCELERATION = 4
   FULL_SPEED = 20
   HALF_SPEED = 12
                    
OBJ 
  Beeper:       "Beeper"                   ' allows robot to make beeps (good for debugging) 
  Servos:       "ServoControl"
  IRSensors:    "IR8SensorArray" 
  Block:        "BlockSensor"
  Camera:       "CMUCam"  
  RBC:          "RBC"
  
PUB Main
  RBC.Init
  Camera.Start
  Camera.SetTrackColor(RED_TRACK,GREEN_TRACK,BLUE_TRACK,SENSITIVITY)
  Beeper.Startup 
  Servos.Start(748, 749, true, true, true, false)
  Servos.SetHeadPitch(105)

  actualLeftSpeed := 0
  actualRightSpeed := 0
  desiredLeftSpeed := FULL_SPEED
  desiredRightSpeed := FULL_SPEED

  turnCounter := 1
  repeat
   wanderLeft := wanderRight := avoidLeft := avoidRight := targetLeft := targetRight := targetStraight := 0
   random := || ?cnt // 15
   rbc.debugstr(string("random: "))
   rbc.debuglong(random)
   
    ' Update the speeds by accelerating or decelerating accordingly
    MakeAMove

    'Now determine if an obstacle or block is seen
    LookForBlock
    if (Block.Detect)
      FoundBlock
    IRSensors.capture                          

    ' If detect right only, then turn left
    if (IRSensors.Detect(1) AND not IRSensors.Detect(3))
      if (haveBlock)
        DropBlock
      else
        avoidRight := 1
        turnCounter := 2
      
    ' If detect left only, then turn right
    elseif (IRSensors.Detect(3) AND not IRSensors.Detect(1))
      if (haveBlock)
        DropBlock
      else
        avoidLeft := 1
        turnCounter := 2
      
    ' If detect ahead, then make random turn
    elseif (IRSensors.Detect(2))
      if (haveBlock)
        DropBlock
      else
        if(random // 2 == 0)
          avoidRight := 1  
        else
          avoidLeft := 1
        turnCounter := 2

    ' Make a random turn once in a while
    elseif(random == 0)
        if(random // 2 == 0)
          wanderLeft := 1     
        else
          wanderRight := 1
        turnCounter := 5 
    else
      turnCounter := 1

    ' Do not wander if found block or have block
    if (haveSeenBlock OR haveBlock)
      wanderLeft := wanderRight := 0
                               
    leftTotal := (wanderLeft * wanderPriority) + (avoidLeft * avoidPriority) + (targetLeft * goalPriority)
    rightTotal := (wanderRight * wanderPriority) + (avoidRight * avoidPriority) + (targetRight * goalPriority)

    if (leftTotal > rightTotal)
        desiredLeftSpeed := FULL_SPEED
        desiredRightSpeed := -1 * FULL_SPEED                    
    elseif (rightTotal > leftTotal)
        desiredLeftSpeed := -1 * FULL_SPEED
        desiredRightSpeed := FULL_SPEED                          
    else                   
        desiredLeftSpeed := FULL_SPEED
        desiredRightSpeed := FULL_SPEED 
               
  Servos.Stop


PUB MakeAMove
    repeat until (turnCounter == 0)
      if(actualLeftSpeed < desiredLeftSpeed)
          actualLeftSpeed += ACCELERATION
      elseif(desiredLeftSpeed < actualLeftSpeed)
          actualLeftSpeed -= ACCELERATION
      if(actualRightSpeed < desiredRightSpeed)
          actualRightSpeed += ACCELERATION
      elseif(desiredRightSpeed < actualRightSpeed)
          actualRightSpeed -= ACCELERATION
      Servos.setSpeeds(actualLeftSpeed, actualRightSpeed)
      turnCounter--     
      rbc.debugstr(string("turncounter: "))
      rbc.debuglongcr(turncounter)
      waitcnt(1600000 + cnt)



      
PUB LookForBlock
  Camera.TrackColor   
  if (Camera.GetCenterX == 0) AND (Camera.GetConfidence == 0)
    'DO NOTHING
  elseif (Camera.GetCenterX > 55) AND (Camera.GetConfidence > 5) 'LEFT SIDE
    targetRight := 1
  elseif (Camera.GetCenterX < 15) AND (Camera.GetConfidence > 5) 'RIGHT SIDE  
    targetLeft := 1
  else
    targetStraight := 1         ' STRAIGHT AHEAD
  
  haveSeenBlock := targetRight OR targetLeft OR targetStraight
  if (targetStraight)
    Beeper.beep(20,500) 
    RBC.DebugStr(String("Block Straight Ahead!"))
    RBC.DebugCr 
  elseif (haveSeenBlock)
     Beeper.beep(20,250)
     RBC.DebugStr(String("Steering toward block ..."))
     RBC.DebugCr


PUB FoundBlock
  ' Close the grippers
  Servos.SetLeftGripper(Servos#LEFT_GRIPPER_MIN)
  Servos.SetRightGripper(Servos#RIGHT_GRIPPER_MIN)
  Beeper.Ok
  haveBlock := true
  RBC.DebugStr(String("Found a Block!"))
  RBC.DebugCr 
  Servos.SetHeadPitch(Servos#PITCH_MID)


PUB DropBlock
  ' Open the grippers
  Servos.SetLeftGripper(Servos#LEFT_GRIPPER_MAX)
  Servos.SetRightGripper(Servos#RIGHT_GRIPPER_MAX)
  haveBlock := false

  ' Back up
  desiredLeftSpeed := -FULL_SPEED
  desiredRightSpeed := -FULL_SPEED                     
  turnCounter := 50
  MakeAMove
  RBC.DebugStr(String("Dropped Off a Block!"))
  RBC.DebugCr 
  Beeper.Error

  ' Turn Away             
  if(random // 2 == 0)
    desiredLeftSpeed := -1 * FULL_SPEED
    desiredRightSpeed := FULL_SPEED                       
  else
    desiredLeftSpeed := FULL_SPEED
    desiredRightSpeed := -1 * FULL_SPEED                  
  turnCounter := 10
  rbc.debugstr(string("turn counter: "))
  rbc.debuglongcr(turncounter)
  MakeAMove
  Servos.SetHeadPitch(105)
