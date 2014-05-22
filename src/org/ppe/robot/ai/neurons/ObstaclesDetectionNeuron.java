package org.ppe.robot.ai.neurons;

import lejos.nxt.UltrasonicSensor;

import org.ppe.robot.ai.AIDecision;
import org.ppe.robot.ai.interruptions.AIDecisionInterruption;

public class ObstaclesDetectionNeuron implements AINeuron{
	
	
	private UltrasonicSensor sensor;
	
	public ObstaclesDetectionNeuron(UltrasonicSensor sensor){
		this.sensor = sensor;
	}

	@Override
	public void deliberate(AIDecision decision) throws AIDecisionInterruption {
		int objectDistance = sensor.getDistance();
		//Object found
		if(objectFound(objectDistance)){
			decision.travel = true;
			decision.distance = objectDistance;
		}else{
			decision.rotate = true;
			decision.rotateAngle = 10.0;
		}
	}
	
	private boolean objectFound(int objectDistance){
		return objectDistance > 0 && objectDistance < 255;
	}

	@Override
	public void check(AIDecision decision) throws AIDecisionInterruption {
		
	}

}
