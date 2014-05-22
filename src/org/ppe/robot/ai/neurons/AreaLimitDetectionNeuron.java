package org.ppe.robot.ai.neurons;

import org.ppe.robot.ai.AIDecision;
import org.ppe.robot.ai.interruptions.AIDecisionInterruption;
import org.ppe.robot.ai.interruptions.BandLimitInterruption;
import org.ppe.robot.sensors.LightSensorArray;
import org.ppe.robot.sensors.LightSensorArray.LightArray;

public class AreaLimitDetectionNeuron implements AINeuron{
	
	
	private LightSensorArray sensor;
	
	public AreaLimitDetectionNeuron(LightSensorArray sensor){
		this.sensor = sensor;
	}

	@Override
	public void deliberate(AIDecision decision) throws AIDecisionInterruption {
		LightArray intensities = sensor.read();
		if(isBandLimit(intensities)){
			decision.rotate = true;
			decision.rotateAngle = 90;
		}else if (!decision.rotate){
			decision.distance = 10;
			decision.travel = true;
		}
	}
	
	private boolean isBandLimit(LightArray intensities){
		return intensities.getAverage() == intensities.get(0);
	}

	@Override
	public void check(AIDecision decision) throws AIDecisionInterruption {
		LightArray intensities = sensor.read();
		if(isBandLimit(intensities)){
			throw new BandLimitInterruption();
		}
	}

}
