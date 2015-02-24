package dang.tracker;




import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import dang.interpreter.IDE;
import dang.program.Debug;
import dang.robot.Robot;
import dang.tracker.objects.Block;
import dang.tracker.objects.Environment;
import dang.tracker.objects.FlowerPot;
import dang.tracker.objects.Obstacle;
import dang.tracker.objects.Wall;
/** 
 * The simulator is much simpler; you know everything is starting up
 * because there are no hardware dependencies. It is tempting to 
 * just rip out all this code and rewrite it, but at some point
 * someone may want to integrate connection simulations, etc, so
 * there is a lot of stuff just commented out. Makes for a difficult
 * read, but ultimately (hopefully) it might be useful in the future
 * 
 * changed my mind, ripping stuff out. Easier to just rewrite it later if need be
 */
public class RobotTracker extends JFrame {
	private static  boolean 	USE_WEB_CAM = true;		// Set to true if webcam is available (false for home version)
														// true for simulator
	/*
	 * I attempted to run the bluetooth through the normal channels using piped streams
	 * and the existing infrastructure. But the additional thread slows things down a lot.
	 * the USE_BLUETOOTH variable set to false means we bypass all that stuff, so that the robot has a
	 * byte decoder to decode byte streams and then talks directly to whatever it needs to.
	 * This will eliminate a separate thread and a lot of overhead. But, I will leave the 
	 * ability to use the 'bluetoothsimulator' and pipe streams through existing infrastructure
	 * if you like. Don't say I didn't warn you.
	 */
	private static  boolean 	USE_BLUETOOTH = false;	// Set to true if bluetooth is available (false for home version)
														// and false for simulator

	public  static final boolean    USE_SIM_BLUE = true;    // Set to true if using simulator Bluetooth
	public  static final int 		CAMERA_WIDTH = 640;
	public  static final int 		CAMERA_HEIGHT = 480;

	public  static final float 		VERSION = 4.0f;	// Current Version
	public  static final int 		YEAR = 2011;		// Year of Current Version

    public  static final int 		MIN_WIDTH  = CAMERA_WIDTH + 69;  // Offset the size of the window by the image size
    public  static final int 		MIN_HEIGHT = CAMERA_HEIGHT + 73;  // Offset the size of the window by the image size
    private static final int		FPS_TRAIL = 10;						// number of previous fps values to average from

	// Robot Connection Types
	private static final byte		ROBOT_NOT_CONNECTED = 0;
	private static final byte		ROBOT_CONNECTING = 1;
	private static final byte		ROBOT_CONNECTED = 2;


	private static final String 	LOG_FILENAME = "log.txt";
	//public static final String 	INSTALL_DIRECTORY = "/Users/darrylhill/Documents/honours/RobotTracker_v4.0";	// loaded from config file
	public static final String 		INSTALL_DIRECTORY = System.getProperty("user.dir");
	public static final String      fs = System.getProperty("file.separator");
    // Image icons used in the status bar and menus
    private static final ImageIcon 	STANDBY_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"standby.png");
	private static final ImageIcon 	OFF_ICON = new ImageIcon(INSTALL_DIRECTORY+ fs +"Images"+fs+"off.png");
	private static final ImageIcon 	ON_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"on.png");
	private static final ImageIcon  START_ICON = new ImageIcon(INSTALL_DIRECTORY+ fs +"Images"+fs+"ButtonStart.png");
	private static final ImageIcon  CONNECT_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonConnect.png");
	private static final ImageIcon  STOP_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonStop.png");
	private static final ImageIcon  WAIT_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonWait.png");
	private static final ImageIcon	CAMERA_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonCamera.png");
	private static final ImageIcon	MOVIE_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonMovie.png");
	private static final ImageIcon	DRAW_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonDraw.png");
	private static final ImageIcon	UNDO_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonUndo.png");
	private static final ImageIcon	ERASE_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonClear.png");
	private static final ImageIcon	MAP_ICON = new ImageIcon(INSTALL_DIRECTORY + fs +"Images"+fs+"ButtonMap.png");
	private static final Color		GREEN = new Color(0,100,0);
	private static final Color		RED = new Color(200,0,0);
	private static final Color		GRAY = new Color(100,100,100);


	// Used on the bottom panel to display information
    private JLabel 				statusBar, robotStatus, plannerStatus, locationFieldX, locationFieldY, robotStatusIcon, plannerStatusIcon;
    private	JButton  			startButton;
    public  TrackingPanel 			trackingPanel;


    // Dialog boxes used in this application
    public  DebugDialog 			debugDialog;
    private LogDialog				logDialog;
    public	CameraDialog			cameraDialog;
    private ResultsDialog			resultsDialog;
    private NetworkDialog			networkDialog;
   // private ScanDialog				scanDialog;
	private MapDialog				mapDialog;
	private ServoCalibrationDialog	servoDialog;
	private RobotPositionDialog	robotPositionDialog;
	private AddObstacleDialog		addObstacleDialog;
	//private RemoveObstacleDialog	removeObstacleDialog;
	//private RobotSpeedDialog 		robotSpeedDialog;



	// Snapshot and Movie Clip variables
 	private Image 				photo;							// tracker image
	private int 				snapshotNumber = 0;				// the next manual snapshot number ... increases each time
	private javax.swing.Timer 	snapTimer;						// timer fortaking snapshots
	private long 				prevTime = new Date().getTime();// used to keep track of previous time
	private int[] 				fpsTimes;						// used to keep track of average frames per second
	private int 				fpsCount;						// used to keep track of index into fpsTimes circular array
    private int 				snapshotCount = 0;				// number of snapshots taken
    private boolean 			recordSnapshots = false;		// True when recording snapshots (for movies)
    private long 				start;							// start time for taking snapshots
    //private int prevSnapshotCount = 0;


	// Used for tracking
    public  int 			frameRate = 4;  					// will be loaded from config file
    //temporarily deprecated. Someday may have a function
	//public  TrackerServer	trackerServer;						// The process that allows all applications to obtain tracking data
 	public  boolean			combineMultipleTrackers = false;	// will be loaded from config file
 	public  int 			stationID = 0;   					// will be loaded from config file
 	public  String[]		stationAddresses;   				// will be loaded from config file
 	public  boolean 		tracking = false;					// True if currently tracking robots
 	public  String			workingDirectory = ".";				// Folder that contains planner, snapshots, traces, etc..
	//private Pose[] 		otherPosesFound = new Pose[8];		// Stores the other trackers' poses


 	// Used for bluetooth communications
    private	int							bluetoothPort = 1;			// The com port that contains the bluetooth device
 	private TreeMap<Integer, String>	robotAddresses;				// The bluetooth addresses of the robots
	public  RBCBlue						bluetoothCoordinator;		// The coordinator for the bluetooth server that communicates between the robot and the PC
	public  EasyBluetoothSim			easyBlueSim;				// bluetooth sim that talks directly to components
	public  RBCPlannerHandler 			plannerHandler;				// handles running planners
	private File						lastPlannerFile;			// The last planner that was loaded
	private boolean 					firstTimeWriting = true;	// Whether the output file has been written to before
	private boolean	 			enableRobotCommunication = false;	// Whether or not the program will communicate with the robot
	private int							robotConnectionMode = ROBOT_NOT_CONNECTED;	// status of robot connection

	// Used for Mapping
	public  boolean			mapping;				// tru iof showing map, false otherwise
	public  int				errorSetting;			// error type (raw, error, angle error, distance error, full Gaussian
	public  int 			resolution;				// sigma error (x1, x2,x3, x5, or x10)
    public  DataSet[]		dataSets;				// sonar, dirrs and side IR
    private MapRenderer 	renderer;				// the map-rendering object
    private Robot 			robot;					// the simulated robot
    
    //used for almost nothing
    private IDE ide;

    public IDE getIDE() {
		return ide;
	}

	public void setIDE(IDE ide) {
		this.ide = ide;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public RobotTracker(String frameTitle, IDE ide) {
        super(frameTitle);
        this.ide = ide;
        this.robot = ide.getRobot();
        // Try to "connect to" and "set up" the webcam
    /*    if (USE_WEB_CAM){
        	webcamManager = new TrackerCanvas(robot);
        }*/

		// Keep track of frames per second
		fpsCount = 0;
		fpsTimes = new int[FPS_TRAIL];

		// Map-related settings
		mapping = !USE_WEB_CAM;
		errorSetting = 0;
		resolution = 0;
		dataSets = new DataSet[0];	// No data yet, must be loaded

        // Set-up bottom panel
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.white);
        bottom.setPreferredSize(new Dimension(MIN_WIDTH,20));
        bottom.setLayout(null);
        if (USE_WEB_CAM)
        	getContentPane().add(bottom, BorderLayout.SOUTH);

        statusBar = new JLabel("Initializing ...");
        //statusBar.setBorder(new LineBorder(Color.black, 1));
        statusBar.setBackground(Color.white);
        statusBar.setLocation(2,0);
        statusBar.setSize(403, 20);
        bottom.add(statusBar);

        robotStatus = new JLabel("Robot Not Connected", OFF_ICON, SwingConstants.RIGHT);
        robotStatus.setForeground(RED);
        robotStatus.setBackground(Color.white);
        robotStatus.setHorizontalAlignment(SwingConstants.CENTER);
        robotStatus.setLocation(404,0);
        robotStatus.setSize(150, 20);
        bottom.add(robotStatus);

        plannerStatus = new JLabel("Planner Not Loaded", OFF_ICON, SwingConstants.RIGHT);
        plannerStatus.setForeground(RED);
        plannerStatus.setBackground(Color.white);
        plannerStatus.setHorizontalAlignment(SwingConstants.CENTER);
        plannerStatus.setLocation(553,0);
        plannerStatus.setSize(150, 20);
        bottom.add(plannerStatus);
        plannerStatus.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		handleLoadPlanner();
    		}
    	});

        // Set-up tracking panel
        trackingPanel = new TrackingPanel(this);
        //TODO
        //trackingPanel.add(webcamManager);
        getContentPane().add(trackingPanel, BorderLayout.CENTER);

        // Load up the configuaration file
        loadConfigFile();

        // Setup the Menubar and Toolbar
        setupMenuBar();
        setupToolBar();

        // Adjust the status bar
        if (!USE_BLUETOOTH)
        	enableRobotCommunication = false;

        if (enableRobotCommunication)
        	showRobotDisconnected();
        else
        	showRobotDisabled();

 		// Event Handler for Window Closing
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		closeTracker();
    		}
    	});

        // Event handler for triggering a single tracking based on a clock at the specified frame rate
        snapTimer = new javax.swing.Timer(1000/frameRate, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		handleTimerTick();
        	}
        });

        setSize(MIN_WIDTH, MIN_HEIGHT);
        setResizable(false);


        // Build the dialog boxes, but do not show them yet
        servoDialog = new ServoCalibrationDialog(this);
        debugDialog = new DebugDialog(this);
        logDialog = new LogDialog(this);
			//scanDialog = new ScanDialog(this, robotAddresses);
        
        if (USE_WEB_CAM) {
        	cameraDialog = new CameraDialog(this);
        	resultsDialog = new ResultsDialog(this, trackingPanel);
        	networkDialog = new NetworkDialog(this);
        }
		mapDialog = new MapDialog(this);
		if (!USE_WEB_CAM) {
			setSize(MIN_WIDTH+TrackingPanel.MAP_BORDER_SIZE*2, MIN_HEIGHT+TrackingPanel.MAP_BORDER_SIZE*2);
			mapDialog.setVisible(true);
		}
		renderer = new MapRenderer(this);

        // Now create and start the tracker server
        /*if (USE_WEB_CAM) {
        	trackerServer = new TrackerServer(this, stationID, stationAddresses);
	        if (trackerServer.online) {
	        	updateStatusBar("Server online ...", Color.black);
	        	System.out.println("Tracker Server online ...");
	        	trackerServer.start();
	        	trackerServer.multiTrack = combineMultipleTrackers;
	        }
	        else {
	        	updateStatusBar("Cannot start server...", RED);
	        	System.out.println("ERROR: Cannot Start Tracker Server");
	        }
        }*/

        // Now create the bluetooth communications server
		if (USE_BLUETOOTH)
			bluetoothCoordinator = new RBCBlue(this);
		else{
			easyBlueSim = new EasyBluetoothSim(this);
			getRobot().setBlue(easyBlueSim);
		}
		
		setVisible(true);
    }

 	// Set up the toolbar to appear on the left side of the window
	private void setupToolBar() {
		JToolBar toolbar;
		JButton  snapshotButton, movieButton, plotToggleButton, mapToggleButton, plotUndoButton, plotClearDesiredButton;

		// Create a vertical toolbar
        toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setFloatable(false);

		// Create a panel to hold the toolbar
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,0,0));
        getContentPane().add(panel, java.awt.BorderLayout.WEST);
        panel.add(toolbar);

        // Create the toolbar with all the necessary buttons
		if (USE_WEB_CAM){
        	toolbar.add(new JLabel("  Track"));
	        if (enableRobotCommunication)
	        	toolbar.add(startButton = new JButton(CONNECT_ICON));
	        else
	        	toolbar.add(startButton = new JButton(START_ICON));
	        	startButton.setBackground(Color.white);
	        	startButton.setToolTipText("Begin tracking");
		        startButton.addActionListener(new ActionListener() {
		        	public void actionPerformed (ActionEvent e) {
		        		handleStartStopButtonPress((JButton)e.getSource());
		        	}
		        });
		}

	    toolbar.add(snapshotButton = new JButton(CAMERA_ICON));
        snapshotButton.setBackground(Color.white);
        snapshotButton.setToolTipText("Take a screenshot");
	    snapshotButton.addActionListener(new ActionListener() {
	        public void actionPerformed (ActionEvent e) {
	        	handleSnapshotButtonPress();
	        }
	    });

		if (USE_WEB_CAM){
	        toolbar.add(movieButton  = new JButton(MOVIE_ICON));
	        	movieButton.setBackground(Color.white);
	        	movieButton.setToolTipText("Toggle movie recording on/off");
		        movieButton.addActionListener(new ActionListener() {
		        	public void actionPerformed (ActionEvent e) {
		        		handleMovieButtonPress((JButton)e.getSource());
		        	}
		        });
		}

		if (USE_WEB_CAM){
	        toolbar.addSeparator();
	        toolbar.add(new JSeparator());
	        toolbar.add(new JLabel("   Plot"));
	        toolbar.add(plotToggleButton = new JButton(DRAW_ICON));
	        	plotToggleButton.setBackground(Color.white);
	        	plotToggleButton.setToolTipText("Begin plotting.");
		        plotToggleButton.addActionListener(new ActionListener() {
		        	public void actionPerformed (ActionEvent e) {
		        		handlePlotToggleButtonPress((JButton)e.getSource());
		        	}
		        });

	        toolbar.add(plotUndoButton = new JButton(UNDO_ICON));
	        	plotUndoButton.setBackground(Color.white);
	        	plotUndoButton.setToolTipText("Undo last point.");
		        plotUndoButton.addActionListener(new ActionListener() {
		        	public void actionPerformed (ActionEvent e) {
		        		handlePlotUndoButtonPress();
		        	}
		        });

	        toolbar.add(plotClearDesiredButton = new JButton(ERASE_ICON));
	        	plotClearDesiredButton.setBackground(Color.white);
	        	plotClearDesiredButton.setToolTipText("Clear Desired Path");
		        plotClearDesiredButton.addActionListener(new ActionListener() {
		        	public void actionPerformed (ActionEvent e) {
		        		handlePlotClearButtonPress();
		        	}
		        });
	        toolbar.addSeparator();
	        toolbar.add(new JSeparator());
	        toolbar.add(new JLabel("    Map"));
	        toolbar.add(mapToggleButton = new JButton(MAP_ICON));
	        mapToggleButton.setBackground(Color.white);
	        mapToggleButton.setToolTipText("Show Map");
		    mapToggleButton.addActionListener(new ActionListener() {
		        public void actionPerformed (ActionEvent e) {
		        	handleMapToggleButtonPress();
		        }
		    });
		}

        toolbar.addSeparator();
        toolbar.add(new JSeparator());
        //toolbar.add(new JLabel(" Mouse"));
	    toolbar.add(locationFieldX = new JLabel("   x: 0"));
	    toolbar.add(locationFieldY = new JLabel("   y: 0"));

    }

    // Update the status bar with the given text
    public void updateStatusBar(String s, Color c) {
    	statusBar.setText(s);
    	statusBar.setForeground(c);
    }

 	// Set up the Menu bar with all its menu items and listeners
	private void setupMenuBar() {
		JMenuBar 				menuBar;
		JMenu 					menu, traceMenu;
		JMenuItem 				menuItem;
		JCheckBoxMenuItem 		checkMenuItem;
		JRadioButtonMenuItem[]	trackIDMenuItems;

		// Create the menu bar
		this.setJMenuBar(menuBar = new JMenuBar());

		// Setup View menu
		if (USE_WEB_CAM) {
			menuBar.add(menu = new JMenu("View"));
			menu.add(checkMenuItem = new JCheckBoxMenuItem("Show Actual Path"));
			checkMenuItem.setSelected(trackingPanel.showActualPath);
			checkMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					handleViewShowActualPathMenuItem((JCheckBoxMenuItem)e.getSource());
				}
			});

			menu.add(checkMenuItem = new JCheckBoxMenuItem("Show Estimated Path"));
			checkMenuItem.setSelected(trackingPanel.showEstimatedPath);
			checkMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					handleViewShowEstimatePathMenuItem((JCheckBoxMenuItem)e.getSource());
				}
			});

			menu.add(checkMenuItem = new JCheckBoxMenuItem("Show User-Plotted Path"));
			checkMenuItem.setSelected(trackingPanel.showDesiredPath);
			checkMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					handleViewShowUserPathMenuItem((JCheckBoxMenuItem)e.getSource());
				}
			});

			menu.add(checkMenuItem = new JCheckBoxMenuItem("Show Robot Tags"));
			checkMenuItem.setSelected(trackingPanel.showTags);
			checkMenuItem.addActionListener(new ActionListener() {
        		public void actionPerformed (ActionEvent event) {
        			handleViewShowRobotTagsMenuItem();
				}
			});

			menu.add(new JSeparator());

			menu.add(menuItem = new JMenuItem("Set Actual Path Color ..."));
			menuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Color newColor = JColorChooser.showDialog(null, "Set Plot Color for Actual Robot Path", trackingPanel.actualPathColor);
					if (newColor != null)
						trackingPanel.actualPathColor = newColor;
					trackingPanel.update();
				}
			});
			menu.add(menuItem = new JMenuItem("Set User-Plotted Path Color ..."));
			menuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Color newColor = JColorChooser.showDialog(null, "Set Plot Color for User-Plotted Path", trackingPanel.desiredPathColor);
					if (newColor != null)
						trackingPanel.desiredPathColor = newColor;
					trackingPanel.update();
				}
			});
			menu.add(menuItem = new JMenuItem("Set Estimated Path Color ..."));
			menuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Color newColor = JColorChooser.showDialog(null, "Set Plot Color for Estimated Robot Path", trackingPanel.estimatePathColor);
					if (newColor != null)
						trackingPanel.estimatePathColor = newColor;
					trackingPanel.update();
				}
			});
			menu.add(menuItem = new JMenuItem("Set Robot Tag Color ..."));
			menuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Color newColor = JColorChooser.showDialog(null, "Set Color for Robot Tags", trackingPanel.tagColor);
					if (newColor != null)
						trackingPanel.tagColor = newColor;
						trackingPanel.tagColorIsDark = (((newColor.getRed() * 299) + (newColor.getGreen() * 587) + (newColor.getBlue() * 114)) / 1000) < 125;
					trackingPanel.update();
				}
			});
			menu.add(new JSeparator());

			menu.add(checkMenuItem = new JCheckBoxMenuItem("Hide Image"));
			checkMenuItem.setSelected(trackingPanel.hideImage);
			checkMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					handleViewHideImageMenuItem();
				}
			});
		}
		else
			trackingPanel.hideImage = true;

		// Setup Settings menu
		if (USE_WEB_CAM) {
			menuBar.add(menu = new JMenu("Settings"));/*
			menu.add(menuItem = new JMenuItem("Bluetooth Settings ..."));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleSettingsScanMenuItem();
    			}
			});
			menu.add(menuItem = new JMenuItem("Network Settings ..."));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleSettingsNetworkMenuItem();
    			}
			});*/
			menu.add(menuItem = new JMenuItem("Camera Settings ..."));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleSettingsCameraMenuItem();
				}
			});
			/*
			menu.add(new JSeparator());

			menu.add(menuItem = new JMenuItem("Servo Calibration ..."));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleSettingsServoCalibrationMenuItem();
				}
			});*/
			menu.add(new JSeparator());

			menu.add(menuItem = new JMenuItem("Set Working Directory ..."));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleSettingsWorkingDirectoryMenuItem();
				}
			});

			menu.add(traceMenu = new JMenu("Traced Robot ID"));
				trackIDMenuItems = new JRadioButtonMenuItem[8];
				ButtonGroup group = new ButtonGroup();
				for (int i=0; i<8; i++) {
					trackIDMenuItems[i] = new JRadioButtonMenuItem("" + i, new ImageIcon(INSTALL_DIRECTORY + fs+"Images"+fs+"ID"+i+".png"));
					if (trackingPanel.trackID == i)
						trackIDMenuItems[i].setSelected(true);
					group.add(trackIDMenuItems[i]);
					traceMenu.add(trackIDMenuItems[i]);
					trackIDMenuItems[i].addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							handleRobotIDMenuItem(Integer.parseInt(e.getActionCommand()));
						}
					});
				}
				/*
			menu.add(new JSeparator());

			menu.add(checkMenuItem = new JCheckBoxMenuItem("Enable Robot Communication"));
			checkMenuItem.setSelected(enableRobotCommunication);
			checkMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					handleSettingsEnableComMenuItem((JCheckBoxMenuItem)e.getSource());
				}
			});*/
		}

  		// Setup Debug menu
		//if (USE_BLUETOOTH) {
		//USE_BLUETOOTH means something different for the simulator.
		//we want these menus no matter what
			menuBar.add(menu = new JMenu("Debugging"));
			menu.add(menuItem = new JMenuItem("Results Summary"));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
        			handleViewResultsMenuItem();
    			}
			});
			menu.add(new JSeparator());
			menu.add(menuItem = new JMenuItem("Debug Output"));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleDebugOutputMenuItem();
    			}
			});
			menu.add(menuItem = new JMenuItem("Log Output"));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleLogOutputMenuItem();
    			}
			});
			menu.add(menuItem = new JMenuItem("Camera Output"));
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					handleCameraOutputMenuItem();
    			}
			});
		//}
		menuBar.add(menu = new JMenu("Robot"));
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Show IRSensors"));
		checkMenuItem.setSelected(getRobot().showIRSensors);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewShowIRSensors((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Show DirrsSensor"));
		checkMenuItem.setSelected(getRobot().showDirrs);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewShowDirrsSensor((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Show Sonar"));
		checkMenuItem.setSelected(getRobot().showSonar);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewShowSonar((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Show Camera"));
		checkMenuItem.setSelected(getRobot().showCamera);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewShowCamera((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(traceMenu = new JMenu("Set Robot ID"));
		trackIDMenuItems = new JRadioButtonMenuItem[8];
		ButtonGroup group = new ButtonGroup();
		for (int i=0; i<8; i++) {
			trackIDMenuItems[i] = new JRadioButtonMenuItem("" + i, new ImageIcon(INSTALL_DIRECTORY + "Images\\ID"+i+".png"));
			if (getRobot().getId() == i)
				trackIDMenuItems[i].setSelected(true);
			group.add(trackIDMenuItems[i]);
			traceMenu.add(trackIDMenuItems[i]);
			trackIDMenuItems[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					handleViewChangeRobotID(Integer.parseInt(e.getActionCommand()));
				}
			});
		}
		menu.add(menuItem = new JMenuItem("Robot start position"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewRobotStartPosition();
				trackingPanel.update();
				saveConfigFile();
			}
		});
		menu.add(menuItem = new JMenuItem("Robot current position"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewRobotPosition();
				trackingPanel.update();
			}
		});
		menu.add(menuItem = new JMenuItem("Robot speed"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewRobotSpeed();
				trackingPanel.update();
			}
		});
		menuBar.add(menu = new JMenu("Dipswitches"));
		menu.add(checkMenuItem = new JCheckBoxMenuItem("IRSensors(All)"));
		checkMenuItem.setSelected(getRobot().IRSensors);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewToggleIRSensors((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Dirrs"));
		checkMenuItem.setSelected(getRobot().dirrs);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewToggleDirrs((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Sonar"));
		checkMenuItem.setSelected(getRobot().sonar);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewToggleSonar((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Camera"));
		checkMenuItem.setSelected(getRobot().camera);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewToggleCamera((JCheckBoxMenuItem)e.getSource());
			}
		});
		menu.add(checkMenuItem = new JCheckBoxMenuItem("Encoders"));
		checkMenuItem.setSelected(getRobot().encoders);
		checkMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewToggleEncoders((JCheckBoxMenuItem)e.getSource());
			}
		});
		JMenu menu2;
		menuBar.add(menu = new JMenu("Environment"));
		menu.add(menu2 = new JMenu("Add item"));
		menu2.add(menuItem = new JMenuItem("Flowerpot"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleAddObstacle("Flowerpot", AddObstacleDialog.FLOWERPOT);
				trackingPanel.update();
			}
		});
		menu2.add(menuItem = new JMenuItem("Block"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleAddObstacle("Block", AddObstacleDialog.BLOCK);
				trackingPanel.update();
			}
		});
		menu2.add(menuItem = new JMenuItem("Wall"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleAddWall();
				trackingPanel.update();
			}
		});
		menu.add(menuItem = new JMenuItem("Remove item"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				handleViewRemoveItem();
			}
		});
		menu.add(menuItem = new JMenuItem("Reset environment"));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				robot.getEnvironment().setDefaultObstacles();
				trackingPanel.processImage();
			}
		});
		trackingPanel.processImage();
   }

	// These are the listeners for the menu items created in the setupMenuBar method
	/* I disabled a lot of functionality for the simulator, but I am reluctant to 
	 * get rid of it as it may be useful down the road
	 */
	private void handleViewRemoveItem(){
		new RemoveObstacleDialog(this);
	}
	private void handleViewToggleCamera(JCheckBoxMenuItem aCheckBox){
		getRobot().camera = aCheckBox.isSelected();
		saveConfigFile();
	}
	private void handleViewToggleDirrs(JCheckBoxMenuItem aCheckBox){
		getRobot().dirrs = aCheckBox.isSelected();
		saveConfigFile();
	}
	private void handleViewToggleEncoders(JCheckBoxMenuItem aCheckBox){
		getRobot().encoders = aCheckBox.isSelected();
		saveConfigFile();
	}
	private void handleViewToggleSonar(JCheckBoxMenuItem aCheckBox){
		getRobot().sonar = aCheckBox.isSelected();
		saveConfigFile();
	}
	private void handleViewShowCamera(JCheckBoxMenuItem aCheckBox){
		getRobot().showCamera = aCheckBox.isSelected();
		saveConfigFile();
	}
	private void handleViewShowDirrsSensor(JCheckBoxMenuItem aCheckBox){
		getRobot().showDirrs = aCheckBox.isSelected();
		saveConfigFile();
	}
	
	private void handleViewShowSonar(JCheckBoxMenuItem aCheckBox){
		getRobot().showSonar = aCheckBox.isSelected();
		saveConfigFile();
	}
	
	private void handleViewShowIRSensors(JCheckBoxMenuItem aCheckBox){
		getRobot().showIRSensors = aCheckBox.isSelected();
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleAddWall(){
		new AddWallDialog(this);
	}
	private void handleAddObstacle(String title, int type){
		addObstacleDialog = new AddObstacleDialog(this, title, type);
	}
	private void handleViewRobotPosition(){
		robotPositionDialog = new RobotPositionDialog(this,false);
	}
	private void handleViewRobotStartPosition(){
		//this dialog handles setting the robot start position and
		//the robot current position, so the second boolean, if true,
		//means we are setting the starting position
		robotPositionDialog = new RobotPositionDialog(this,true);
	}
	private void handleViewRobotSpeed(){
		new RobotSpeedDialog(this);
	}
	private void handleFileExitMenuItem() {
		closeTracker();
	}
	
	private void handleViewToggleIRSensors(JCheckBoxMenuItem aCheckBox){
		getRobot().IRSensors = aCheckBox.isSelected();
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleViewChangeRobotID(int id){
		getRobot().setId(id);
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleViewShowActualPathMenuItem(JCheckBoxMenuItem aCheckbox) {
		trackingPanel.showActualPath = aCheckbox.isSelected();
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleViewShowEstimatePathMenuItem(JCheckBoxMenuItem aCheckbox) {
		trackingPanel.showEstimatedPath = aCheckbox.isSelected();
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleViewShowUserPathMenuItem(JCheckBoxMenuItem aCheckbox) {
		trackingPanel.showDesiredPath = aCheckbox.isSelected();
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleViewShowRobotTagsMenuItem() {
		trackingPanel.showTags = !trackingPanel.showTags;
	    trackingPanel.update();
	    saveConfigFile();
	}
	private void handleViewHideImageMenuItem() {
		trackingPanel.hideImage = !trackingPanel.hideImage;
	    trackingPanel.update();
	}
	//TODO have to root through this and find what is useful
	private void handleSettingsCameraMenuItem() {
		trackingPanel.calibrating = true;
		trackingPanel.update();
		new CalibrationDialog(null, "Camera Settings and Calibration", true, trackingPanel).setVisible(true);
		trackingPanel.calibrating = false;
		trackingPanel.update();
		saveConfigFile();
	}
	private void handleSettingsWorkingDirectoryMenuItem() {
		JFileChooser chooser = new JFileChooser(new File(workingDirectory));
		chooser.setDialogTitle("Choose a working directory (i.e. folder)");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showDialog(this, "Select");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File aFolder = chooser.getSelectedFile();
			if (aFolder.isDirectory()) {
				workingDirectory = aFolder.getAbsolutePath();
				saveConfigFile();
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid path ... working directory not set ", "Error Setting Working Directory", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void handleSettingsServoCalibrationMenuItem() {
		//servoDialog.setVisible(true);
	}
	private void handleSettingsNetworkMenuItem() {
		//networkDialog.setVisible(true);
		//trackerServer.multiTrack = combineMultipleTrackers;
		//saveConfigFile();
	}
	private void handleSettingsScanMenuItem() {
		//scanDialog.setVisible(true);
	}
	private void handleSettingsEnableComMenuItem(JCheckBoxMenuItem aCheckbox) {
		aCheckbox.setSelected(false);
		/*enableRobotCommunication = aCheckbox.isSelected();
		if (enableRobotCommunication) {
			showRobotDisconnected();
		}
		else {
			startButton.setIcon(START_ICON);
        	startButton.setToolTipText("Start Tracking");
			showRobotDisabled();
		}
		saveConfigFile();*/
	}
	private void handleRobotIDMenuItem(int index) {
		trackingPanel.changeTrackID(index);
		saveConfigFile();
	}
	private void handleViewResultsMenuItem() {
		resultsDialog.setVisible(true);
	}
	private void handleDebugInputMenuItem() {
		String input = JOptionPane.showInputDialog(this, "Input text to send to robot");
		System.out.println(input.getBytes().length);
		sendDebugData(input.getBytes());
	}
	private void handleDebugOutputMenuItem() {
		debugDialog.setVisible(true);
	}
	private void handleLogOutputMenuItem() {
		logDialog.setVisible(true);
	}
	private void handleCameraOutputMenuItem() {
		cameraDialog.setVisible(true);
	}

	// This is the listener for clicking on the planner status button to load a planner
	@SuppressWarnings("deprecation")
	private void handleLoadPlanner() {
		if (plannerHandler == null) {
			// First, get the name of the file
			JFileChooser chooser = new JFileChooser(new File(workingDirectory));
			chooser.setDialogTitle("Choose a Pre-compiled Planner Class File");
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new ClassFileFilter());
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File plannerFile = chooser.getSelectedFile();

				// Now load the planner into the planner handler
				try {
					plannerHandler = new RBCPlannerHandler(this, plannerFile.getParentFile().toURL(), plannerFile.getName().split("\\.")[0]);
					lastPlannerFile = plannerFile;	// Remember the file so that it can be re-loaded later
					showPlannerLoaded();
					updateStatusBar("Planner Loaded (" + plannerFile.getName() + ")", Color.black);
				} catch (Exception e) {
					plannerStatus.setIcon(OFF_ICON);
					plannerStatus.setForeground(RED);
					JOptionPane.showMessageDialog(null, "ERROR: Unable to load planner from " + plannerFile.getAbsolutePath()+".", "Error Opening Planner", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
			unloadPlanner();
	}

	// Unload the planner
	public void unloadPlanner() {
		plannerHandler.interrupt();
		plannerHandler = null;
		showPlannerUnloaded();
	}

	// Show that the tracking is happenning
	private void startTracking() {
		
		if (USE_BLUETOOTH){
			Debug.debug("Checking for bluetooth thread state");
			if (bluetoothCoordinator.getState() == Thread.State.NEW){
				Debug.debug("Starting bluetooth");
				bluetoothCoordinator.start();
			}
		}
		
		if (!getRobot().isRunning()){
			Thread robot = new Thread(getRobot());
			robot.start();
		}
		Debug.debug("START TRACKING$$$$$$$$$$$$$$$$$$$");
		//TODO
		//servoDialog.resetValues();	// reset the servo values to defaults so that servos do not jerk from last bad values
		eraseMap();
		Debug.debug("Changing the ICON to STOP");
		startButton.setIcon(STOP_ICON);
        startButton.setToolTipText("Stop tracking");
	    tracking = true;
	    snapTimer.start();
		updateStatusBar("Tracking Started ", Color.black);
		// Start the planner if there is one
		startupPlanner();
	}

	// Show that the tracking is not happenning
	private void stopTracking() {
		Debug.debug("STOP TRACKING$$$$$$$$$$$$$$$$$$$");
		getRobot().setRunning(false);
		tracking = false;
        stopPlanner();
	    haltTheTimer();
	    
	    if (USE_BLUETOOTH){
	    	bluetoothCoordinator.interrupt();
	    	bluetoothCoordinator = new RBCBlue(this);
	    }else{
	    	easyBlueSim = new EasyBluetoothSim(this);
			getRobot().setBlue(easyBlueSim);
	    }

	    Debug.debug("setting the ICON to START");
	    startButton.setIcon(START_ICON);
	    startButton.setToolTipText("Start tracking");

	}

	// Adjust what is needed when the robot is connected
	public void showRobotConnected() {
		robotStatus.setIcon(ON_ICON);
		robotStatus.setText("Robot Connected");
		robotStatus.setForeground(GREEN);
		robotConnectionMode = ROBOT_CONNECTED;
		
		//showTrackingWait();
	}

	// Adjust what is needed when the robot communications are disable
	public void showRobotDisabled() {
		robotStatus.setIcon(STANDBY_ICON);
		robotStatus.setText("Robot Com. Disabled");
		robotStatus.setForeground(GRAY);
		robotConnectionMode = ROBOT_NOT_CONNECTED;
	}

	// Adjust what is needed when the robot is being connected
	public void showRobotConnecting() {
		robotStatus.setIcon(STANDBY_ICON);
		robotStatus.setText("Connecting Robot ...");
		robotStatus.setForeground(GRAY);
		robotConnectionMode = ROBOT_CONNECTING;
        startButton.setIcon(WAIT_ICON);
        startButton.setToolTipText("Please Wait");
		updateStatusBar("Attempting to connect to robot " + trackingPanel.trackID + " ...", Color.black);
	}

	// Adjust what is needed when the robot is disconnected
	public void showRobotDisconnected() {
		robotStatus.setIcon(OFF_ICON);
		robotStatus.setText("Robot Not Connected");
		robotStatus.setForeground(RED);
		robotConnectionMode = ROBOT_NOT_CONNECTED;
		stopTracking();
	}

	// Adjust what is needed when the planner is loaded
	public void showPlannerLoaded() {
		plannerStatus.setIcon(ON_ICON);
		plannerStatus.setText("Planner Loaded");
		plannerStatus.setForeground(GREEN);
	}

	// Adjust what is needed when the planner is running
	public void showPlannerRunning() {
		plannerStatus.setIcon(ON_ICON);
		plannerStatus.setText("Planner Running");
		plannerStatus.setForeground(GREEN);
	}

	// Adjust what is needed when the planner is loaded and stopped
	public void showPlannerStopped() {
		plannerStatus.setIcon(ON_ICON);
		plannerStatus.setText("Planner Stopped");
		plannerStatus.setForeground(GREEN);
	}

	// Adjust what is needed when the planner is unloaded
	public void showPlannerUnloaded() {
		plannerStatus.setIcon(OFF_ICON);
		plannerStatus.setText("Planner Not Loaded");
		plannerStatus.setForeground(RED);
		updateStatusBar("Planner Unloaded", Color.black);
	}
	
	private BufferedImage canvasToImage(Canvas cnvs)
	{
	   int w = cnvs.getWidth();
	   int h = cnvs.getHeight();
	   int type = BufferedImage.TYPE_INT_RGB;
	   BufferedImage image = new BufferedImage(w,h,type);
	   Graphics2D g2 = image.createGraphics();
	   cnvs.paint(g2);
	   g2.dispose();
	   return image;
	 }//canvasToImage


	// Event handler for when the start/stop button is pressed ... start/stop tracking.
    private void handleStartStopButtonPress(JButton startButton) {
        if (!tracking) {
        	if (robotConnectionMode == ROBOT_NOT_CONNECTED) {
        		Debug.debug("START/STOP BUTTON robot not connected");
	        	// Clear previous actual/estimated paths
		    	trackingPanel.clearActualPath();
		        trackingPanel.clearEstimatedPath();

				// Save the latest snapshot to a file upon starting
				if (USE_WEB_CAM) {
			    	Image photo = null;
			    	try {
			        	photo = trackingPanel.getImage();
			        	storeSnapshot(photo, INSTALL_DIRECTORY + fs +"TrackerImage.png");
			    	}
			    	catch (Exception e) {
            			updateStatusBar(statusBar.getText() + "Error: Cannot read image from camera", Color.red);
			    	}
				}
				else {
					try {
						photo = ImageIO.read(new File(INSTALL_DIRECTORY + fs +"TrackerImage.png"));
					} catch (Exception e) {	}
				}
		        startTracking();
        	}
	    	else if (robotConnectionMode == ROBOT_CONNECTED) {
        		Debug.debug("START/STOP BUTTON robot connected");
        		startTracking();
	    	}
        }
        else {
			updateStatusBar("Tracking stopped", Color.black);
        	stopTracking();

        }
	}

	/*** BLUETOOTH COMMUNICATIONS METHODS ***/

	// Reset the bluetooth server
	public void resetBluetooth() {
		bluetoothCoordinator.interrupt();
		bluetoothCoordinator = new RBCBlue(this);
		showRobotDisconnected();
	}

	// Send data through the bluetooth server
	public void sendData(byte[] data) {
		easyBlueSim.sendData(data);
	}

	// Send debug data through the bluetooth server
	public void sendDebugData(byte[] data) {
		easyBlueSim.sendDebugData(data);
	}

	// Get the planner started, return true if planner was started
	private boolean startupPlanner() {
		if (plannerHandler != null) {
			plannerHandler.start();
			showPlannerRunning();
			return true;
		}
		return false;
	}

	// Stop the planner, return true if planner was started
	@SuppressWarnings("deprecation")
	private boolean stopPlanner() {
		if (plannerHandler != null) {
			plannerHandler.interrupt();
			plannerHandler = null;
			showPlannerStopped();
			try {
				plannerHandler = new RBCPlannerHandler(this, lastPlannerFile.getParentFile().toURL(), lastPlannerFile.getName().split("\\.")[0]);
			} catch (Exception e) {
				System.out.println("Error: Cannot reload planner");
				return false;
			}
			return true;
		}
		return false;
	}



	// Event handler for when the snapshot button is pressed ... bring up a dialog box
	private void handleSnapshotButtonPress() {
		if (!mapping && photo == null) {
			updateStatusBar("Error: No image available ... snapshot not saved", Color.red.darker());
			return;
		}
		FileDialog saveDialog = new FileDialog(this, "Save PNG", FileDialog.SAVE);
		saveDialog.setDirectory(workingDirectory);
		saveDialog.setFile("Snapshot" + snapshotNumber + ".png");
		saveDialog.setVisible(true);

     	String directory = saveDialog.getDirectory();
        String filename = saveDialog.getFile();

		if (filename == null)
			updateStatusBar("Error: cannot find working directory \"" + workingDirectory + "\"", Color.red.darker());
        else {
        	BufferedImage bi;
        	if (!mapping)
				bi = new BufferedImage(photo.getWidth(null), photo.getHeight(null), BufferedImage.TYPE_INT_RGB);
			else
				bi = new BufferedImage(trackingPanel.mapImage.getWidth(null), trackingPanel.mapImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = bi.createGraphics();
	        //g2.drawImage(canvasToImage(webcamManager),0,0, this);
	        // Decide whether or not to display tags

	        if (mapping) {
	        	// Show a shaded background while creating map
				g2.setColor(Color.LIGHT_GRAY);
				g2.fillRect(0, 0, trackingPanel.mapImage.getWidth(null), trackingPanel.mapImage.getHeight(null));
	        	if (!trackingPanel.hideImage)
	        		g2.drawImage(trackingPanel.currentImage, TrackingPanel.MAP_BORDER_SIZE, TrackingPanel.MAP_BORDER_SIZE, this);
	        	g2.drawImage(trackingPanel.mapImage, 0, 0, this);
	        }
	        else {
	        	if (trackingPanel.showTags)
	        		g2.drawImage((Image)trackingPanel.getModifiedImage(), null, null);
	   			else
	        		g2.drawImage((Image)trackingPanel.getImage(), null, null);
	        }

			// Decide whether or not to display paths
	        if (trackingPanel.showDesiredPath)
				trackingPanel.drawUserDefinedPath(g2);
			if (trackingPanel.showEstimatedPath)
				trackingPanel.drawEstimatePath(g2);
			if(trackingPanel.showActualPath)
				trackingPanel.drawActualPath(g2);

			if (mapping) {
				g2.setColor(Color.BLACK);
        		g2.drawRect(TrackingPanel.MAP_BORDER_SIZE,TrackingPanel.MAP_BORDER_SIZE,640,480);
        		g2.setColor(Color.WHITE);
        		g2.drawRect(TrackingPanel.MAP_BORDER_SIZE-1,TrackingPanel.MAP_BORDER_SIZE-1,642,482);
			}


			// Save it
	        try {
	       		ImageIO.write(bi, "png", new File(directory + filename));
	       		snapshotNumber++;
	        }
	        catch (Exception ex) {
	        	JOptionPane.showMessageDialog(null, "Error saving snapshot", "Error", JOptionPane.ERROR_MESSAGE);
			}
        }
    }

	// Event handler for when the movie button is pressed ... bring up a dialog box
	private void handleMovieButtonPress(JButton movieButton) {
		recordSnapshots = !recordSnapshots;
        if (recordSnapshots) {
        	start = System.currentTimeMillis();
			// Make a Snapshots directory if it is not there
        	if (! new File(INSTALL_DIRECTORY + "/Snapshots").exists())
        		new File(INSTALL_DIRECTORY + "/Snapshots").mkdir();
        	movieButton.setIcon(new ImageIcon(INSTALL_DIRECTORY + fs+"Images"+fs+"ButtonMovieRec.png"));
        }
        else {
        	// Stop recording.  Popup movie config
        	new MovieDialog(this, snapshotCount, frameRate, workingDirectory).setVisible(true);

        	// Clear snapshots directory
        	File snapsDir = new File(INSTALL_DIRECTORY + fs+"Snapshots");
        	if (snapsDir.exists()){
        		File[] files = snapsDir.listFiles();
        		for (int i=0; i<files.length; i++)
    				files[i].delete();
        	}
        	snapshotCount = 0;
        	movieButton.setIcon(new ImageIcon(INSTALL_DIRECTORY + fs+"Images"+fs+"ButtonMovie.png"));
        }
	}

	// Event handler for when the plot undo button is pressed ... start/stop drawing user plotted points
	private void handlePlotToggleButtonPress(JButton plotToggleButton){
		System.out.println("toggling buttons");
	    trackingPanel.plotting = !trackingPanel.plotting;
        if (trackingPanel.plotting) {
	        plotToggleButton.setToolTipText("Stop plotting.");
        	plotToggleButton.setIcon(new ImageIcon(INSTALL_DIRECTORY + fs + "Images"+fs+"ButtonDrawStop.png"));
        }
        else {
	        plotToggleButton.setToolTipText("Start plotting.");
	        plotToggleButton.setIcon(new ImageIcon(INSTALL_DIRECTORY + fs+"Images"+fs+"ButtonDraw.png"));
        }
	}

	// Event handler for when the plot undo button is pressed ... erase last plotted point.
	private void handlePlotUndoButtonPress() {
		trackingPanel.removeLastDesiredPlot();
	}

	// Event handler for when the map button is pressed ... bring up the map dialog box.
	private void handleMapToggleButtonPress() {
		mapping = !mapping;
		if (mapping) {
			robotStatus.setLocation(TrackingPanel.MAP_BORDER_SIZE*2+404,0);
        	plannerStatus.setLocation(TrackingPanel.MAP_BORDER_SIZE*2+553,0);
			setSize(MIN_WIDTH+TrackingPanel.MAP_BORDER_SIZE*2, MIN_HEIGHT+TrackingPanel.MAP_BORDER_SIZE*2);
		}
		else {
			robotStatus.setLocation(404,0);
        	plannerStatus.setLocation(553,0);
			setSize(MIN_WIDTH, MIN_HEIGHT);
		}
		mapDialog.setVisible(mapping);
	}

	// Event handler for when the plot erase button is pressed ... erase all plotted points.
	private void handlePlotClearButtonPress() {
		trackingPanel.clearDesiredPath();
	}

	// Event handler for timer tick running at the current frame rate
	private void handleTimerTick() {
        // Update the status bar to show the frame rate
		long newTime = new Date().getTime();
		fpsTimes[fpsCount] = 1000/(int)(newTime - prevTime);
		fpsCount = (fpsCount + 1) % FPS_TRAIL;
		int frameRate = 0;
		for (int i=0; i<FPS_TRAIL; i++)
			frameRate += fpsTimes[i];
		frameRate = frameRate / FPS_TRAIL;
        if (combineMultipleTrackers)
        	updateStatusBar(String.format("Networked tracking (fps=%02d)", frameRate), Color.black);
        else
        	updateStatusBar(String.format("Tracking (fps=%02d)", frameRate), Color.black);
        prevTime = newTime;  // remember for next timer tick in order to have correct frame rate

        // If recording a movie, display appropriate information in the status bar
    	if (recordSnapshots){
    		long stop = System.currentTimeMillis();
	    	int durationMin = (int)((stop - start)/(60*1000f));
	    	int durationSec = (int)((stop - start)/1000f)%60;

	    	updateStatusBar(statusBar.getText() + " (Recording ... " + String.format("%02d",durationMin) + ":" + String.format("%02d",durationSec) + ")", Color.black);
    	}

		// Get the image from the camera, set it in the tracking panel and
		// store it in the snapshots directory if recording a movie.
        try {
        	/*if (USE_WEB_CAM){
        		//TODO
                System.out.println("***************setting image");

        		photo = canvasToImage(webcamManager);
                System.out.println("***************got image");

        		//photo = new BufferedImage(WIDTH, HEIGHT, 0);
        	}*/
    		photo = createImage(trackingPanel);

            trackingPanel.processImage();	// This will also process the image
            Debug.debug("***************tracker running ***********");
            if (recordSnapshots)
            	storeSnapshot(photo, INSTALL_DIRECTORY + fs+"Snapshots"+fs+"TrackerImage" + (String.format("%05d", snapshotCount++)) + ".png");
        }
        catch (Exception e) {
        	e.printStackTrace();
			updateStatusBar(statusBar.getText() + "Error: Cannot read image from camera", Color.red);
        }

		// Update the results dialog box to show the latest tracking results
		//resultsDialog.update(trackingPanel.getPosesFound(), trackerServer.posesFromServers);
        resultsDialog.update(trackingPanel.getPosesFound());
	}
	
	public BufferedImage createImage(JPanel panel) {

	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR); //BufferedImage.TYPE_INT_RBG
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    return bi;
	}

	// Saves the image as a PNG in file
	private void storeSnapshot(Image photo, String fileName) {
		if (photo == null)
			return;
		BufferedImage bi = new BufferedImage(photo.getWidth(null), photo.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(photo, null, null);
        try {
       		ImageIO.write(bi, "png", new File(fileName));
        }
        catch (Exception ex) {
        	System.out.println("Error: Cannot save TrackerImage.png to file");
		}
	}

	// Close the webcam before quitting
    protected void finalize() throws Throwable {
        if (USE_WEB_CAM)
        	//webcamManager.closePlayer();
        super.finalize();
    }

    // Load configuration file
	private void loadConfigFile() {
		BufferedReader in = null;
		String s = "";
		stationAddresses = new String[3];
		try {
            in = new BufferedReader(new FileReader(INSTALL_DIRECTORY + fs+"config"+fs+"RobotTrackerConfig.txt"));
            s = in.readLine();
            	frameRate = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	combineMultipleTrackers = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	stationID = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	stationAddresses[0] = s.substring(s.indexOf('=')+2).trim();
            s = in.readLine();
            	stationAddresses[1] = s.substring(s.indexOf('=')+2).trim();
            s = in.readLine();
            	stationAddresses[2] = s.substring(s.indexOf('=')+2).trim();
            s = in.readLine();
            	trackingPanel.diff = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.sum = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.showActualPath = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.showDesiredPath = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.showEstimatedPath = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            s = s.substring(s.indexOf('=')+2);
				String[] cols = s.split(",");
            	trackingPanel.actualPathColor = new Color(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
            s = in.readLine();
            s = s.substring(s.indexOf('=')+2);
				cols = s.split(",");
            	trackingPanel.desiredPathColor = new Color(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
            s = in.readLine();
            s = s.substring(s.indexOf('=')+2);
				cols = s.split(",");
            	trackingPanel.estimatePathColor = new Color(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
            s = in.readLine();
            s = s.substring(s.indexOf('=')+2);
				cols = s.split(",");
            	trackingPanel.tagColor = new Color(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), Integer.parseInt(cols[2]));
				trackingPanel.tagColorIsDark = (((trackingPanel.tagColor.getRed() * 299) + (trackingPanel.tagColor.getGreen() * 587) + (trackingPanel.tagColor.getBlue() * 114)) / 1000) < 125;
            s = in.readLine();
            	trackingPanel.showTags = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.trackID = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.xOffset = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	trackingPanel.yOffset = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().showIRSensors = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().showDirrs = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().showSonar = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().showCamera = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().IRSensors = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().dirrs = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().sonar = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().encoders = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().setId(Integer.parseInt(s.substring(s.indexOf('=')+2)));
            s = in.readLine();
            	getRobot().startX = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().startY = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	getRobot().startAngle = Math.toRadians(Integer.parseInt(s.substring(s.indexOf('=')+2)));
           
            s = in.readLine();
             	getRobot().leftGripOpen = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().leftGripClosed = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().rightGripOpen = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().rightGripClosed = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().headPitchCenter = Integer.parseInt(s.substring(s.indexOf('=')+2));
         	s = in.readLine();
             	getRobot().headPitchDownMax = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().headPitchUpMax = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().headYawCenter = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().headYawLeftMax =Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
             	getRobot().headYawRightMax =Integer.parseInt(s.substring(s.indexOf('=')+2));
             
            s = in.readLine();
            	bluetoothPort = Integer.parseInt(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            	workingDirectory = s.substring(s.indexOf('=')+2);
            s = in.readLine();
            	enableRobotCommunication = Boolean.parseBoolean(s.substring(s.indexOf('=')+2));
            s = in.readLine();
            getRobot().setPosition();
			// Read in the Robot ID/BluetoothAddress pairs
            String data = s.substring(s.indexOf('=')+2);
			if (data.startsWith("{") && data.substring(data.length()-1).compareTo("}")==0) {
				data = data.substring(1,data.length()-1);
				data = data.replace("[","");
				String[] pairs = data.split("]");
				robotAddresses = new TreeMap<Integer, String>();
				for (int i=0;i<pairs.length;i++){
					String[] pair = pairs[i].split(",");
					Integer key = new Integer(pair[0]);
					String value = new String(pair[1]);
					robotAddresses.put(key, value);
				}
			}
			//read in the objects in the environment
			s= in.readLine();
			data = s.substring(s.indexOf('=')+2);
			if (data.startsWith("{") && data.substring(data.length()-1).compareTo("}")==0) {
				data = data.substring(1,data.length()-1);
				data = data.replace("[","");
				String[] trips = data.split("]");
				Environment en = robot.getEnvironment();
				en.resetObstacles();
				for (int i=0;i<trips.length;i++){
					String[] trip = trips[i].split(",");
					switch (Integer.parseInt(trip[0])){
					case -1: 	//wall
						en.addObstacle(new Wall(Integer.parseInt(trip[1]),Integer.parseInt(trip[2]),
								Integer.parseInt(trip[3]),Integer.parseInt(trip[4])));
						break;
					case -2:	//flowerpot
						en.addObstacle(new FlowerPot(Integer.parseInt(trip[1]),Integer.parseInt(trip[2])));
						break;
					case -3:	//block
						en.addObstacle(new Block(Integer.parseInt(trip[1]),Integer.parseInt(trip[2])));
						break;
					}
				}
			}
			in.close();
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Problem reading configuration file RobotTrackerConfig.txt", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
        	try { in.close(); } catch (Exception e) {}
        }
	}

	// Save configuration file
 	public void saveConfigFile() {
		try {
            PrintWriter out = new PrintWriter(new FileWriter(INSTALL_DIRECTORY + fs+"config"+fs+"RobotTrackerConfig.txt"));
            out.println("FrameRate           = " + frameRate);
			out.println("UseMultipleTrackers = " + combineMultipleTrackers);
			out.println("StationID           = " + stationID);
			out.println("StationAddress1     = " + stationAddresses[0]);
			out.println("StationAddress2     = " + stationAddresses[1]);
			out.println("StationAddress3     = " + stationAddresses[2]);
			out.println("TrackDifference     = " + trackingPanel.diff);
            out.println("TrackSum            = " + trackingPanel.sum);
			out.println("ShowActualPath      = " + trackingPanel.showActualPath);
            out.println("ShowDesiredPath     = " + trackingPanel.showDesiredPath);
			out.println("ShowEstimatedPath   = " + trackingPanel.showEstimatedPath);
			out.println("ActualPathColor     = " + trackingPanel.actualPathColor.getRed() + "," + trackingPanel.actualPathColor.getGreen() + "," + trackingPanel.actualPathColor.getBlue());
			out.println("DesiredPathColor    = " + trackingPanel.desiredPathColor.getRed() + "," + trackingPanel.desiredPathColor.getGreen() + "," + trackingPanel.desiredPathColor.getBlue());
			out.println("EstimatePathColor   = " + trackingPanel.estimatePathColor.getRed() + "," + trackingPanel.estimatePathColor.getGreen() + "," + trackingPanel.estimatePathColor.getBlue());
			out.println("TagColor            = " + trackingPanel.tagColor.getRed() + "," + trackingPanel.tagColor.getGreen() + "," + trackingPanel.tagColor.getBlue());
            out.println("ShowTags            = " + trackingPanel.showTags);
			out.println("TrackID             = " + trackingPanel.trackID);
			out.println("XOffset             = " + trackingPanel.xOffset);
			out.println("YOffset             = " + trackingPanel.yOffset);
			out.println("ShowIRSensors       = " + getRobot().showIRSensors);
			out.println("ShowDirrs           = " + getRobot().showDirrs);
			out.println("ShowSonar           = " + getRobot().showSonar);
			out.println("ShowCamera          = " + getRobot().showCamera);
			out.println("IRSensorsOn         = " + getRobot().IRSensors);
			out.println("DirrsOn             = " + getRobot().dirrs);
			out.println("SonarOn             = " + getRobot().sonar);
			out.println("EncodersOn          = " + getRobot().encoders);
			out.println("RobotID             = " + getRobot().getId());
			out.println("RobotStartX         = " + getRobot().startX);
			out.println("RobotStartY         = " + getRobot().startY);
			out.println("RobotStartAngle     = " + (int)Math.toDegrees(getRobot().startAngle));
			out.println("LeftGripOpen        = " + getRobot().leftGripOpen);
			out.println("LeftGripClosed      = " + getRobot().leftGripClosed);
			out.println("RightGripOpen       = " + getRobot().rightGripOpen);
			out.println("RightGripClosed     = " + getRobot().rightGripClosed);
			out.println("HeadCenterPitch     = " + getRobot().headPitchCenter);
			out.println("HeadDownPitchMax    = " + getRobot().headPitchDownMax);
			out.println("HeadUpPitchMax      = " + getRobot().headPitchUpMax);
			out.println("HeadYawCenter       = " + getRobot().headYawCenter);
			out.println("HeadYawLeftMax      = " + getRobot().headYawLeftMax);
			out.println("HeadYawRightMax     = " + getRobot().headYawRightMax);
			out.println("BluetoothPort       = " + bluetoothPort);
			out.println("WorkingDirectory    = " + workingDirectory);
			out.println("RobotCommunication  = " + enableRobotCommunication);
			out.print(  "RobotAddresses      = {");
			Object[] keys = robotAddresses.keySet().toArray();
			for (int i=0;i<robotAddresses.keySet().size();i++) {
				out.print("["+keys[i]+","+robotAddresses.get(keys[i])+"]");
			}
			out.println("}");
			out.print(  "Obstacles           = {");
			for (Obstacle o: robot.getEnvironment().getObstacles()){
				//for the time being we are not implementing walls here
				if (o.getType()==-1){
					//walls have 2 extra attributes
					Wall wall = (Wall)o;
					out.print("["+wall.getType()+","+(int)wall.x1+","+(int)wall.y1+","+(int)wall.x2+","+(int)wall.y2+"]");
				}else{
					out.print("["+o.getType()+","+(int)o.getX()+","+(int)o.getY()+"]");
				}
			}
			out.println("}");
			out.println();
            out.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot write configuration file RobotTrackerConfig.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	// Stop the tracker timer
 	private void haltTheTimer() {
 		if ((snapTimer != null) && snapTimer.isRunning())
        	snapTimer.stop();
 	}

 	// This is called after changes are made to the Network settings
 	// to properly enable or disable the tracking searcher process.
 	public void adjustMultiTracking() {
 		//trackerServer.multiTrack = combineMultipleTrackers;
 	}

	// Called by tracking panel to display mouse location
	public void setMouseLoc(Point p) {
		if (combineMultipleTrackers)
			p.translate(0, -trackingPanel.yOffset);
		if (mapping)
			p.translate(-trackingPanel.MAP_BORDER_SIZE,-trackingPanel.MAP_BORDER_SIZE);
		//locationField.setText(String.format("%11s","(" + p.x + "," + (CAMERA_HEIGHT - p.y - 1) + ")"));
		locationFieldX.setText(String.format("x: %d",p.x));
		locationFieldY.setText(String.format("y: %d",(CAMERA_HEIGHT - p.y - 1)));
	}

	// Set the frame rate and adjust the timer for the snapshots
	public void setFrameRate(int fr){
		frameRate = fr;
		snapTimer.setDelay(1000/fr);
	}

	// Do what needs to be done whent he program is closed
	public void closeTracker(){
        saveConfigFile();

		if (USE_WEB_CAM) {
			getRobot().setRunning(false);
			//trackerServer.online = false;
		}
		getIDE().setTracker(null);
		haltTheTimer();
		dispose();
	}






	/*** PLANNER COMMUNICATIONS METHODS ***/
	// Called when data has been received for the planner.  flag output options are
	// (OUTPUT_TO_LOG, OUTPUT_TO_FILE, OUTPUT_TO_LOG_AND_FILE, OUTPUT_TO_NONE)
	public void receivedPlannerData(ArrayList<Integer> data, int flag) {
		String timeStamped = "[" + new Time(System.currentTimeMillis()) + "] ";
		String output = "";
		for (int i=0;i<data.size();i++) {
			output += data.get(i);
			if (i != data.size()-1)
				output += ",";
		}

		switch (flag) {
			// Output data to log
			case RBCConstants.OUTPUT_TO_LOG:
				logDialog.appendTextWithCR(new String(output));
				break;

			// Output data to file
			case RBCConstants.OUTPUT_TO_FILE:
				String tempString = new String(output);
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(workingDirectory + "\\" + LOG_FILENAME, !firstTimeWriting));
					out.write("\r\n" + timeStamped + tempString);
					out.close();
					firstTimeWriting = false;
				} catch (IOException e) {
					System.out.println("ERROR: Could not write to file " + workingDirectory + "\\" + LOG_FILENAME);
				}
				break;

			// Output data to log and file
			case RBCConstants.OUTPUT_TO_LOG_AND_FILE:
				logDialog.appendTextWithCR(output);
				String tString = new String(output);
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(workingDirectory + "\\" + LOG_FILENAME, !firstTimeWriting));
					out.write("\r\n" + timeStamped + tString);
					out.close();
					firstTimeWriting = false;
				} catch (IOException e) {
					System.out.println("ERROR: Could not write to file " + workingDirectory + "\\" + LOG_FILENAME);
				}
				break;
			default:		// otherwise ... do not output the data
		}
		if (plannerHandler != null && plannerHandler.isAlive())
			plannerHandler.dataReceived(data);
	}


	// Erase the map data
	public void eraseMap() {
		dataSets = new DataSet[0];
		reDrawMap();
	}

	// Called to re-draw the map when necessary
	public void reDrawMap() {
		Thread t = new Thread(renderer);
		t.setPriority(1);
		t.start();
	}

	// Called after the relative sensor weights have been changed, to redraw the map
	public void reWeightMap() {
		Thread t = new Thread() {
			public void run() {
				trackingPanel.setMaxProgress(2);
				trackingPanel.setProgress(0);
				trackingPanel.setProgressMessage("Fusing sensor data..");
				renderer.fuseSensorData();
				trackingPanel.advance();
				renderer.computeMap();
				trackingPanel.advance();
			}
		};
		t.setPriority(1);
		t.start();
	}

	// Called to redraw the map after a color change has been made
	public void reColorMap() {
		Thread t = new Thread() {
			public void run() {
				trackingPanel.setMaxProgress(1);
				trackingPanel.setProgress(0);
				trackingPanel.setProgressMessage("Applying color map..");
				renderer.computeMap();
				trackingPanel.advance();
			}
		};
		t.setPriority(1);
		t.start();
	}

	public void update() {
		repaint();
	}

	/*
	 * For convenience, when adding an obstacle or moving the
	 * robot around the screen, this method will send the mouse
	 * position to the appropriate dialog. The point is already
	 * appropriately shifted
	 */
	public void mousePosition(Point point) {
		// TODO Auto-generated method stub
		if (addObstacleDialog!=null){
			addObstacleDialog.mousePosition(point);
		}
		if (robotPositionDialog!=null){
			robotPositionDialog.mousePosition(point);
		}
		
	}

}