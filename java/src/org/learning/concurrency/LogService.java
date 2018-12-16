package org.learning.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LogService {
	
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private final ExecutorService exec;
	public LogService() {
		exec = Executors.newFixedThreadPool(2);
		
	}
	public void start() {
		exec.submit(new LogTask(queue));
		exec.submit(new LogTask(queue));
	}
	
	public void stop() {
		exec.shutdown(); 
	}
	
	public void log(String msg) {
		queue.add(msg);
	}
	private class LogTask implements Runnable {
		private final BlockingQueue<String> queue;
		public LogTask(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		@Override
		public void run() {
			while(true) {
				try {
					String msg = queue.take();
					System.out.println("Logger: " + msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
