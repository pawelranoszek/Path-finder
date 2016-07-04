/**
 * Class containing information about 
 * starting node: id and its position.
 * @author Pawel
 *
 */
public class Node {
  int id;
  float latitude;
  float longitude;
  Arc tracebackArc;
  float tempDistance;
  
  public Node(int nid, float nlatitude, float nlongitude) {
    this.id = nid;
    this.latitude = nlatitude;
    this.longitude = nlongitude;  
    this.tempDistance = Float.MAX_VALUE;
  }
}
