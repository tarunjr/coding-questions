package org.learning.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalMerge {
	
	public class IntervalCompator implements Comparator<Interval> {
        public int compare(Interval l, Interval r) {
            return l.start - r.start;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() == 0 || intervals.size() == 1)
            return intervals;
            
        Collections.sort(intervals, new IntervalCompator());
        List<Interval> result = new ArrayList<Interval>();
        
        result.addAll(mergePair(intervals.get(0), intervals.get(1)));
        
        for(int i=2; i < intervals.size();i++) {
            Interval first = result.remove(result.size()-1);
            List<Interval> r = mergePair(first, intervals.get(i));
            result.addAll(r);
        }
        
        return result;
    }
    private List<Interval> mergePair(Interval l, Interval r) {
        List<Interval> merged = new ArrayList<Interval>();
        if(l.end < r.start) {
            merged.add(l);
            merged.add(r);
           
        } else if (l.end >= r.end ) {
            merged.add(l);
        } else if (l.end >= r.start) {
            merged.add(new Interval(l.start, r.end));
        }
        return merged;
    }
}
