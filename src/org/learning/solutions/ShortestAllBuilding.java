package org.learning.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestAllBuilding {
	public class AddressKey{
		public AddressKey(int x, int y){
			this.X = x;
			this.Y = y;
		}
		public int X;
		public int Y;
	}
	private int layout[][];
	private int width;
	private int height;
	
	public ShortestAllBuilding(){
		this.layout = null;
		this.width = this.height = 0;
	}
	public AddressKey findAddress(int[][] layout){
		this.layout = layout;
		this.height = layout.length;
		this.width = layout[0].length;
		
		List<AddressKey> houses = new ArrayList<AddressKey>();
		List<AddressKey> plots = new ArrayList<AddressKey>();
		
		for(int j=0;j < height; j++)
			for(int i=0; i < width;i++){
				if(this.layout[j][i] == 1)
					houses.add(new AddressKey(i,j));
				else if(this.layout[j][i] == 0)
					plots.add(new AddressKey(i,j));
			}
		int minDistance = Integer.MAX_VALUE;
		AddressKey  bestPlot = null;
		for(AddressKey plot: plots){
			int distance = minDistanceForPlot(plot, houses);
			if(distance < minDistance) {
				minDistance = distance;
				bestPlot = plot;
			}	
		}
		return bestPlot;
	}
	
	private int minDistanceForPlot(AddressKey plot, List<AddressKey> houses) {
		// Data structure to remember visited units.
		// Calculate and remember the distance from unit 'plot'
		boolean[][] visited = new boolean[this.height][this.width];
		int[][] distance = new int[this.height][this.width];
		for(int j=0;j < this.height; j++)
			for(int i=0; i < this.width;i++){
				visited[j][i] = false;
				distance[j][i] = Integer.MAX_VALUE;
			}
		
		Queue<AddressKey> discovered = new LinkedList<AddressKey>();
		discovered.add(plot);
		distance[plot.Y][plot.X] = 0;
		
		while(!discovered.isEmpty()){
			AddressKey unit = discovered.remove();
			visited[unit.Y][unit.X] = true;
			List<AddressKey> neighbours = getAdjacentUnits(unit);
			for(AddressKey adjUnit: neighbours){
				if(visited[adjUnit.Y][adjUnit.X])
					continue;
				distance[adjUnit.Y][adjUnit.X] = distance[unit.Y][unit.X] + 1;
				if(isHome(adjUnit) || isBlock(adjUnit)){
					visited[adjUnit.Y][adjUnit.X] = true;
				}else{
					discovered.add(adjUnit);
				}
			}
		}
		int totalDistance = 0;
		for(AddressKey house: houses){
			int d = distance[house.Y][house.X];
			totalDistance+=d;
		}
		return totalDistance;
	}
	private boolean isHome(AddressKey unit){
		return (this.layout[unit.Y][unit.X] == 1);
	}
	private boolean isBlock(AddressKey unit){
		return (this.layout[unit.Y][unit.X] == 2);
	}
	private boolean isPlot(AddressKey unit){
		return (this.layout[unit.Y][unit.X] == 0);
	}
	private List<AddressKey> getAdjacentUnits(AddressKey unit){
		List<AddressKey> adjUnits = new ArrayList<AddressKey>();
		int x = unit.X;
		int y = unit.Y;
		int y1 = Math.min(this.height-1, y+1);
		int y2 = Math.max(0, y-1);
		int x1 = Math.min(this.width-1, x+1);
		int x2 = Math.max(0, x-1);
		if(y1 != y)
			adjUnits.add(new AddressKey(x, y1));
		if(y2 != y)
			adjUnits.add(new AddressKey(x, y2));
		if(x1 != x)
			adjUnits.add(new AddressKey(x1, y));
		if(x2 != x)
			adjUnits.add(new AddressKey(x2, y));
		
		return adjUnits;
	}
}