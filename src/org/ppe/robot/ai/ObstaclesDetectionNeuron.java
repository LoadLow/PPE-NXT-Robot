package org.ppe.robot.ai;

import org.ppe.robot.Robot;

public class ObstaclesDetectionNeuron extends AINeuron{
	
	public ObstaclesDetectionNeuron(Robot robot){
		super(robot);
		robot.getObjectDistance();
	}

	@Override
	protected void deliberate() {
		//Wait if locked by another neuron
		robot.locker.waitUnlock();
		
		//Read ultrasonir sensor distance
		int objectDistance = robot.getObjectDistance();
		//Object found
		if(objectFound(objectDistance)){
			System.out.println("Object found !");
			robot.goForward(100);
		}else{
			robot.rotateClockwise(10);
		}
	}
	
	private static boolean objectFound(int objectDistance){
		return objectDistance > 0 && objectDistance <= 70; 
	}

}
