/**
 * Plans route depending on the input settings.
 * in arg[1] options 1-3 are about Nurnberg
 * 4-6 are about the point furthest away.
 * @author Pawel
 *
 */
public class RoutePlannerMain {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please give the file destination as a parameter.");
      System.exit(1);
    }
    Graph gph = new Graph();
    gph.readFromFile(args[0]); 
    switch (args[1]) {
    case "1":
      gph.setArcCostToDistance();
      long startTime = System.currentTimeMillis();
      gph.computeShortestPath(5508637);
      long duration = System.currentTimeMillis() - startTime;
      System.out.println("The time elapsed is: " + duration);
      System.out.println("The distance is " + gph.nodes.get(4435496).tempDistance +"m \n");
      System.out.println("The time is " + gph.rollBack(gph.nodes.get(4435496)) +"h \n");
      break;
    case "2":
      gph.setArcCostToTravelTime(130);
      startTime = System.currentTimeMillis();
      gph.computeShortestPath(5508637);
      duration = System.currentTimeMillis() - startTime;
      System.out.println("The time elapsed is: " + duration);
      System.out.println("The time is " + gph.nodes.get(4435496).tempDistance +"h \n");
      System.out.println("The distance is " + gph.rollBack(gph.nodes.get(4435496)) +"m \n");
      break;
    case "3":
      gph.setArcCostToTravelTime(100);
      startTime = System.currentTimeMillis();
      gph.computeShortestPath(5508637);
      duration = System.currentTimeMillis() - startTime;
      System.out.println("The time elapsed is: " + duration);
      System.out.println("The time is " + gph.nodes.get(4435496).tempDistance +"h \n");
      System.out.println("The distance is " + gph.rollBack(gph.nodes.get(4435496)) +"m \n");
      break;
    case "4":
      gph.setArcCostToDistance();
      startTime = System.currentTimeMillis();
      gph.computeShortestPath(5508637);
      duration = System.currentTimeMillis() - startTime;
      int id = gph.findTheFurthestNode();
      System.out.println("The node furthest away is of id: " + id + "\n");
      System.out.println("The time elapsed is: " + duration);
      System.out.println("The distance is " + gph.nodes.get(id).tempDistance +"m \n");
      System.out.println("The time is " + gph.rollBack(gph.nodes.get(id)) +"h \n");
      break;
    case "5":
      gph.setArcCostToTravelTime(130);
      startTime = System.currentTimeMillis();
      gph.computeShortestPath(5508637);
      duration = System.currentTimeMillis() - startTime;
      id = gph.findTheFurthestNode();
      System.out.println("The node furthest away is of id: " + id + "\n");
      System.out.println("The time elapsed is: " + duration);
      System.out.println("The time is " + gph.nodes.get(id).tempDistance +"h \n");
      System.out.println("The distance is " + gph.rollBack(gph.nodes.get(id)) +"m \n");
      break;
    case "6":
      gph.setArcCostToTravelTime(100);
      startTime = System.currentTimeMillis();
      gph.computeShortestPath(5508637);
      duration = System.currentTimeMillis() - startTime;
      id = gph.findTheFurthestNode();
      System.out.println("The node furthest away is of id: " + id + "\n");
      System.out.println("The time elapsed is: " + duration);
      System.out.println("The time is " + gph.nodes.get(id).tempDistance +"h \n");
      System.out.println("The distance is " + gph.rollBack(gph.nodes.get(id)) +"m \n");
      break;
    default:
      System.out.println("Please give the program version as a parameter (from 1 to 6).");
    }


  }
}
