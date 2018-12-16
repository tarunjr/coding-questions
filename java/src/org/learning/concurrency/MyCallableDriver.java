import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class MyCallableDriver {
	public static void main(String[] args)
		throws InterruptedException
	{
		List<Future<Long>> futures = new ArrayList<Future<Long>>();

		ExecutorService executor = Executors.newFixedThreadPool(20);

		for(int i=0; i<10000;i++){
			Callable task = new MyCallable();
			Future<Long> future = executor.submit(task);
			futures.add(future);
		}		
		
		
		System.out.println("All submitted");
		long sum = 0;
		for(Future<Long> future: futures){
			try{
				sum += future.get();	
			} catch(InterruptedException e){
				e.printStackTrace();
			} catch(ExecutionException e){
				e.printStackTrace();
			}
		}
		System.out.println(sum);
		executor.shutdown();


	}
}
