COMP4807 
Assignment 3

Name: Darryl Hill
Student#: 100238971

A few things to note,

	The WallFollow code I built on top of all my existing code, and just built a Wall Follow method with a higher priority and implemented it. Its a bit big, I apologize for that, but the lab is jammed and I don't have time to fix it. It works fine but there is a ton of code that doesn't even get used.  For the Navigate code I stripped it down to collision avoidance and wandering behaviour, so its an easier read.

	In Navigate I implemented all the logic in the NavigationPlanner, and simply sent commands to the robot.  Initially I was under the impression that we couldn't use the encoders.  You did mention that we could use them for turn angles and such, but again lab time is tight and I didn't have time to redo it. It works, but I think I can make it better using a combination of the encoders and the tracker (which I will probably do for the 4th assignment).

	The way it is now there is a constant communication with the robot, and the Planner does all the "thinking" and the robot follows the commands.

	I think thats it, hope there are no surprises. As before, video is on YouTube and pictures are on MediaFire.

Thanks,

Darryl Hill