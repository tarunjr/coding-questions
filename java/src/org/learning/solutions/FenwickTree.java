package org.learning.solutions;

public class FenwickTree {
	private int[] data;
	private int[] tree;
	private int size;
	
	public FenwickTree(int[] data){
		this.data = data;
		this.size = data.length;
		this.tree = new int[size+1];
		for(int i=0; i<=size;i++)
			this.tree[i] = 0;
		
		build();
	}
	private void build(){
		for(int i=0; i<size;i++){
			int j = i+1;
			int value = this.data[i];
			while(j <= size){
				this.tree[j] += value;
				j = getNext(j);
			}
		}
	}
	public void update(int index, int value){
		int i = index+1;
		int delta = value-this.tree[i];
		while(i <= size){
			this.tree[i] += delta;
			i = getNext(i);
		}
	}
	private int getParent(int i){
		return i -= i & -i;
	}
	private int getNext(int i){
		return i += i & -i;
	}
	public int getSum(int index){
		int sum = 0;
		int i = index+1;
		while(i > 0){
			sum += this.tree[i];
			i = getParent(i);
		}
		return sum;
	}
}
