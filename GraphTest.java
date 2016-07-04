import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * Computes tests.
 * @author Pawel
 *
 */
public class GraphTest {

  /**
  @Test
  public void testGraphmethods() {
        
    Graph testGraph = new Graph();    
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();
    testGraph.addNode();



    testGraph.addArc(0, new Arc(1, 12, 30));
    testGraph.addArc(0, new Arc(2, 11, 60));
    testGraph.addArc(0, new Arc(3, 9, 90));
    testGraph.addArc(1, new Arc(2, 7, 120));
    testGraph.addArc(1, new Arc(3, 4, 10));
    testGraph.addArc(2, new Arc(3, 2, 50));
    testGraph.addArc(3, new Arc(1, 1, 70));
    //5 nodes, 4 of which are reachable within one partition

    
    Boolean[] markings = new Boolean[testGraph.getNumNodes()];
    Arrays.fill(markings, false);   
    ArrayList<Boolean> initialbools = new ArrayList<Boolean>(
      Arrays.asList(markings));
    //Below reachable nodes from id=3 are computed
    //from 3 only 1 is reachable, from there: 2 and 3
    ArrayList<Boolean> bools = testGraph.computeReachableNodes(3);
    Assert.assertEquals (bools.get(0), Boolean.FALSE);
    Assert.assertEquals (bools.get(1), Boolean.TRUE);
    Assert.assertEquals (bools.get(2), Boolean.TRUE);
    Assert.assertEquals (bools.get(3), Boolean.TRUE);
    Assert.assertEquals (bools.get(4), Boolean.FALSE);
    int result = testGraph.computeLargestConnectedComponent();
    //as mentioned, only 4 nodes are in the single biggest partition
    Assert.assertEquals(4, result);
    
    
    Assert.assertEquals("{5; 7; (0,1); (0,2); (0,3); (1,2); (1,3); (2,3); (3,1)}",
      testGraph.toString());
    
  }
  
**/
}
