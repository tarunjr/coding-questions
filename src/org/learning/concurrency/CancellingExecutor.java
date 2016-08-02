package org.learning.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancellingExecutor  extends ThreadPoolExecutor{

	
	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}
	
	public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask)
			return ((CancellableTask<T>) callable).newTask();
		else
			return super.newTaskFor(callable);
	}
}
