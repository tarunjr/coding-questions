package org.learning.solutions;

public class StringBigNumber {
	public String multiply(String a, String b){
		char[] first = reverse(a.toCharArray());
		char[] second = reverse(b.toCharArray());
		char[] result = new char[first.length * second.length+1];
		
		for(int i=0; i<result.length;i++)
			result[i] ='0';
		
		char[] rt = new char[second.length+1];
		
		for(int i=0; i < first.length;i++){
			for(int k=0; k < rt.length;k++)
				rt[k] ='0';
			
			char carry = '0';
			for(int j=0; j < second.length;j++) {
				char[] r1 = multiplyHelper(first[i], second[j]);
				char[] r2 = addHelper(r1[1], '0', carry);
				rt[j] = r2[1];
				carry = r1[0];
			}
			rt[second.length] = carry;
			addShift(result, i, rt, 0);
		}
		result = reverse(result);	
		return new String(result);
	}
	
	private char[] reverse(char[] carr){
		int i = 0;
		int j = carr.length-1;
		while(i < j){
			char t = carr[i];
			carr[i] = carr[j];
			carr[j] = t;
			i++;
			j--;
		}
		return carr;
	}
	
	private void addShift(char[] target, int start1,  char[] source, int start2){
		char carry = '0';
		for(int i=start2; i< source.length; i++){
			char a = target[start1+i];
			char b = source[i];
			char[] r = addHelper(a, b, carry);
			target[start1+i] = r[1];
			carry = r[0];
		}
		return;
	}
	private char[] addHelper(char a, char b, char carry){
		int result = (a-'0') + (b-'0') + (carry-'0');
		return formatTwoChar(result);
	}
	private char[] multiplyHelper(char a, char b){
		int result = (a-'0') * (b -'0');
		return formatTwoChar(result);
	}
	private char[] formatTwoChar(int number){
		char[] cr = {0,0};
		char[] t = Integer.toString(number).toCharArray();
		if(t.length == 2){
			cr[0] = t[0];
			cr[1] = t[1];
		} else {
			cr[1] = t[0];
		}
		return cr;
	}
}
