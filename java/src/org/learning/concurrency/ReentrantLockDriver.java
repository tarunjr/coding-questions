import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.concurrent.Semaphore;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.IntStream;

public class ReentrantLockDriver{
	public static void main(String[] args){
		
		fourth_demo();
	}

	public static void first_demo(){
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ReentrantLock lock = new ReentrantLock();

		Runnable task1 = () -> {
			try
			{
				lock.lock();
				sleep(1);
			} finally{
				lock.unlock();
			}};

		Runnable task2 = () -> {
			System.out.println("Locked: " + lock.isLocked());
    		System.out.println("Held by me: " + lock.isHeldByCurrentThread());
    		boolean locked = lock.tryLock();
    		System.out.println("Lock acquired: " + locked);
		};

		executor.submit(task2);
		executor.submit(task1);

		stop(executor);
	}

	public static void second_demo(){
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		Map<String, String> names = new HashMap<String,String>();
		
		executor.submit(() -> {
				try{
					lock.writeLock().lock();
					sleep(1);
					names.put("tarun", "rathor");

				} finally{
					lock.writeLock().unlock();
				}
			});

		executor.submit(() -> {
				try{

					lock.readLock().lock();
					System.out.println("first: " + names.get("tarun"));
					sleep(1);
				} finally{
					lock.readLock().unlock();
				}
			
			});

		executor.submit(() -> {
				try{

					lock.readLock().lock();
					System.out.println("second: " + names.get("tarun"));
					sleep(1);
				} finally{
					lock.readLock().unlock();
				}
			
			});
		stop(executor);
	}

	public static void third_demo(){

		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
    	long stamp = lock.tryOptimisticRead();
    	try {
        	System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        	sleep(1);
        	System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        	sleep(2);
        	System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
    	} finally {
        	lock.unlock(stamp);
    	}
		});

		executor.submit(() -> {
   			long stamp = lock.writeLock();
    		try {
       			 System.out.println("Write Lock acquired");
        		sleep(2);
    		} finally {
        		lock.unlock(stamp);
        		System.out.println("Write done");
    		}
		});

stop(executor);
	}

	public static void fourth_demo(){
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Semaphore semaphore = new Semaphore(5);
		Runnable task = () -> {
			boolean permit = false;
			try{
				permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
				if(permit){
					System.out.println("Semaphore acquired");
					sleep(5);
				} else {
					System.out.println("Could not acquire semaphore");
				}
			} catch(InterruptedException ex) {} 
			finally {
				if(permit){
					semaphore.release();
				}
			}
		};
		IntStream.range(0, 10).forEach(i -> executor.submit(task));

		stop(executor);
	}
	public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

}