package org.learning.solutions;

public class Regex {
	private static final String SINGLE_NUMERIC_PATTERN  = "n";
	public boolean match(String s, String r) {
		return matchHelper(s.toCharArray(), 0, s.length()-1, r.toCharArray(), 0, r.length()-1);
	}
	private boolean matchHelper(char[] source, int s1, int e1, char[] pattern, int s2, int e2) {
		if(s2 == e2)
			return true;
		
		int i1 = s1;
		int i2 = s2;
		
		if(pattern[i2] == 'n') {
			if (Character.isDigit(source[i1])) {
				i1++;
				if (e2 > s2 && pattern[i2+1] == '+') {
					while (Character.isDigit(source[i1]) ) {
						if (matchHelper(source, i1+1, e1, pattern, i2+2, e2) == true)
							break;
						i1 += 1;
					}
				}
			} 
			else {
				return false;
			}
		} else if (pattern[i2] == '.') {
			if(pattern[i2+1] == '?') {
				
			}
		} else if (pattern[i2] == 'e' || pattern[i2] == 'E') {
			
		}
		return false;
	}
}
