package org.ppe.robot.ai;

import org.ppe.robot.Robot;

public abstract class AINeuron implements Runnable{
	
	protected final Robot robot;
	private final Thread tr;
	
	public AINeuron(Robot robot){
		this.robot = robot;
		this.tr = new Thread(this);
	}
	
	protected abstract void deliberate();
	
	@Override
	public void run(){
		while(robot.isRunning()){
			if(!robot.isRunning()){
				break;
			}
			this.deliberate();
		}
	}
	
	public void start(){
		if(!tr.isAlive()){
			tr.start();
		}
	}
	
	public void stop(){
		tr.interrupt();
	}
	
	
}
