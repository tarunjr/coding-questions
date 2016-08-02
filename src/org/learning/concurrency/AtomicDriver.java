import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class AtomicDriver{

	public static void main(String[] args){
		AtomicInteger atInt = new AtomicInteger(100);
		//System.out.println(atInt.updateAndGet(n -> n*2));

		//System.out.println(atInt.accumulateAndGet(5, (n,m) -> n / m));
		LongAdder lngAddr = new LongAdder();
		IntStream.range(0,100).forEach(i -> lngAddr.add(i) );

		LongBinaryOperator op = (x, y) -> 2 * x + y;
		LongAccumulator lngAccum = new LongAccumulator(op, 1L);
		IntStream.range(0,10).forEach(i -> lngAccum.accumulate(i) );

		System.out.println(lngAddr.sum());
		System.out.println(lngAccum.getThenReset());

		map_test();
	}
	public static void map_test(){
		System.out.println(ForkJoinPool.getCommonPoolParallelism());

		ConcurrentHashMap<String,String> map = new ConcurrentHashMap();
		map.put("tarun", "rathor");
		map.put("vikas", "singh");
		map.put("vimal", "mak");
		map.put("tarun2", "rathor");
		map.put("vikas2", "singh");
		map.put("vimal2", "mak");

		map.forEach(1, (k,v) -> System.out.printf("%s : %s thread=%s\n", k,v, Thread.currentThread().getName()));
	}
}
