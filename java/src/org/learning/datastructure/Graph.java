package org.learning.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Graph<V> {
	private class Vertex {
		private V value;
		private Set<Vertex> edges;
		
		public Vertex(V value) {
			this.value = value;
			this.edges = new HashSet<Vertex>();
		}
		public void addEdge(Vertex toVertex){
			this.edges.add(toVertex);
		}
	}
	

	private List<Vertex> vertices;
	public Graph(){
		this.vertices = new ArrayList<Vertex>();
	}
	public void addEdge(V fromValue, V toValue)
	{
		Vertex fromVertex = findVertex(fromValue);
		if(fromVertex == null){
			fromVertex = new Vertex(fromValue);
			this.vertices.add(fromVertex);
		}
		
		Vertex toVertex = findVertex(toValue);
		if(toVertex == null){
			toVertex = new Vertex(toValue);
			this.vertices.add(toVertex);
		}
		fromVertex.addEdge(toVertex);
	}
	
	public boolean hasCycles(){
		Map<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		Map<Vertex, Vertex> cycleEdges = new HashMap<Vertex, Vertex>();
		
		for(Vertex v: this.vertices)
			visited.put(v, Boolean.FALSE);
	    
		List<Vertex> stack = new ArrayList<Vertex>();
		Boolean result =  cycleHelper(vertices.get(0),visited, stack,cycleEdges);
		
		return result;
	}
	private boolean cycleHelper(Vertex current, Map<Vertex, Boolean> visited, List<Vertex> stack, Map<Vertex, Vertex> cycleEdges){
		visited.put(current, Boolean.TRUE);
		stack.add(current);
		
		boolean hasCycle = false;
		for (Vertex adj: current.edges) {
			if (visited.get(adj) == Boolean.FALSE) {
				hasCycle = cycleHelper(adj, visited, stack,cycleEdges);
			}
			else if (stack.contains(adj)) {
				cycleEdges.put(current, adj);
				hasCycle = true;
			}
	    }
	    stack.remove(current);

		return hasCycle;
	}
	public List<V> bfs(){
		Queue<Vertex> discovered = new LinkedList<Vertex>();
		List<V> values = new ArrayList<>();
		
		discovered.add(this.vertices.get(0));
		
		while(!discovered.isEmpty()){
			Vertex current = discovered.remove();
			values.add((V)current.value);
		
			for(Vertex adj: current.edges)
				if(!discovered.contains(adj))
					discovered.add(adj);
		}
		return values;
	}
	public List<V> dfs(){
		List<V> values = new ArrayList<>();
		Map<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		for(Vertex v: this.vertices)
			visited.put(v, Boolean.FALSE);
		
		dfsHelper(this.vertices.get(0), values, visited);
		return values;
	}
	
	private void dfsHelper(Vertex u, List<V> results, Map<Vertex, Boolean> visited){
		if(visited.get(u) == Boolean.TRUE)
			return;
		results.add((V)u.value);
		visited.put(u, Boolean.TRUE);
		
		for(Vertex adj: u.edges)
			dfsHelper(adj, results, visited);
		
	}
	public List<V> topologicalSort(){
		List<V> sorted = new ArrayList<>();
		
		Map<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
		for(Vertex v: this.vertices)
			visited.put(v, Boolean.FALSE);
		
		for(Vertex v: this.vertices)
			if(!visited.get(v))
				dfsHelperToplogicalSort(this.vertices.get(0), sorted, visited);
		return sorted;
	}
	private void dfsHelperToplogicalSort(Vertex u, List<V> sorted, Map<Vertex, Boolean> visited){
		if(visited.get(u) == Boolean.TRUE)
			return;
		
		visited.put(u, Boolean.TRUE);
		
		for(Vertex adj: u.edges){
			dfsHelperToplogicalSort(adj, sorted, visited);
		}
		sorted.add((V)u.value);
	}
	private Vertex findVertex(V value) {
		for(Vertex v : this.vertices){
			if(v.value.equals(value) == true)
				return v;
		}
		return null;
	}

}
