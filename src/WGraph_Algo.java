package ex1.src;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WGraph_Algo implements weighted_graph_algorithms,Serializable{
	private static final long serialVersionUID = -8189817200935335646L;
	private weighted_graph gr;

	@Override
	public void init(weighted_graph g) {
		this.gr=g;
	}

	@Override
	public weighted_graph getGraph() {
		return this.gr;
	}

	@Override
	public weighted_graph copy() {
		weighted_graph graph = new WGraph_DS();
		for (node_info n : gr.getV()) {
			if (n != null) { // create new node for deep copy
				graph.addNode(n.getKey());
			}
		}
		for(node_info node : gr.getV()){
			for(node_info node2 : gr.getV(node.getKey())) {
					graph.connect(node.getKey(), node2.getKey(), gr.getEdge(node2.getKey(),node.getKey()));
				}
		}
		return graph;
	}
	
	

	@Override
	public boolean isConnected() {
		if (this.getGraph().nodeSize() == 0 || this.getGraph().nodeSize() == 1) {//if there is not vertices or there is one vertex it's mean that the graph is connected
			return true;
		}
		node_info first = gr.getV().iterator().next();//to find the first node
		for(node_info n1 : gr.getV()) {
			n1.setInfo(" ");
		}
		VisitVertex(first,first);
		for(node_info n : gr.getV()) {
			if(!n.getInfo().equals("1"))//the vertex is not visited
				return false;
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if(src==dest)return 0;
		if(gr.getNode(src)==null || gr.getNode(dest)==null)return -1;
		dijkstra(src, dest);//to find the minimum weight 
		if(((NodeInfo)gr.getNode(dest)).getWeight()==Double.POSITIVE_INFINITY) return -1;
		return ((NodeInfo)gr.getNode(dest)).getWeight();//return minimum weight
	}
	
	

	@Override
	public List<node_info> shortestPath(int src, int dest) {
		List<node_info> path = new LinkedList<node_info>();
		node_info temp = gr.getNode(dest);
		if(src==dest) {
			path.add(temp);
			return path;
		}
		
		if(gr.getNode(src)==null || gr.getNode(dest)==null)return null;
		dijkstra(src, dest);
		try {
			while (temp.getKey() != src) {// begin from dest-node to src-node
				path.add(temp);
				temp = gr.getNode((int)temp.getTag());
			}
			path.add(temp);// to add src node
			int index = path.size() - 1;
			List<node_info> rev = new LinkedList<node_info>();
			rev.add(temp);
			index--;
			while (index != 0) {
				rev.add(path.get(index));
				index--;
			}
			rev.add(path.get(0));
			return rev;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@Override
	public boolean save(String file) {
		try
		{    
			FileOutputStream file_save = new FileOutputStream(file); 
			ObjectOutputStream out = new ObjectOutputStream(file_save); 
			out.writeObject(this.gr);  
			out.close(); 
			file_save.close();
			return true;
		}   
		catch(IOException ex) 
		{ 
			return false;
		}
	}
	
	@Override
	public boolean load(String file) {
		try
		{    
			FileInputStream file_in = new FileInputStream(file); 
			ObjectInputStream in = new ObjectInputStream(file_in); 
			gr = (weighted_graph) in.readObject(); 
			in.close(); 
			file_in.close(); 
			return true;
		} 

		catch(IOException ex) 
		{ 
			return false;
		} 

		catch(ClassNotFoundException ex) 
		{ 
			return false;
		}
		
	}

	
	public WGraph_Algo(weighted_graph g) {
		this.gr=g;
	}
	public WGraph_Algo() {
		this.gr=new WGraph_DS();
		
	}
	
	
	///////////////private function//////////
	
	
	private void VisitVertex(node_info n, node_info start) {
		if (gr.getV(n.getKey()) != null) {
			for (node_info ni : gr.getV(n.getKey())) {
				if (!gr.getNode(ni.getKey()).getInfo().equals("1")) {
					n.setInfo("1");
					gr.getNode(ni.getKey()).setInfo("1");
					VisitVertex(gr.getNode(ni.getKey()), start);//do recursivly to neighbour of the node
				}
			}
		}
	}
	
	
	
	private void dijkstra(int src, int dest) {
		for(node_info n1 : gr.getV()) {
			((NodeInfo)n1).setTag(-1);
			((NodeInfo)n1).setWeight(Double.POSITIVE_INFINITY);
			((NodeInfo)n1).setVisited(false);
		}
		Collection<node_info> ni = gr.getV();
		MinHeap h = new MinHeap();
		((NodeInfo) gr.getNode(src)).setWeight(0);
		for (node_info n : ni) {
			h.insert(n);//add vertices to heap
		}
		while (h.getList().size() != 0) {
			node_info u = (node_info) h.extractMin();
			if (gr.getV(u.getKey()) != null) {
			Collection<node_info> ni2 = gr.getV(u.getKey());
			for (node_info n : ni2) {
			node_info v = (NodeInfo)gr.getNode(n.getKey());
			if (!((NodeInfo)v).isVisited()) {
			double w = ((NodeInfo) u).getWeight() + gr.getEdge(v.getKey(), u.getKey());
				if (w < ((NodeInfo) v).getWeight()) {
					((NodeInfo) v).setWeight(w);
					v.setTag(u.getKey());// to know the previous node with the lowest cost
					h.buildHeap();
						}
					}
				}
				((NodeInfo) u).setVisited(true);
			}
		}
	}
	
}
