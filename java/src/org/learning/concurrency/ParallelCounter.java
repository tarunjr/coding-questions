public class ParallelCounter implements Runnable {

    private int counter = 0;
    private int MAX_COUNT= 20;

    @Override
    public void run(){
      while(true){
          try{
              incrementAndPrint();
          }catch(Exception ex) {
            break;
          }
      }
    }
    synchronized void incrementAndPrint() throws Exception{
        if( this.counter < this.MAX_COUNT){
          System.out.println(++this.counter);
          return;
        } else {
          throw new Exception();
        }
    }
    public static void main(String[] args){
      System.out.println("Hello World");

      Runnable r = new ParallelCounter();
      Thread t1 = new Thread(r);
      t1.start();

      //System.out.println("next");
      Thread t2 = new Thread(r);
      t2.start();
    }
}
