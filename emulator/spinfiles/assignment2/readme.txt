Name: Darryl Hill
Student #: 100238971

COMP4807 Assignment 2

A few quick things, for the InverseKinematics.png, I left the estimate position code going as well, so there are 3 lines, the user plotted points in dark green, then the estimated path in light green (almost over top), and the actual path in purple.

For both programs I used encoder counting in a private method called MaxSpeed, along with varying the wheel speed to match them,  so that the robot would travel in a straight line. I then used the straight line formulas to calculate position, and it worked reasonably well. (The encoders would differ by at most 5 but generally between 0 and 4.) The arcing code is there too, but there is a bug in it. It calculates angles ok, but the distances are really small (but proportioned). I didn't bother to find it, because I decided not to use it. I'll probably track it down later when I have spare time.

Happy reading week!