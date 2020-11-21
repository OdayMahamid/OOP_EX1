# OOP_EX1
In this project I build weighted undirected graph.

1.File list:
node_info :interface that represent the set of operations applicable on a node (vertex) in weighted undirectional graph.

NodeInfo : class that implements "node_info" interface.

weighted_graph: interface that represent an undirectional weighted graph.

WGraph_DS :class that implements "weighted_graph" interface.

weighed_graph_algorithms:interface represents the "regular" Graph Theory algorithms(shrotest path,check if the graph is connected).

WGraph_Algo: class that implements "weighted_graph_algorithms" interface.


2.Algorithms:
Dijkstra's algorithm (or Dijkstra's Shortest Path First algorithm, SPF algorithm) 
is an algorithm for finding the shortest paths between nodes in a graph.

Analysis for dijkstra's:
a.Mark all nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.

b.Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes.
 Set the initial node as current.

c.For the current node, consider all of its unvisited neighbours and calculate their tentative distances through the current node. 
Compare the newly calculated tentative distance to the current assigned value and assign the smaller one

d.When we are done considering all of the unvisited neighbours of the current node, 
mark the current node as visited and remove it from the unvisited set. 
A visited node will never be checked again.

e.If the destination node has been marked visited 
(when planning a route between two specific nodes) or if the smallest tentative distance among the nodes
 in the unvisited set is infinity (when planning a complete traversal; 
occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.

f.Otherwise, select the unvisited node that is marked with the smallest tentative distance, 
set it as the new "current node", and go back to step e.



3.Data structures:
in NodeInfo class : "HashMap<node_info,Double>" represent the neighbors of each vertex and the weight ,
where the key of HashMap represent all neghbours and the value of HashMap is the weight between the vertex and his neighbour.

in Graph_DS class : HashMap<Integer,node_info>  represents the vertices in the graph, the key of HashMap is the node id of the node_data.



4.graph_algorithms:
isConnected : same as bfs in assignment 0 but I do a recursive help function to visit all vertices in 
graph and check if we visit all vertices if one of vertices is not visited then the graph is not connected otherwise the graph is connected.

shortestpath: according to dijistra, the explain is(a-f).

shortestpathdist: doing dijkstra's between the src and the dest and return the weight of the vertex dest.
