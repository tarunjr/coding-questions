package org.learning.concurrency;

import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class CancellableTaskBase<T> implements CancellableTask<T> {
	protected  boolean cancelled = false;
	
	@Override
	public synchronized void cancel() {
		cancelled = true;
	}

	@Override
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {
			public boolean cancel(boolean mayInterruptIfRunning) { 
				try {
					CancellableTaskBase.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}
}
