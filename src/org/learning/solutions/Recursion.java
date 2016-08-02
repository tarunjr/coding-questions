package org.learning.solutions;

public class Recursion {
	public boolean recurse(int n){
		return recursionHelper(n,2);
	}
	private boolean recursionHelper(int n,int x){
		if(n == x)
			return true;
		if(n % x == 0)
			return false;
		return recursionHelper(n,x+1);
	}
}
