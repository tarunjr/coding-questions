package org.learning.solutions;

public class StockProfit {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        // can't sell on the same day.
        if (days <= 1) {
            return 0;
        }
        
        // calculate and remember the max profit made by selling on day i
        // If making a loss then normalize the loss to 0 i.e no profit.
        int[] profit = new int[prices.length];
        for(int i=0; i < days;i++) {
            profit[i] = 0;
        }
        
        int max_profit = 0;
        for(int i=1; i < days; i++) {
            profit[i] =  Math.max(0, profit[i-1] + (prices[i] - prices[i-1]));
        
            if(profit[i] > max_profit) {
                max_profit = profit[i];
            }
        }
  
        return max_profit;
        
    }
}