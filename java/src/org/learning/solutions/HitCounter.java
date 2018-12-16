package org.learning.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class HitCounter {
    private Deque<Integer> batchHitCounts;
    private int batchHitCount;
    private int batchTimestamp;
    private int windowHitCount;
    private int windowSize = 300;
    
    /** Initialize your data structure here. */
    public HitCounter() {
        batchHitCounts = new ArrayDeque<Integer>();
        batchHitCount = 0;
        batchTimestamp = 0;
        windowHitCount = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if(timestamp == batchTimestamp){
           batchHitCount++;
        }
        else if(timestamp > batchTimestamp){
            int outBatchCount = 0;
            if(batchHitCounts.size() < windowSize)
            {
                batchHitCounts.addFirst(batchHitCount);
            } else{
                outBatchCount = batchHitCounts.removeLast();
                batchHitCounts.addFirst(batchHitCount);
            }
            windowHitCount =  windowHitCount-outBatchCount+batchHitCount;
            batchHitCount = 1;
            batchTimestamp = timestamp;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
       //System.out.println("getHits:" + timestamp + "->" +  windowHitCount);
       return  windowHitCount;
    }
}
