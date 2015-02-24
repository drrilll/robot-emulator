con
  _clkmode = xtal1 + pll16x
  fart = 10

var
    byte alias[9]
    long s

obj
    
    servo:"servocontrol"
    rbc:"RBC"

pub main
    if ( s>2)
        s:=3+1
    else
        s:=2
    rbc.debuglongcr(2)