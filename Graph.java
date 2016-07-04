import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Analyzes the given file and computes biggest
 * connected node spanning tree.
 * @author Pawel
 *
 */
public class Graph {
  
  //Construct an empty graph.
  public Graph() {
    adjacencyLists = new ArrayList<ArrayList<Arc>>();
    nodes = new ArrayList<Node>();
    nodeMarks = new ArrayList<Boolean>();
    numArcs = 0;    
  }
  
  // Add a node to this graph.
  public void addNode(Node node) {
    nodes.add(node);
    adjacencyLists.add(node.id, new ArrayList<Arc>());
  }
  
/**
 * New node is added.
 */
  public void addNode() {
    Node nd = new Node(nodes.size(), 0, 0);
    nodes.add(nd);
    adjacencyLists.add(nd.id, new ArrayList<Arc>());
  }
  /**
   * Add new arc to the sourceNode.
   * @param sourceNode Node with outgoing edge.
   * @param arc Object containing target node.
   */
  public void addArc(int sourceNode, Arc arc) {
    adjacencyLists.get(sourceNode).add(arc);
    numArcs++;   
  }
  /**
   * Function converting the incoming file into node and arc objects.
   * @param fileName name of the file to be parsed.
   */
  public void readFromFile(String fileName) {
    BufferedReader inputstream = null;
    try {
      inputstream = new BufferedReader(new FileReader(new File(fileName)));
      String currentLine;
      while ((currentLine = inputstream.readLine()) != null) {  
        // loop till the next line is empty
        String[] data = currentLine.split(" ");
        
        if (data.length == 3) { 
          //in this case according to the assumption
          //
          int nid = Integer.parseInt(data[0]);
          float nlatitude = Float.parseFloat(data[1]);
          float nlongitude = Float.parseFloat(data[2]);           
          this.addNode(new Node(nid, nlatitude, nlongitude));
        }
        else if (data.length == 4) {
       // "<tail node id> <head node id> <distance in meters> <max speed (in km/h)>"
          int srcnodeid = Integer.parseInt(data[0]);
          int destnodeid = Integer.parseInt(data[1]);
          int distance = Integer.parseInt(data[2]);
          //float cost = Integer.parseInt(data[2]);
          int maxspeed = Integer.parseInt(data[3]);
          
          this.addArc(srcnodeid, new Arc(srcnodeid, destnodeid, distance, maxspeed));
        }
        else {
        }
      }                      
    } catch (IOException error) {
      error.printStackTrace();
    } finally {
      try {
        inputstream.close();
      } catch (IOException error) {
        error.printStackTrace();
      }
    }   
  }
  
/**
 * Functions getting number of nodes.
 * @return number of nodes.
 */
  public int getNumNodes() { 
    return nodes.size();
  }
  
/**
 * Simple function returning number of arcs.
 * @return number of arcs.
 */
  public int getNumArcs() {
    return numArcs;
  }
  
/**
 * Looks in a BFS fashion for any reachable node starting from
 * the input node.
 * @param nodeId node indicating beginning of the search.
 * @return list of reachable nodes.
 */
  public ArrayList<Boolean> computeReachableNodes(int nodeId) {
  //function modified: when I create a boolean array list within
  //the performance of the program seems to improve.
    for (Node n: nodes) {
      this.nodeMarks.add(false);
    }
    ArrayList<Integer> current = new ArrayList<Integer>();
    ArrayList<Integer> next = new ArrayList<Integer>();     
    current.add(nodeId);
    nodeMarks.set(nodeId, true);
    for (Arc arc : adjacencyLists.get(nodeId)) {  
      if(!nodeMarks.get(arc.headNodeId)) {
        next.add(arc.headNodeId);
        nodeMarks.set(arc.headNodeId, true);        
      }
    //in the lines above two lists are filled with initial values
    //(given ID and its children) so that the condition to run
    // while loop is fulfilled
    }  
    while(!current.isEmpty() && !next.isEmpty()) {  
      current.clear();current.addAll(next);
      next.clear();
      //next is always copied to current.
      //they could interchange the roles (in next run current is next
      //and vice versa but it was omitted to keep the code clean)
      for (Integer k : current) {       
        //here every entry from the current list is taken
        //and arcs from it are taken into consideration
        for (Arc arc : adjacencyLists.get(k)) {         
          if(!nodeMarks.get(arc.headNodeId)) {
            //if the arc node hasn't been visited yet
            //it's added to the next list and marked as visited
            next.add(arc.headNodeId);
            nodeMarks.set(arc.headNodeId, true);
          }
        }           
      }             
    }     
    return nodeMarks;
  }
  
  
  /**
   * Converts data to a readable string.
   */
  public String toString() {
    String output = "{" + nodes.size() + "; " + numArcs;
    for (int i = 0; i < nodes.size(); i++) {    
      //iterates through every node index
      for (Arc arc : adjacencyLists.get(i)) {   
        output += "; (" + i + "," + arc.headNodeId + ")";
      }    
    }
    output += "}";
    return output;
  }

  public int computeLargestConnectedComponent() {
    
    for (Node n: nodes) {
      this.nodeMarks.add(Boolean.FALSE);
    }
    
    //used to store nodes that have been visited in all runs
    ArrayList<Boolean> allVisited = new ArrayList<Boolean>(); 
    allVisited.addAll(this.nodeMarks);
    //used to store nodes visited in one run
    ArrayList<Boolean> temp = new ArrayList<Boolean>();
    temp.addAll(this.nodeMarks);
    
    int currentSize = 0; //current number of the biggest connected component
    int newSize = 0;
    for (int i = 0; i < nodes.size(); i++) {
      if (allVisited.get(i) == Boolean.FALSE) {
        // allVisited prevents program from going through one partition many times
        newSize = 0;
        temp.clear();
        temp = computeReachableNodes(i);
        for (int j = 0; j < temp.size(); j++) {
          if (temp.get(j) == Boolean.TRUE) {
            newSize++;
            allVisited.set(j, Boolean.TRUE);
          }
          if (newSize > currentSize) {
            nodeMarks = temp;
            currentSize = newSize;
          }
        }
      }
    }
    
    System.out.print("Size of Largest Component: "+currentSize);
    return currentSize;
  }
  
  public void setArcCostToDistance() {
    for (int i = 0; i < nodes.size(); i++) {
      for (int k = 0; k < adjacencyLists.get(i).size(); k++) {
        adjacencyLists.get(i).get(k).cost = adjacencyLists.get(i).get(k).distance;       
      }
    }
  }
  
  public void setArcCostToTravelTime(int maxVehicleSpeed) {
    for (int i = 0; i < adjacencyLists.size(); i++) {
      for (int k = 0; k < adjacencyLists.get(i).size(); k++) {
        adjacencyLists.get(i).get(k).cost = (float) (0.001*(float)adjacencyLists.get(i).get(k).distance/
          (float)(Math.min(adjacencyLists.get(i).get(k).maxSpeed, maxVehicleSpeed)));       
      } 
    }
  }

  public int findTheFurthestNode() {
    float longestDistance = 0;
    int id = 0;
    for (int i = 0; i < nodes.size(); i++) {
      if (longestDistance < nodes.get(i).tempDistance && nodes.get(i).tempDistance != Float.MAX_VALUE) {
        longestDistance = nodes.get(i).tempDistance;
        id = i;
      }
    }
    return id;
  }
  
  public void computeShortestPath(int startNodeId) {
    ArrayList<Boolean> settled = new ArrayList<Boolean>();
    Comparator<Node> comparator = new NodeComparator();
    PriorityQueue<Node> pq = new PriorityQueue<Node>(10, comparator);
   // ArrayList<Float> cost = new ArrayList<Float>();
    for (Node n: nodes) {
      settled.add(false);
    }
    settled.set(startNodeId, true);
    nodes.get(startNodeId).tempDistance = 0;
    for (Arc arc : adjacencyLists.get(startNodeId)) {
      nodes.get(arc.headNodeId).tempDistance = arc.cost;
      nodes.get(arc.headNodeId).tracebackArc = arc;
      pq.add(nodes.get(arc.headNodeId));
    }
    while(!pq.isEmpty()) {
      if (settled.get(pq.peek().id) == false) {
        settled.set(pq.peek().id, true);
        nodes.get(pq.peek().id).tracebackArc = swapArc(nodes.get(pq.peek().id).tracebackArc);
        //nodes.get(pq.peek().id).tracebackArc = new Arc (previousId, pq.peek().id, currentNodeId, currentNodeId, currentNodeId)
        for (Arc arc : adjacencyLists.get(pq.peek().id)) {
          if (nodes.get(arc.headNodeId).tempDistance > pq.peek().tempDistance + (float)arc.cost) {
            //nodes.set(pq.peek().tempDistance + (float)arc.distance, nodes.get(arc.headNodeId).tempDistance);
            nodes.get(arc.headNodeId).tracebackArc = arc;
            nodes.get(arc.headNodeId).tempDistance = pq.peek().tempDistance + (float)arc.cost;
            pq.add(nodes.get(arc.headNodeId));
          }     
        }
      } else {
        pq.remove();
      }
    }
  }
  public Arc swapArc(Arc toBeSwapped) {
    int temp = toBeSwapped.headNodeId;
    toBeSwapped.headNodeId = toBeSwapped.tailNodeId;
    toBeSwapped.tailNodeId = temp;
    return toBeSwapped;
  }
  
  public float rollBack(Node stub) {
    //create a file to write the coordinates into
    String path = "non_code/data.txt";
    int intermission = 0;
    File coord = new File(path);
    try {
      FileWriter fw = new FileWriter(coord);
      float cost = 0;
      fw.write("[map]\n");
      if (stub.tracebackArc.cost == stub.tracebackArc.distance) {
        //here cost = distance thus we're looking for the time
        while (stub.tracebackArc != null) {
          if (intermission == 10) {
            fw.write(nodes.get(stub.id).latitude + "," + nodes.get(stub.id).longitude + " ");
            intermission = 0;
          }
          cost += (float)((0.001)*(float)stub.tracebackArc.distance/(float)stub.tracebackArc.maxSpeed);
          stub = nodes.get(stub.tracebackArc.headNodeId);
          intermission++;
        }
        fw.write("[/map]\n");
        fw.close();
        return cost;
      } else {
        while (stub.tracebackArc != null) {
          if (intermission == 10) {
            fw.write(nodes.get(stub.id).latitude + "," + nodes.get(stub.id).longitude + " ");
            intermission = 0;
          }
          cost += stub.tracebackArc.distance;
          stub = nodes.get(stub.tracebackArc.headNodeId);
          intermission++;
        }
        fw.write("[/map]\n");
        fw.close();
        return cost;
      }
    } catch (IOException ex) {
       ex.printStackTrace();
       return 0;
    }

  }
  // PRIVATE MEMBERS
  
  // The adjacency list of each node.
  ArrayList<ArrayList<Arc>> adjacencyLists;
  
  // Information about each node.
  ArrayList<Node> nodes;
  
  ArrayList<Boolean> nodeMarks; 
  
  // The number of arcs in the graph. Note that unlike the number of nodes this
  // cannot be computed in constant time from adjacencyLists or nodes, hence
  // better keep track of it seperately.
  private int numArcs;  
}