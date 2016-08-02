import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyRunnableDriver {
	public static void main(String[] args)
		throws InterruptedException
	{
		List<Thread> threads = new ArrayList<Thread>();

		ExecutorService executor = Executors.newFixedThreadPool(20);

		for(int i=0; i<10000;i++){
			Runnable task = new MyRunnable(100000+i);
			executor.execute(task);
		}		
		
		executor.shutdown();
		executor.awaitTermination(60, TimeUnit.SECONDS);
		System.out.println("All complete");
	}
}
