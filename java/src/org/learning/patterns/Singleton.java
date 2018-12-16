package org.learning.patterns;

public class Singleton {
	private Singleton instance;
	private int data;
	
	private Singleton() {
		instance = null;
		data = 0;
	}
	public Singleton getInstance() {
		synchronized(this){
			if(instance == null) {
				instance = new Singleton();
			}
			return instance;
		}
	}
	
	public synchronized int getData() {
		return data;
	}
	public synchronized void setData(int data) {
		this.data = data;
	}
	
}
