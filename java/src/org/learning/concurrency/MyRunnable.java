public class MyRunnable implements Runnable{
	private final int countUntil;

	public MyRunnable(int countUntil){
		this.countUntil = countUntil;
	}

	@Override
	public void run(){
		long sum = 0;
		for(int i=0; i < this.countUntil;i++)
			sum += i;

		System.out.println(sum);
	}
}