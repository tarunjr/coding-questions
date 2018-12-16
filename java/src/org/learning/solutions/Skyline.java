package org.learning.solutions;

import java.util.ArrayList;
import java.util.List;

public class Skyline {
	private class Point {
		public final int x;
		public final int y;
		public Point(int x, int y) {
			this.x =x;
			this.y = y;
		}
		public int equalsX(Point that) {
			return this.x - that.y;
		}
		public int	equalsY(Point that){
			return this.y-that.y;
		}
	}
	public int[][] getSkyline(int[][] buildings){
		List<Point> skyline = new ArrayList<Point>();
		for(int[] building: buildings){
			skyline = merge(skyline, building);
		}
		
		int[][] result = new int[skyline.size()][2];
		for(int i=0; i < skyline.size(); i++) {
			result[i][0] = skyline.get(i).x;
			result[i][1] = skyline.get(i).y;
		}
		return result;
	}
	private List<Point> merge(List<Point> target, int[] building){
		List<Point> merged = new ArrayList<Point>();
		List<Point> source = getBuildingSkyline(building);
		if(target.size() == 0) {
			merged.addAll(source);
			return merged;
		}
		
		Point sourceStart = source.get(0);
		Point sourceEnd = source.get(source.size()-1);
		
		if(sourceStart.equalsX(target.get(target.size()-1)) > 0){
			merged.addAll(target);
			merged.addAll(source);
			return merged;
		}
		
		int i = 0;
		
		
		for(; i < target.size(); i++) {
			Point p = target.get(i);
			if(p.equalsX(sourceStart) < 0) {
				merged.add(p);
			} else if(p.equalsX(sourceStart) >= 0) {
				if(p.equalsY(sourceStart) >= 0) {
					merged.add(p);
				} else {
					merged.add(sourceStart);
				}
				break;
			} 
		}
		
		for(i++; i < target.size(); i++) {
			Point p = target.get(i);
			if(p.equalsX(sourceStart) > 0 && p.equalsX(sourceEnd) < 0) {
				if(p.equalsY(sourceStart) > 0) {
					merged.add(p);
				} 
			} else if(p.equalsX(sourceEnd) == 0) {
				if(p.equalsY(sourceEnd) > 0) {
					merged.add(p);
				} 
			} else {
				merged.add(p);
			}
		}
		if(sourceEnd.equalsX(target.get(target.size()-1)) > 0 ){
			merged.add(sourceEnd);
		}

		return merged;
	}
	private List<Point> getBuildingSkyline(int[] building){
		List<Point> skyline = new ArrayList<Point>();
		if(building.length == 3){
			skyline.add(new Point(building[0],building[2]));
			skyline.add(new Point(building[1],0));
		}
		return skyline;
	}
}
