package org.learning.solutions;


public class FenewickTreeMin {
	private int[] data;
	private int[] tree;
	private int size;
	
	public FenewickTreeMin(int[] data){
		this.data = data;
		this.size = data.length;
		this.tree = new int[size+1];
		for(int i=0; i<=size;i++)
			this.tree[i] = Integer.MAX_VALUE;
		
		build();
	}
	private void build(){
		for(int i=0; i<size;i++){
			int j = i+1;
			int value = this.data[i];
			while(j <= size){
				//this.tree[j] += value;
				this.tree[j] = Math.min(this.tree[j], value);
				j = getNext(j);
			}
		}
	}
	public void update(int index, int value){
		int i = index+1;
		int min = Math.min(value,this.tree[i]);
		while(i <= size){
			//this.tree[i] += delta;
			this.tree[i] = Math.min(this.tree[i], min);
			i = getNext(i);
		}
	}
	private int getParent(int i){
		return i -= i & -i;
	}
	private int getNext(int i){
		return i += i & -i;
	}
	public int getMin(int index){
		int min = Integer.MAX_VALUE;
		int i = index+1;
		while(i > 0){
			min = Math.min(min, this.tree[i]);
			i = getParent(i);
		}
		return min;
	}
}