package org.ppe.robot.ai;

import lejos.nxt.Sound;

import org.ppe.robot.Robot;

public class BeeperNeuron extends AINeuron{
	public BeeperNeuron(Robot robot){
		super(robot);
		Sound.setVolume(75);
	}

	@Override
	public void deliberate() {
		Sound.beep();
		try { Thread.sleep(1500); } catch (InterruptedException e) {}
	}
}
