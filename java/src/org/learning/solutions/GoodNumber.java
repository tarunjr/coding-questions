package org.learning.solutions;

import java.util.ArrayList;
import java.util.List;

public class GoodNumber {
	public List<Integer> getValidNumbers(int number){
		int aMin = 0;
		int bMax = (int)Math.floor(Math.pow(number, (1.0/3.0)));
		int i = aMin;
		int j = bMax;
		int nTemp = 0;

		List<Integer> goodNumbers = new ArrayList<Integer>();
		System.out.println("Number:" + number);
		while(i < j){
			nTemp = (int)(Math.pow(i, 3) + Math.pow(j, 3));
			if(nTemp == number){
				goodNumbers.add(nTemp);
				break;
			}
			else if(nTemp < number){
				goodNumbers.add(nTemp);
				i++;
			}
			else
				j--;
		}	
		return goodNumbers;
	}
}
