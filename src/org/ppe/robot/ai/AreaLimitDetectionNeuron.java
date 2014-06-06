package org.ppe.robot.ai;

import org.ppe.robot.Robot;
import org.ppe.robot.utils.LightSensorArray.LightArray;

public class AreaLimitDetectionNeuron extends AINeuron{
	
	
	public AreaLimitDetectionNeuron(Robot robot){
		super(robot);
		robot.getLightArray();
	}

	@Override
	public void deliberate() {
		LightArray intensities = robot.getLightArray();
		if(intensities.hasIntensity(10)){
			robot.locker.lock();
			try{
				System.out.println("Limit found!");
				robot.interruptMovements();
				robot.goBack(20);
				robot.rotateClockwise(20);
			}finally{
				robot.locker.unlock();
			}
		}
	}

}
