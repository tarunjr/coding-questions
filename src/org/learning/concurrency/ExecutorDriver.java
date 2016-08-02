import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorDriver {
	public static void main(String[] args) throws IllegalStateException, InterruptedException {

		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(
			() -> "task 1",
			() -> "task 2",
			() -> "task 3");


		executor.invokeAll(callables)
				.stream()
				.map(future -> {
					try{
						return future.get();
					} catch (Exception e){
						throw new IllegalStateException(e);
					}
				})
				.forEach(System.out::println);
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		Runnable task = () -> System.out.println("Time: " + System.nanoTime());

		ses.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);	
	}

}