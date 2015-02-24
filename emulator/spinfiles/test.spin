
var
    long alias
    long s

obj
  rbc: "rbc"
  servo1: "ServoControl"
  IRSensors: "IR8SensorArray"
  debug: "debugger"
  dirrs: "DirrsSensor"
  sonar: "PingSensor"
  cam: "CMUCam"

PUB main
  rbc.init
  cam.start
  servo1.start(1,1,true,true,true,true)
  alias := lookdown(2: 1, 2..4, 6)
  servo1.setspeeds(15,10)
  repeat
    opengrippers
    cam.trackcolor
    alias := dirrs.distancecm
    midgrippers
    s:= sonar.distancecm
    rbc.debuglongcr(alias) 
    closegrippers
    s++
    s:=s++
    s~~
    rbc.debugstr(string("s: "))
    rbc.debuglongcr(s) 

pub dummy

pub midGrippers
    servo1.setLeftGripper(servo1#left_gripper_mid)
    servo1.setRightGripper(servo1#right_gripper_mid)
PUB openGrippers
    servo1.setLeftGripper(servo1#left_gripper_max)
    servo1.setRightGripper(servo1#right_gripper_max)

PUB closeGrippers
     servo1.setLeftGripper(servo1#left_gripper_min)
     servo1.setRightGripper(servo1#right_gripper_min)
  'debug.memorymap(0)
  'alias := 5
  'debug.memorymap(1)
