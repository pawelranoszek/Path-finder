/**
 * A Test for route planner.
 * Graph taken from AlgDat Lecture no 13.
 */

import org.junit.Assert;
import org.junit.Test;

public class RoutePlannerTest {
  @Test
  public void testRoutePlanner() {
    Graph testGraph = new Graph();    
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();
    //order of variables in Arc: where from -- where to -- distance -- max speed
    testGraph.addArc(0, new Arc(0, 1, 1, 5));
    testGraph.addArc(0, new Arc(0, 3, 5, 5));
    testGraph.addArc(0, new Arc(0, 4, 10, 5));
    testGraph.addArc(1, new Arc(1, 2, 1, 5));
    testGraph.addArc(1, new Arc(1, 3, 3, 5));
    testGraph.addArc(2, new Arc(2, 3, 1, 5));
    testGraph.addArc(2, new Arc(2, 5, 4, 5));
    testGraph.addArc(3, new Arc(3, 4, 3, 5));
    testGraph.addArc(4, new Arc(4, 5, 7, 5));
    testGraph.setArcCostToDistance();
    testGraph.computeShortestPath(0);
    Assert.assertEquals(testGraph.nodes.get(0).tempDistance, (float)0, 0.5);
    Assert.assertEquals(testGraph.nodes.get(1).tempDistance, (float)1, 0.01);
    Assert.assertEquals(testGraph.nodes.get(2).tempDistance, (float)2, 0.01);
    Assert.assertEquals(testGraph.nodes.get(3).tempDistance, (float)3, 0.01);
    Assert.assertEquals(testGraph.nodes.get(4).tempDistance, (float)6, 0.01);
    Assert.assertEquals(testGraph.nodes.get(5).tempDistance, (float)6, 0.01);
    Assert.assertEquals(testGraph.nodes.get(5).tracebackArc.headNodeId, 2);
  }
}
