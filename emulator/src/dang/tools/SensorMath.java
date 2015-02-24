package dang.tools;

public class SensorMath {

	byte sensor = 0;

	public byte getSensor() {
		return sensor;
	}
	
	public void setSensor(byte sensorNumber){
		sensor = (byte) (sensor|(1<<sensorNumber));
	}
	
	public byte readSensor(byte sensorNumber){
		return (byte) ((sensor&(1<<sensorNumber))>>sensorNumber);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new SensorMath();
	}
	
	public SensorMath(){
		setSensor((byte) 7);
		setSensor((byte) 5);
		setSensor((byte) 4);
		setSensor((byte) 3);
		setSensor((byte) 2);

		System.out.println(sensor);
		for (int i=0; i<8; i++){
			System.out.println("Sensor "+i+": "+readSensor((byte)i));
		}
	}

}
