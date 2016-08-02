import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CompletableFutureDriver{
	public static void main(String[] args){
		
		CompletableFuture<String> futureCount = createCompletableFuture()
			.thenApply((Integer count) -> {
					int transformedValue = count * 10;
					return transformedValue;
			}).thenApply(transformed -> "Finally a string:" + transformed);

		
	
     	try {
     		String result = futureCount.get();

     		System.out.print("Result: " + result  + "\n");
     	} catch(InterruptedException | ExecutionException ex) {}
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
