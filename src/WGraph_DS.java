package ex1.src;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class WGraph_DS implements weighted_graph,Serializable {
	private static final long serialVersionUID = 7483875478698488016L;
	private int MC;
	private int edgeSize;
	HashMap<Integer,node_info> Vertices;//vertices in graph
	
	
	@Override
	public node_info getNode(int key) {
		return this.Vertices.get(key);
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		if(getNode(node1)==null ||getNode(node2)==null) return false;
		return ((NodeInfo)getNode(node1)).getneighbors().containsKey(getNode(node2));//check if node2 is in hashmap if node1
	}
	@Override
	public double getEdge(int node1, int node2) {
		if(!hasEdge(node1,node2))return -1;
		if(getNode(node1)==null) return -1;
		return ((NodeInfo)getNode(node1)).getneighbors().get(getNode(node2));//return the value in hashmap of node1 that represent the weight
	}

	@Override
	public void addNode(int key) {
	    node_info n1= new NodeInfo(key);
	    Vertices.put(n1.getKey(), n1);//add to hashmap of vertices
		MC++;
	}
	

	@Override
	public void connect(int node1, int node2, double w) {
		if(getNode(node1)==null || getNode(node2)==null) return;
		
		if(node1==node2) return;
	
		if(!hasEdge(node1, node2)) { 
			edgeSize++;
		}
			// if there is not an edge between node1 and node2
       ((NodeInfo) getNode(node1)).getneighbors().put(getNode(node2),w);//add node2 to hashmap neighbors of node1
       ((NodeInfo) getNode(node2)).getneighbors().put(getNode(node1),w);//add node1 to hashmap neighbors of node2
       MC++;
		
	}

	@Override
	public Collection<node_info> getV() {
		return Vertices.values();
		
	}

	@Override
	public Collection<node_info> getV(int node_id) {
		Collection<node_info> V = new LinkedList<node_info>();
		if (getNode(node_id) != null) {
			if(((NodeInfo)getNode(node_id)).getneighbors()!=null){// if there is edges to this node
			for(node_info n : ((NodeInfo)getNode(node_id)).getneighbors().keySet()) {
				V.add(n);
			}
		}
		}
		else return null;
		return V;
		
	}
	

	@Override
	public node_info removeNode(int key) {
		node_info removedData = Vertices.get(key);
		if(removedData == null) {
			return removedData;
		}
		else {
			if(((NodeInfo) removedData).getneighbors()!= null){
				for(node_info temp : getV(key)) {
					removeEdge(key, temp.getKey());// remove the key from his neighbors
						 
				}
			}
			Vertices.remove(key);//remove the node
			MC++;
		}
		return removedData;
	}
	
	@Override
	public void removeEdge(int node1, int node2) {
		node_info n1 = Vertices.get(node1); // node 1
		node_info n2 = Vertices.get(node2); // node 2
		if(hasEdge(node1,node2)) {
			edgeSize--;
			MC++;
			((NodeInfo) n1).getneighbors().remove(n2);// remove node2 from neighbors of node1
			((NodeInfo) n2).getneighbors().remove(n1); // remove node1 from neighbors of node2
		}
	}  
	
	@Override
	public int nodeSize() {
		return Vertices.size();
	}

	@Override
	public int edgeSize() {
		return edgeSize;
	}

	@Override
	public int getMC() {
		return MC;
	}
	public WGraph_DS() {
		Vertices= new HashMap<Integer,node_info>();
	}
	public boolean equals(Object g1) {
		if(g1 instanceof weighted_graph) {
		WGraph_DS g0 = (WGraph_DS) g1;
		for(node_info n1 : this.getV()) {
			if(!g0.Vertices.containsValue(n1)) return false;
		}	
		for(node_info n1 : ((WGraph_DS) g1).getV()) {
			if(!this.Vertices.containsValue(n1)) return false;
	    }
	 	 if(!checkedges(this.Vertices,g0)||!checkedges(g0.Vertices,this)) return false;
		
		}	
		return true;
	}


	private boolean checkedges(HashMap<Integer,node_info> g1, WGraph_DS g2) {
		Collection<Integer> col1 = g1.keySet();
		for(int n : col1) {
			for(node_info n1 : ((NodeInfo) g1.get(n)).getneighbors().keySet()) {
				double weight = ((NodeInfo) g1.get(n)).getneighbors().get(n1);
				if(!g2.hasEdge(n, n1.getKey())||g2.getEdge(n, n1.getKey())!=weight) return false;
			}	
		}
		return true;
	}
}
	



