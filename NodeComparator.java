import java.util.Comparator;
/**
 * Compares the nodes by their teporary Distance.
 * @author Pawel
 *
 */
public class NodeComparator implements Comparator<Node> {
 
  @Override
  public int compare(Node x, Node y)
  {

      if (x.tempDistance < y.tempDistance)
      {
          return -1;
      }
      if (x.tempDistance > y.tempDistance)
      {
          return 1;
      }
      return 0;
  }
}