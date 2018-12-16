import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class MinSumTwo{
    public static void main(String[] args){
      Integer[] prices = {150,24,79,50,88,345, 3};
      Integer credit = 200;
      Integer[] results = minSum(credit, prices);
      System.out.println(results[0] + ", " + results[1]);

      Integer[] prices2 = {2,1,9, 4 ,4 ,56, 90 ,3};
	    credit = 8;
      results = minSum(credit, prices2);
      System.out.println(results[0] + ", " + results[1]);

      return;
    }
    public static Integer[] minSum(Integer credit, Integer[] prices){
        Map<Integer,List<Integer>> priceIndexMap = new HashMap<Integer, List<Integer>>();
        Integer index = -1;
        for(Integer price: prices){
            index++;
            if(price < credit){
              List<Integer> indexes = priceIndexMap.get(price);
              if(indexes == null)
                  indexes = new ArrayList();
              indexes.add(index);
              priceIndexMap.put(price, indexes);
            }
        }
        Integer[] results = {-1,-1};

        for(Map.Entry<Integer, List<Integer>> entry: priceIndexMap.entrySet())
        {
            Integer price = entry.getKey();
            List<Integer> indexes = entry.getValue();
            Integer otherPrice = credit-price;
            Integer thisIndex = indexes.remove(0);
            List<Integer> otherIndexes = priceIndexMap.get(otherPrice);
            if(otherIndexes != null){
                  Integer otherIndex = otherIndexes.remove(0);
                  results[0] = thisIndex;
                  results[1] = otherIndex;
                  break;
            }
        }
        return results;
    }
}
