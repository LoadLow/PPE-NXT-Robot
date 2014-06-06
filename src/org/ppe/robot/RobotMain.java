package org.ppe.robot;

public class RobotMain {
	
  static{
	  Runtime.getRuntime().addShutdownHook(new Thread(){
		  @Override
		  public void run(){
			  if(Inst!=null){
				  Inst.stop();
			  }
		  }
	  });
  }
  
  private static Robot Inst;
	
  public static void main (String[] args) {
	  System.out.print("Robot starting...");
	  Inst = new Robot();
	  Inst.start();
	  System.out.println(" ok!");
  }
  
  
}
