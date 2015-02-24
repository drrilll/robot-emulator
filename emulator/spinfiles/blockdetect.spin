con

  fart = 10

var
    long alias
    long s

obj
  rbc: "RBC"
  servo1: "ServoControl"
  IRSensors: "IR8SensorArray"
  debug: "debugger"
  dirrs: "DirrsSensor"
  sonar: "PingSensor"
  cam: "CMUCam"
  bt: "BlockSensor"
  fs: "FloatString"
  f: "Float32Full"

PUB main
  rbc.init
  cam.start
  f.start
  s := f.fmod(3.0,2.0)
  rbc.debugstrcr(fs.floattostring(s))
  servo1.start(1,1,true,true,true,true)
  servo1.setspeeds(15,16)
  s:=3.14
  rbc.debugstrcr(fs.floattostring(s))
  opengrippers
  s:= string("toot")
  repeat
    if (bt.detect)
        rbc.debugstrcr(s) 
        rbc.debugstrcr(string("got block")) 
        midgrippers

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
