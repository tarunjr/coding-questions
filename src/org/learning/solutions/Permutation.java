package org.learning.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {
	public List<List<Integer>> permutations(int[] source) {
		List<Integer> s = new ArrayList<Integer>();
		for(int n: source) 
			s.add(n);
		List<List<Integer>> permuts = new ArrayList<List<Integer>>();
		permutationHelper(s, 0, permuts);
		return permuts;
	}
	private  void permutationHelper(List<Integer> source, int i, List<List<Integer>> permuts) {
		if(source.size() == i) {
			permuts.add(new ArrayList<Integer>(source));
			return;
		}
		Set<Integer> uniq = new HashSet<Integer>();
		for (int j=i;j < source.size(); j++) {
			if (uniq.contains(source.get(j))) {
				continue;
			} else {
				uniq.add(source.get(j));
			}
			
			swap(source, i, j);
			permutationHelper(source, i+1, permuts);
			swap(source, i, j);
		}
	
		return;
	}
	private void swap(List<Integer> n, int i, int j) {
		Integer t = n.set(i, n.get(j));
		n.set(j, t);
	}
	public static int[] nextPermutation(int[] permut) {
		if (permut.length < 2) {
			return permut;
		}
			
		int[] next = permut.clone();
		int k = next.length-2;
		
		// find the element k such that its p[k+1..... size-1] is the longest decreasing suffix 
		while(k >= 0 && next[k] > next[k+1]) {
			k--;
		}
		if (k == -1) {
			return new int[0];
		}
		// find l such that permut[l] is the smallest number greater than permut[k] in the suffix
		int l = k+1;
		for(int i=k+1; i < next.length; i++) {
			if (next[i] > next[k]) {
				l = i;
			} else  {
				break;
			}
		}
		swap(next, k, l);
		reverse(next, k+1, next.length-1);
		return next;
	}
	public static int[] prevPermutation(int[] permut) {
		if (permut.length < 2) {
			return permut;
		}
			
		int[] next = permut.clone();
		int k = next.length-2;
		
		// find the element k such that its p[k+1..... size-1] is the longest increasing suffix 
		while(k >= 0 && next[k] < next[k+1]) {
			k--;
		}
		if (k == -1) {
			return new int[0];
		}
		reverse(next, k+1, next.length-1);
		// find l such that permut[l] is the largest number smaller than permut[k] in the suffix
		int l = k+1;
		for(int i=k+1; i < next.length; i++) {
			if (next[i] > next[k]) {
				//
			} else  {
				l = i;
				break;
			}
		}
		swap(next, k, l);
		
		return next;
	}
	private static void reverse(int[] next, int i, int j) {
		while(i < j) {
			swap(next, i, j);
			i++;
			j--;
		}
	}
	private static void swap(int[] next, int k, int l) {
		int temp = next[k];
		next[k] = next[l];
		next[l] = temp;
	}
}
