package org.learning.solutions;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;



public class StringSegmenter {
	public List<String> segment(String source, Set<String> dict){
		List<String> results = new ArrayList<String>();
		int len = source.length();
		if(len == 0)
			return results;
		
		int i = 0;
		int j = i+1;
		
		while(j <= len){
			String token = source.substring(i, j);
			if(dict.contains(token)){
				i = j;
				results.add(token);
			}
			j++;
		}
		if(i == len)
			return results;
		else{
			return new ArrayList<String>();
		}
	}
	public String segmentRecurse(String input, Set<String> dict, Set<String> tokens) {
		 
		  if (dict.contains(input)) return input;
		  tokens.add(input);
		  int len = input.length();
		  for (int i = 1; i < len; i++) {
		    String prefix = input.substring(0, i);
		    tokens.add(prefix);
		    if (dict.contains(prefix)) {
		      String suffix = input.substring(i, len);
		      String segSuffix = segmentRecurse(suffix, dict, tokens);
		      if (segSuffix != null) {
		        return prefix + " " + segSuffix;
		      }
		    }
		  }
		  return null;
	 }
	public String segmentRecurseMemo(String input, Set<String> dict, Set<String> tokens, Map<String,String> memo) {
		  
		  if (dict.contains(input)) return input;
		  if(memo.containsKey(input)) return memo.get(input);
		  tokens.add(input);
		  int len = input.length();
		  for (int i = 1; i < len; i++) {
		    String prefix = input.substring(0, i);
		    tokens.add(prefix);
		    if (dict.contains(prefix)) {
		      String suffix = input.substring(i, len);
		      String segSuffix = segmentRecurseMemo(suffix, dict, tokens, memo);
		      if (segSuffix != null) {
		    	  memo.put(input, prefix + " " + segSuffix);
		    	  return prefix + " " + segSuffix;
		      }
		    }
		  }
		  memo.put(input, null);
		  return null;
	 }
}
