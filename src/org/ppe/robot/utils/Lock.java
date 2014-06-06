package org.ppe.robot.utils;

public class Lock {
	
	private volatile boolean locked = false;
	private final Object sync = new Object();
	
	public void lock(){
		synchronized(sync){
			while(locked){
					try { sync.wait(); } catch (InterruptedException e) {}
			}
			locked = true;
		}
	}
	
	public synchronized void unlock(){
		synchronized(sync){
			locked = false;
			sync.notifyAll();
		}
	}
	
	public void waitUnlock(){
		if(locked){
			synchronized(sync){
				while(locked){
					try { sync.wait(); } catch (InterruptedException e) {}
				}
			}
		}
	}
	
	public boolean locked(){
		synchronized(sync){
			return locked;
		}
	}

	
}
