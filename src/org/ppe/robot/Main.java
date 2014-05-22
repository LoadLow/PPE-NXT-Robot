package org.ppe.robot;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Main {
  public static void main (String[] args) {
    System.out.println(Constants.NAME);
    UltrasonicSensor uSensor = new UltrasonicSensor(SensorPort.S2);
    for(int i=0; i<100; i++){
    	System.out.print("Distance={");
    	System.out.print(uSensor.getDistance());
    	System.out.println("};");
    	try{
    		Thread.sleep(5000);
    	}catch(InterruptedException e){}
    }
  }
}
