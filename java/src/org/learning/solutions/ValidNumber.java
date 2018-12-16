package org.learning.solutions;

public class ValidNumber {
	private static final String regex = "d+";
	public static boolean isNumber(String s) {
		return isMatch(s.toCharArray(),0, regex.toCharArray(), 0);
	}
	public static boolean isMatch(char[] s,int sStart,  char[] r, int rStart) {
		// Case 0: everything matches an empty pattern
		if(r.length == rStart && s.length == sStart) {
			return true;
		}
		
		char r0 = r[rStart];
		char r1 = r[rStart+1];
		char s0 = s[sStart];
		
		if(r0 == 'd' && r1 == '*') {
			for(int i=sStart; i < s.length; i++) {
				if (isDigit(s[i]) && !isMatch(s, sStart+i, r, rStart+2)) {
					continue;
				} else {
					
				}
			}
			return false;
		} else if (r0 == 'd') {
			if(!isDigit(s0))
				return false;
			return false;
		}
		return false;
	}
	private static boolean isDigit(char s) {
		if(s >= '0' && s <= '9')
			return true;
		else
			return false;
	}
}
