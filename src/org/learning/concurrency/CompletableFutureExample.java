package org.learning.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
	public int run() {
		CompletableFuture<Integer> cf = createCompletableFuture();
		int i = -1;
		try {
			i =  cf.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	public static CompletableFuture<Integer> createCompletableFuture(){
		CompletableFuture<Integer> futureCount = CompletableFuture.supplyAsync(() -> {
			try{
				Thread.sleep(5000);
			} catch(InterruptedException e){}
			return 20;
			
		});
		return futureCount;
	}
}
