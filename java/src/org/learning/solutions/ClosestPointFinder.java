package org.learning.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClosestPointFinder {
	private class Point{
		private int x;
		private int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private class PointPair {
		public Point a;
		public Point b;
		double distance;
		public PointPair(Point a, Point b, double d) {
			this.a = a;
			this.b = b;
			this.distance = d;
		}
	}
	private class PointComparator implements Comparator<Point> {
		private boolean firstOperand;
		public PointComparator(boolean firstOperand) {
			this.firstOperand = firstOperand;
		}
		@Override
		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			if(firstOperand)
				return Integer.compare(o1.x, o2.x);
			else
				return Integer.compare(o1.y, o2.y);
		}
		
	}
	
	public int[][] find(int[][] input) {
		List<Point> points = new ArrayList<Point>();
		for(int[] n: input) {
			Point p = new Point(n[0], n[1]);
			points.add(p);
		}
		
		PointPair pp = findHelper(points, true);
	
		int[][] result= new int[2][2];
		result[0][0] = pp.a.x;
		result[0][1] = pp.a.y;
		result[1][0] = pp.b.x;
		result[1][1] = pp.b.y;
		
		return result;
	}
	public PointPair findHelper(List<Point> points, boolean axis) {
		Collections.sort(points, new PointComparator(axis));
		if(points.size() <= 3) {
			//return bruteForce(points);
		
		}
		int mid = points.size() / 2 ;
		List<Point> left = points.subList(0, mid);
		List<Point> right =  points.subList(mid, points.size());
		
		PointPair lpp = findHelper(left,!axis);
		PointPair rpp = findHelper(left,!axis);
		
		return null;
	
		
	}
}
