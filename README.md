PPE-NXT-Robot SUMO
=================

>>Robot SUMO controller using leJOS Java SDK

<h4>Requirements</h4>
* I'm using [Eclipse IDE](https://www.eclipse.org/) with leJOS plugin
* NXT Robot Firmware changed to [leJOS NXT Firmware](http://www.lejos.org/)
* USB NXT Drivers

<h4>Robot structure</h4>
* Two motors (Right and Left)
* An Ultrasonic Sensor of NXT(Sending 0-255 distance in cm)
* A LightSensorArray of Mindstorm.com(Sending int[] array of 0-100% of light intensities) using an edited I2C Bus
