package dang.tracker;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class ServoCalibrationDialog extends JDialog {

	private static final int LEFT_SERVO_STOPPED_VALUE = 750;
	private static final int RIGHT_SERVO_STOPPED_VALUE = 750;
	private static final int LEFT_GRIPPER_CENTER = 170;
	private static final int RIGHT_GRIPPER_CENTER = 150;
	private static final int HEAD_PITCH_HORIZONTAL = 137;
	private static final int HEAD_YAW_STRAIGHT_AHEAD = 146;

	private RobotTracker rb;
	private JSpinner 		lwSpinner, rwSpinner, lgSpinner, rgSpinner, hpSpinner, hySpinner;
	private SpinnerModel 	lwModel, rwModel, lgModel, rgModel, hpModel, hyModel;

    public ServoCalibrationDialog(RobotTracker owner){
		super(owner,"Servo Calibration",false);
		rb = owner;
		setLocationRelativeTo(rb);
		setLocation(90, 70);

		setSize(550, 418);
		setResizable(false);
		setLayout(null);

		JPanel aPanel;
		JLabel aLabel;


		// Set up the Wheel Servos Panel
		aPanel = new JPanel();
		getContentPane().setBackground(Color.white);
		aPanel.setBackground(Color.white);
		aPanel.setLayout(null);
		aPanel.setSize(245, 170);
		aPanel.setLocation(10, 10);
		aPanel.setBorder(new TitledBorder("Wheel Servos"));
		this.add(aPanel);
		aLabel = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\wheelServos.png"));
		aLabel.setSize(125, 141);
		aLabel.setLocation(60, 20);
		aPanel.add(aLabel);
		lwModel = new SpinnerNumberModel(LEFT_SERVO_STOPPED_VALUE,  LEFT_SERVO_STOPPED_VALUE - 20,  LEFT_SERVO_STOPPED_VALUE + 20,  1);
		lwSpinner = new JSpinner(lwModel);
		lwSpinner.setSize(50, 40);
		lwSpinner.setLocation(10, 90);
		aPanel.add(lwSpinner);
		lwSpinner.setValue(LEFT_SERVO_STOPPED_VALUE);
		lwSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleLeftServoChange(((Integer)(((JSpinner)(e.getSource())).getValue())).intValue());
            }
        });

		rwModel = new SpinnerNumberModel(RIGHT_SERVO_STOPPED_VALUE,  RIGHT_SERVO_STOPPED_VALUE - 20,  RIGHT_SERVO_STOPPED_VALUE + 20,  1);
		rwSpinner = new JSpinner(rwModel);
		rwSpinner.setSize(50, 40);
		rwSpinner.setLocation(185, 90);
		aPanel.add(rwSpinner);
		rwSpinner.setValue(RIGHT_SERVO_STOPPED_VALUE);
		rwSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleRightServoChange(((Integer)(((JSpinner)(e.getSource())).getValue())).intValue());
            }
        });

		// Set up the Gripper Servos Panel
		aPanel = new JPanel();
		getContentPane().setBackground(Color.white);
		aPanel.setBackground(Color.white);
		aPanel.setLayout(null);
		aPanel.setSize(270, 170);
		aPanel.setLocation(265, 10);
		aPanel.setBorder(new TitledBorder("Gripper Servos"));
		this.add(aPanel);
		aLabel = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\gripperServos.png"));
		aLabel.setSize(153, 141);
		aLabel.setLocation(60, 20);
		aPanel.add(aLabel);

		lgModel = new SpinnerNumberModel(LEFT_GRIPPER_CENTER,  0,  255,  1);
		lgSpinner = new JSpinner(lgModel);
		lgSpinner.setSize(50, 40);
		lgSpinner.setLocation(10, 30);
		aPanel.add(lgSpinner);
		lgSpinner.setValue(LEFT_GRIPPER_CENTER);
		lgSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleLeftGripperChange(((Integer)(((JSpinner)(e.getSource())).getValue())).intValue());
            }
        });
		rgModel = new SpinnerNumberModel(RIGHT_GRIPPER_CENTER,  0,  255,  1);
		rgSpinner = new JSpinner(rgModel);
		rgSpinner.setSize(50, 40);
		rgSpinner.setLocation(210, 30);
		aPanel.add(rgSpinner);
		rgSpinner.setValue(RIGHT_GRIPPER_CENTER);
		rgSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleRightGripperChange(((Integer)(((JSpinner)(e.getSource())).getValue())).intValue());
            }
        });

		// Set up the Head Pivot Servo Panel
		aPanel = new JPanel();
		getContentPane().setBackground(Color.white);
		aPanel.setBackground(Color.white);
		aPanel.setLayout(null);
		aPanel.setSize(245, 190);
		aPanel.setLocation(10, 190);
		aPanel.setBorder(new TitledBorder("Head Pitch Servo"));
		this.add(aPanel);
		aLabel = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\headPivotServo.png"));
		aLabel.setSize(145, 148);
		aLabel.setLocation(80, 30);
		aPanel.add(aLabel);
		hpModel = new SpinnerNumberModel(HEAD_PITCH_HORIZONTAL,  0,  255,  1);
		hpSpinner = new JSpinner(hpModel);
		hpSpinner.setSize(50, 40);
		hpSpinner.setLocation(25, 78);
		aPanel.add(hpSpinner);
		hpSpinner.setValue(HEAD_PITCH_HORIZONTAL);
		hpSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleHeadPitchChange(((Integer)(((JSpinner)(e.getSource())).getValue())).intValue());
            }
        });

		// Set up the Head Rotate Servo Panel
		aPanel = new JPanel();
		getContentPane().setBackground(Color.white);
		aPanel.setBackground(Color.white);
		aPanel.setLayout(null);
		aPanel.setSize(270, 190);
		aPanel.setLocation(265, 190);
		aPanel.setBorder(new TitledBorder("Head Yaw Servo"));
		this.add(aPanel);
		aLabel = new JLabel(new ImageIcon(RobotTracker.INSTALL_DIRECTORY + "Images\\headRotateServo.png"));
		aLabel.setSize(187, 160);
		aLabel.setLocation(80, 20);
		aPanel.add(aLabel);
		hyModel = new SpinnerNumberModel(HEAD_YAW_STRAIGHT_AHEAD,  0,  255,  1);
		hySpinner = new JSpinner(hyModel);
		hySpinner.setSize(50, 40);
		hySpinner.setLocation(40, 78);
		aPanel.add(hySpinner);
		hySpinner.setValue(HEAD_YAW_STRAIGHT_AHEAD);
		hySpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleHeadYawChange(((Integer)(((JSpinner)(e.getSource())).getValue())).intValue());
            }
        });
	}

	public void resetValues() {
		lwSpinner.setValue(LEFT_SERVO_STOPPED_VALUE);
		rwSpinner.setValue(RIGHT_SERVO_STOPPED_VALUE);
		lgSpinner.setValue(LEFT_GRIPPER_CENTER);
		rgSpinner.setValue(LEFT_GRIPPER_CENTER);
		hpSpinner.setValue(HEAD_PITCH_HORIZONTAL);
		hySpinner.setValue(HEAD_YAW_STRAIGHT_AHEAD);
	}


	private void handleLeftServoChange(int newValue) {
		byte[]  data = new byte[3];
		data[0] = 1;
		data[1] = (byte)(newValue / 256);
		data[2] = (byte)(newValue % 256);
		rb.sendData(data);
	}
	private void handleRightServoChange(int newValue) {
		byte[]  data = new byte[3];
		data[0] = 2;
		data[1] = (byte)(newValue / 256);
		data[2] = (byte)(newValue % 256);
		rb.sendData(data);
	}
	private void handleLeftGripperChange(int newValue) {
		byte[]  data = new byte[3];
		data[0] = 3;
		data[1] = (byte)(newValue / 256);
		data[2] = (byte)(newValue % 256);
		rb.sendData(data);
	}
	private void handleRightGripperChange(int newValue) {
		byte[]  data = new byte[3];
		data[0] = 4;
		data[1] = (byte)(newValue / 256);
		data[2] = (byte)(newValue % 256);
		rb.sendData(data);
	}
	private void handleHeadPitchChange(int newValue) {
		byte[]  data = new byte[3];
		data[0] = 5;
		data[1] = (byte)(newValue / 256);
		data[2] = (byte)(newValue % 256);
		rb.sendData(data);
	}
	private void handleHeadYawChange(int newValue) {
		byte[]  data = new byte[3];
		data[0] = 6;
		data[1] = (byte)(newValue / 256);
		data[2] = (byte)(newValue % 256);
		rb.sendData(data);
	}

	public static void main(String[] args) {
		new ServoCalibrationDialog(null).setVisible(true);
	}
}