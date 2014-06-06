package org.ppe.robot;

import java.util.ArrayList;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

import org.ppe.robot.ai.AINeuron;
import org.ppe.robot.ai.AreaLimitDetectionNeuron;
import org.ppe.robot.ai.BeeperNeuron;
import org.ppe.robot.ai.ButtonHandlerNeuron;
import org.ppe.robot.ai.ObstaclesDetectionNeuron;
import org.ppe.robot.utils.LightSensorArray;
import org.ppe.robot.utils.LightSensorArray.LightArray;
import org.ppe.robot.utils.Lock;

public class Robot {
	
	private final LightSensorArray lightSensor;
	private final UltrasonicSensor ultrasonicSensor;
	private final DifferentialPilot motorsPilot;
	
	public final Lock locker = new Lock();
	
	private ArrayList<AINeuron> neurons;
	
	private boolean running;
	
	public boolean isRunning(){
		return running;
	}
	
	public Robot(){
		this.neurons = new ArrayList<AINeuron>();
		this.lightSensor = new LightSensorArray(SensorPort.S1);
		this.ultrasonicSensor = new UltrasonicSensor(SensorPort.S2);
		this.motorsPilot = new DifferentialPilot(2.20f, 2.20f, Motor.B, Motor.A, false);
		registerNeurons();
	}
	
	///Register all neurons on the neurons ArrayList
	private void registerNeurons(){
		neurons.add(new ObstaclesDetectionNeuron(this));
		neurons.add(new AreaLimitDetectionNeuron(this));
		neurons.add(new ButtonHandlerNeuron(this));
		neurons.add(new BeeperNeuron(this));
	}
	
	public void start(){
		this.running = true;
		for(AINeuron neuron : neurons){
			neuron.start();
		}
	}
	
	public void stop(){
		System.out.print("Powering off...");
		this.running = false;
		for(AINeuron neuron : neurons){
			neuron.stop();
		}
		System.out.println(" ok!");
	}
	
	public void rotateClockwise(double angle){
		motorsPilot.rotate(68.5 + (4.94*angle) + (0.00462*Math.pow(angle, 2)));
	}
	
	public void rotateAntiClockwise(double angle){
		motorsPilot.rotate(-(68.5 + (4.94*angle) + (0.00462*Math.pow(angle, 2))));
	}
	
	public void goBack(int distance){
		motorsPilot.travel(-distance);
	}
	
	public void goForward(int distance){
		motorsPilot.travel(distance);
	}
	
	public void interruptMovements(){
		motorsPilot.stop();
	}
	
	public LightArray getLightArray(){
		return lightSensor.read();
	}
	
	public int getObjectDistance(){
		return ultrasonicSensor.getDistance();
	}
	
}
