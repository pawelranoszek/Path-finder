/**
 * Represents Arc class with its attributes:
 * destination node, cost of the arc (path)
 * and maximum speed on the arc
 * @author Pawel
 *
 */
public class Arc {
  //PUBLIC MEMBERS
  int tailNodeId;
  int headNodeId;
  float cost;
  int distance;
  int maxSpeed;
  
  public Arc(int atailNodeId, int aheadNodeId, int adistance, int amaxSpeed) {
    this.tailNodeId = atailNodeId;
    this.headNodeId = aheadNodeId;
    this.cost = adistance; //as default distance is taken as the cost
    this.distance = adistance;
    this.maxSpeed = amaxSpeed;    
  }
}
