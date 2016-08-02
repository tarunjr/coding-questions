package org.learning.solutions;

import java.util.Arrays;

public class SegmentTree {
	private int[] tree;
	private int[] numbers;
	private int n;
	public void build(int[] numbers){
		this.numbers = numbers;
		this.n=numbers.length;
		
		int height = (int)Math.ceil(Math.log(this.n)/Math.log(2));
		int size = (int) (2*Math.pow(2, height)-1);
		this.tree = new int[size];
		for(int i=0; i< size;i++)
			this.tree[i] = -1;
		buildRecursive(1, 0, n-1);
		System.out.println(Arrays.toString(this.tree));
	}
	private void buildRecursive(int nodeNumber, int start, int end){
		if(start == end){
			this.tree[nodeNumber-1] = this.numbers[start];
			return;
		}
		int mid = (start+end)/2;
		buildRecursive(2*nodeNumber, start, mid);
		buildRecursive(2*nodeNumber+1, mid+1, end);
		
		this.tree[nodeNumber-1] = this.tree[2*nodeNumber-1] + this.tree[2*nodeNumber];
		return;
	}
	public int query(int l, int r){
		return queryHelper(1, 0, n-1, l, r);
	}
	private int queryHelper(int nodeIndex, int start, int end, int l, int r){
		
		if(r < start || l > end)		// query range is outside of the segment
			return 0;
		else if(l <= start && end <= r)	// segment is completely inside query range
			return this.tree[nodeIndex-1];
		else{
			int mid = (start + end)/2;
			return queryHelper(2*nodeIndex,start, mid, l, r ) + 		// Left subtree
				   queryHelper(2*nodeIndex+1,mid+1, end, l, r );		// right subtree
		}
	}
	public void update(int index, int value){
		updateHelper(1,0, this.n-1, index, value);
		System.out.println(Arrays.toString(this.tree));
	}
	private void updateHelper(int nodeIndex, int start, int end, int index, int value)
	{
		if(start == end){
			this.tree[nodeIndex-1] = value;
			return;
		}
		int mid = (start+end)/2;
		if(index >= start && index <= mid)
			updateHelper(2*nodeIndex, start, mid, index, value);
		else if(index > mid && index <= end)
			updateHelper(2*nodeIndex+1, mid+1, end, index, value);
		
		this.tree[nodeIndex-1] = this.tree[2*nodeIndex-1] + this.tree[2*nodeIndex];
	}
}
