package org.learning.concurrency;

public class LogServiceTest {
	public static void main(String[] args) {
		LogService svc = new LogService();
		svc.start();
		
		
		AppService appSvc = new AppService(svc);
		appSvc.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		appSvc.stop();
		svc.stop();
	}
}
