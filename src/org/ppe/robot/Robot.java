package org.ppe.robot;

import java.util.ArrayList;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

import org.ppe.robot.ai.AIDecision;
import org.ppe.robot.ai.interruptions.AIDecisionInterruption;
import org.ppe.robot.ai.neurons.AINeuron;
import org.ppe.robot.sensors.LightSensorArray;

public class Robot {
	
	private LightSensorArray lightSensor;
	private UltrasonicSensor ultrasonicSensor;
	private DifferentialPilot motorsPilot;
	
	private ArrayList<AINeuron> neurons;
	
	public Robot(){
		this.neurons = new ArrayList<AINeuron>();
		this.lightSensor = new LightSensorArray(SensorPort.S1);
		this.ultrasonicSensor = new UltrasonicSensor(SensorPort.S2);
		this.motorsPilot = new DifferentialPilot(2.25f, 2.25f, Motor.A, Motor.B, false);
	}
	
}
