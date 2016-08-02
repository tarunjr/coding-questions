package org.learning.solutions;

import java.util.List;

/* 57 Insert interval
 * https://leetcode.com/problems/insert-interval/
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 */
public class IntervalInsert {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        insert(intervals, 0, intervals.size()-1, newInterval);
        return intervals;
    }
    private void insert(List<Interval> intervals, int start, int end, Interval newInterval) {
        if (start == end) {
            insertAt(intervals, start, newInterval);
            return;
        }
        int mid = start + (end-start) / 2 ;
        
        int diff = compare(newInterval, intervals.get(mid));
        if(diff == 0) {
            insertAt(intervals, mid, newInterval);
        } else  if(diff > 0) {
            insert(intervals, mid+1, end, newInterval);
        } else {
            insert(intervals, start, mid, newInterval);
        }
        return;
    }
    private void insertAt(List<Interval> intervals, int index, Interval newInterval) {
        if(index < 0 || index > intervals.size())
        	return;
        
    	int d = compare(newInterval, intervals.get(index));
        if(d == 0) {
            Interval merged = merge(intervals.get(index), newInterval);
            intervals.remove(index);
            intervals.add(index, merged);
            insertAt(intervals, index+1, merged);
            insertAt(intervals, index-1, merged);
        } else  if(d > 0) {
            insertAt(intervals, index+1, newInterval);
        } else {
        	insertAt(intervals, index-1, newInterval);
        }
    }
    
    private int compare(Interval first, Interval second) {
        if (first.end < second.start) {
            return -1;
        } else if (first.start > second.end) {
            return 1;
        } else {
            return 0;
        }
    }
    private Interval merge(Interval first, Interval second) {
        int start = Math.min(first.start, second.start);
        int end = Math.max(first.end, second.end);
        return new Interval(start, end);
    }
}