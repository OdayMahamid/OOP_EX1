package ex1.tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

class Graph_Test {

	@Test ///the graph is empty
	void test0() {
		weighted_graph empty_graph = new WGraph_DS();
		weighted_graph_algorithms g = new  WGraph_Algo();
        g.init(empty_graph);
        boolean connect = g.isConnected();
        assertTrue(connect);
    }
	
    /**
     * one node
     */
	@Test
	void test1() {
		weighted_graph gr = new WGraph_DS();
		gr.addNode(1);
		gr.addNode(1); // the graph wont add this node because the key already exist..
		weighted_graph_algorithms g = new WGraph_Algo();
        g.init(gr);
        boolean connect = g.isConnected();
        assertTrue(connect);
    }
	
    /**
     * 
     */
	@Test////////graph with 2 vertices and there is not an edge,check save and load 
	void test2() {
		weighted_graph gr = new WGraph_DS();
		gr.addNode(2);
		gr.addNode(1);
		weighted_graph_algorithms g = new  WGraph_Algo();
        g.init(gr);
        boolean notconnect = g.isConnected();
        g.save("g"); ///////////////save the graph
        gr.connect(1,2,5);
        g.load("g");//////////////load to graph
        notconnect|=g.isConnected();
        assertFalse(notconnect);//////////////the graph is not connected because when I save the graph there is not an edge between 0 and 1
    }
    /**
     * graph with two vertices and a one edge - connected
     */
	@Test
	void test3() {
		weighted_graph gr = new WGraph_DS();
		gr.addNode(2);
		gr.addNode(1);
		gr.connect(2, 1, 41);
		weighted_graph_algorithms g = new  WGraph_Algo();
        g.init(gr);
        boolean connect = g.isConnected();
        assertTrue(connect);
    }
	
    /**
     * graph with 5 vertices 
     * test connectivity remove edge or node from the graph
     */
	@Test
	void test4() {
		boolean correct = true;
		boolean notcorrect = true;
		weighted_graph gr = new WGraph_DS();
		weighted_graph_algorithms g = new  WGraph_Algo();

		for (int i = 0; i < 5; i++) { // add 5 vertices to the graph
			gr.addNode(i);
		}
		gr.connect(0, 1, 41);
		gr.connect(0, 4, 32);
		gr.connect(1, 2, 21);
		gr.connect(2, 3, 8);
		gr.connect(3, 4, 5);
        g.init(gr);  
		correct = g.isConnected();
		gr.removeEdge(1, 2);
		notcorrect &= g.isConnected();
		gr.removeEdge(0, 4);
		notcorrect &= g.isConnected();
		gr.removeNode(0);
		notcorrect &= g.isConnected();
        assertTrue(correct);
        assertFalse(notcorrect);
    }
	
	
	/**
	 *  test for shortestpath and shortestpathdist
	 */
	@Test
	void test5() {
		boolean correct = true;
		weighted_graph gr = new WGraph_DS();
		weighted_graph_algorithms g = new  WGraph_Algo();
		List<node_info> expectpath = new LinkedList<node_info>();
		double dist =-1;
        g.init(gr);  
		// find path between vertices are not exist in the graph
		correct &= g.shortestPathDist(0, 1) == dist;
		correct &= g.shortestPath(1, 0) == null;
		
		// path with one node
		gr.addNode(0);
		dist = 0;
		expectpath.add(gr.getNode(0));
		correct &= g.shortestPathDist(0, 0) == dist;
		correct &= g.shortestPath(0, 0).equals(expectpath);  
        assertTrue(correct);
	}
	
	
    /**
     * graph with 5 vertices 
     * to check shortest path and shortestpathdist functions 
     *  with extra cases.
     */
	@Test
	void test6() {
		boolean correct = true;
		weighted_graph gr = new WGraph_DS();
		weighted_graph_algorithms g = new  WGraph_Algo();
		List<node_info> shortestPath = new LinkedList<node_info>();
		List<node_info> expectpath = new LinkedList<node_info>();
		double dist =0;
		for (int i = 0; i < 5; i++) { // add 5 vertices to the graph
			gr.addNode(i);
			expectpath.add(gr.getNode(i));
		}
		gr.connect(0, 1, 11);
		gr.connect(0, 4, 41);
		gr.connect(1, 2, 3);
		gr.connect(2, 3, 8);
		gr.connect(3, 4, 5);
		gr.connect(1, 3, 20);
		g.init(gr);//////////////init to graph
	    gr=g.copy();/////////////to check if the copy is work
	    g.init(gr);//////////////init the graph 
	    
        // path from 0 to 4   (0 -> 1 -> 2 -> 3 -> 4) 
         shortestPath = g.shortestPath(0, 4);
         dist = 27.0; // 11 + 3 + 8 + 5
         correct&= g.shortestPathDist(0, 4)==dist;
         correct &= shortestPath.equals(expectpath);
         
         expectpath = new LinkedList<node_info>();
         gr.connect(0, 4, 2); 
         expectpath.add(gr.getNode(0));
         expectpath.add(gr.getNode(4));
         expectpath.add(gr.getNode(3));
      //   path from 0 to 3   (0 -> 4 -> 3)
        shortestPath = g.shortestPath(0, 3);
        dist = 7.0; // 2 + 5
        correct &= g.shortestPathDist(0, 3) == dist;
       correct &= shortestPath.equals(expectpath);
  
       
        
        assertTrue(correct);
    }


}
