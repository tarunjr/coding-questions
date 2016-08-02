package org.learning.solutions;

public class Combinatorial {
	public static int choose(int n, int k){
		int numerator = 1;
		int denominator = 1;
		for(int i=0; i < k; i++){
			numerator *= (n-i);
			denominator *= (i+1);
		}
		return (int)(numerator/denominator);
	}
	public static int permute(int n, int k){
		int numerator = 1;
		
		for(int i=0; i < k; i++){
			numerator *= (n-i);
		}
		return numerator;
	}
}
