package org.learning.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AppService {
	private final ExecutorService exec;
	private final LogService logService;
	private  long counter = 0;
	private  List<Future<?>> tasks;
	
	public AppService(LogService logService){
		exec = new CancellingExecutor(2,2,0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000));
		this.logService = logService;
		this.tasks = new ArrayList<Future<?>>();
	}
	public void start() {
		Callable<Integer> task = new AppTask<Integer>(logService); 
		tasks.add(exec.submit(task));
	}
	public void stop() {
		for(Future<?> t: tasks)
			t.cancel(true);
		
		exec.shutdown();
		
	}
	private class AppTask<T>  extends CancellableTaskBase {
		private final LogService logService;
		
		public AppTask(LogService logSvc) {
			logService = logSvc;
		}
		@Override
		public Object call() throws Exception {
			
			while(!cancelled) {
				logService.log(Long.toString(counter++));
			}
			return 0;
		}	
	}
}
