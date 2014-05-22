package org.ppe.robot.sensors;

import lejos.nxt.I2CPort;
import lejos.nxt.I2CSensor;

public class LightSensorArray extends I2CSensor {

    private byte[] buf = new byte[8];
    private final static byte COMMAND = 0x41;

    public enum Command {

        CALIBRATE_WHITE('W'),
        CALIBRATE_BLACK('B'),
        SLEEP('D'),
        WAKEUP('P'),
        FREQ_60HZ('A'),
        FREQ_50HZ('E'),
        FREQ_UNIVERSAL('U');

        private Command(char value) {
            this.value = value;
        }
        final char value;
    }

    public LightSensorArray(I2CPort port, int address) {
        super(port, address, I2CPort.LEGO_MODE, TYPE_LOWSPEED_9V);
    }

    public LightSensorArray(I2CPort port) {
        this(port, 0x14 /*DEFAULT_I2C_ADDRESS*/);
    }

    public int sendCommand(Command cmd) {
        return sendData(COMMAND, (byte) cmd.value);
    }

    public int wakeUp() {
        return this.sendCommand(Command.WAKEUP);
    }

    /**
     * Calibrates the Light Sensor Array
     */
    public void calibrateBlack() {
        this.sendCommand(Command.CALIBRATE_BLACK);
    }

    /**
     * Gets the values from the Light Sensor Array
     *
     * @return LightArray of intensities
     */
    public LightArray read() {
        int[] ret = new int[8];
        int err = getData(0x42, buf, 8);
        if (err == 0) {
            for (int i=0; i<8; i++) ret[i] = buf[i] & 0xff;
        } else {
            for (int i=0; i<8; i++) ret[i] = -1;
        }
        return new LightArray(ret);    
    }
    
    public class LightArray {
    	private int[] array;

    	public LightArray(int[] array){
    		this.array = array;
    	}
    	
    	public int[] get(){
    		return array;
    	}
    	
    	public int get(int slot){
    		if (slot > 7 || slot < 0) {
    			throw new IllegalArgumentException("Slot MUST be between 0 and 7!");
    	    }
    		return array[slot];
    	}
    	
    	public int[] getLeft(){
    		int[] subArr = new int[4];
    		System.arraycopy(array, 0, subArr, 0, 4);
    		return subArr;
    	}
    	
    	public int[] getRight(){
    		int[] subArr = new int[4];
    		System.arraycopy(array, 3, subArr, 0, 4);
    		return subArr;
    	}
    	
    	public int[] getMiddle(){
    		int[] subArr = new int[2];
    		System.arraycopy(array, 3, subArr, 0, 2);
    		return subArr;
    	}
    	
    	public int getAverage(){
    		int average = 0;
        	for(int i=0; i < 8; i++){
        		average+=array[i];
        	}
        	return (int)average/8;
    	}
    	
    }
}
