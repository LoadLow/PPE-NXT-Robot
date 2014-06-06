package org.ppe.robot.ai;

import lejos.nxt.Button;

import org.ppe.robot.Robot;

public class ButtonHandlerNeuron extends AINeuron{
	
	public ButtonHandlerNeuron(Robot robot){
		super(robot);
	}

	@Override
	public void deliberate() {
		try{
			Button.ESCAPE.waitForPressAndRelease();
		}finally{
			robot.stop();
			System.exit(0);
		}
	}

}
