package ex1.src;
import java.io.Serializable;
import java.util.HashMap;

public class NodeInfo implements node_info,Serializable {
	private static final long serialVersionUID = 7117374457011215713L;
	private static int k=0;
	private int key;
	private double tag;
	private double weight;
	private String info=" ";
	private HashMap<node_info,Double> neighbors;//neighbors and weight for each vertex
	private boolean visited;//to check if visit the vertex

	
///////////////getters and setters////////////////
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public String getInfo() {
	return info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
		
	}

	@Override
	public double getTag() {
		return tag;
	}

	@Override
	public void setTag(double t) {
	this.tag=t;
		
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double w) {
		this.weight = w;
	}

	public HashMap<node_info,Double> getneighbors(){
		return this.neighbors;
	}
	public void setneighbors(HashMap<node_info,Double> ne) {
		this.neighbors=ne;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	////////////constructors///////////
	public NodeInfo() {
		key=k;
		NodeInfo.k++;
		this.info = "";
		this.tag = -1;
		this.neighbors = new HashMap<node_info,Double>();
	}
	public NodeInfo(int key) {
		neighbors = new HashMap<node_info,Double>();
		this.key=key;
		this.weight = Double.POSITIVE_INFINITY;
		this.tag = -1;
		this.setVisited(false);
	}
	public NodeInfo(node_info n) {
		 neighbors = ((NodeInfo) n).getneighbors();
		this.key=n.getKey();
		this.weight = ((NodeInfo) n).getWeight();
		this.tag = n.getTag();
		this.setVisited(((NodeInfo) n).isVisited());
	}
	@Override
	public String toString() {
		return key+"";
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NodeInfo) {
			return ((NodeInfo) obj).getKey()==key;
		}
		return false;
	}

}
