package org.ppe.robot.ai.neurons;

import org.ppe.robot.ai.AIDecision;
import org.ppe.robot.ai.interruptions.AIDecisionInterruption;

public interface AINeuron {
	
	public void deliberate(AIDecision decision) throws AIDecisionInterruption;
	public void check(AIDecision decision) throws AIDecisionInterruption;
	
}
