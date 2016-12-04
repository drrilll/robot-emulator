# robot-emulator

This was my honours project for my undergrad degree. I took a 4th year robotics class. The robots you see were custom built
by my professor. They were "propbots", and were programmed in a low-level, yet object-oriented language called "SPIN".

The one problem with the class is that lab time was limited. I loved the class so much I decided to do a robot emulator for 
my honours project. The goal was to have an editor that you could program with SPIN code, compile it, and then run the simulator 
to see what the robot would do. This would help eliminate the competition for lab time. 

It was a titanic undertaking. I had to teach myself about parsers and compilers. I used ANTLR as the language recognition tool. 
I have found it works quite well. If memory serves, I wrote 12k lines of original code, and ANTLR generated another 13k. 

It pretty much works. I have no doubt there are bugs, but I have a small collection of SPIN programs for which it compiles and
runs and behaves how it is supposed to. 

Edit: Up until now I left the project as an Eclipse project, so you either had to fire it up in Eclipse, or navigate the bin folder to the correct class. I have included robot-emulator.zip, which is a folder with an executable jar file, as well as the necessary config and graphics files in order to make it runnable with simply a double-click. Sorry for not including this sooner. 

The main class is dang.interpreter.IDE.java. This will bring up a primitive editor. The easiest thing to do is load up a
program (file->load). I found that BlockGame.spin works the best. Some of the other programs I was using for testing, and I 
think I left some of them in an error state. Load BlockGame.spin, select "compile" from the menu, and push the "start tracker" 
button at the bottom. 

From there press the "play" icon, and the robot will start looking for blocks. When it finds one it will grab it, bring it to
a wall and release it. There are a bunch of other features you can experiment with. You can add in blocks and obstacles in
real time. 

Enjoy!

Darryl Hill
