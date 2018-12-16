import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class RandomList {
    public class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; this.next = null; this.random = null; }
    }
    public RandomListNode copyRandomList(RandomListNode head) {
      Map<RandomListNode, RandomListNode> nodeMappings = new HashMap<RandomListNode, RandomListNode>();
      RandomListNode newHead = null;
      RandomListNode previous = null;
      RandomListNode node = head;
      RandomListNode current = null;
      List<RandomListNode> pending = new ArrayList<RandomListNode>();

      while(node != null){
          current = new RandomListNode(node.label);
          nodeMappings.put(node, current);
          if(previous != null){
              previous.next = current;
          }else{
              newHead = current;
          }

          if(node.random != null)
            if(nodeMappings.get(node.random) != null)
                current.random = nodeMappings.get(node.random);
            else
              pending.add(node);

          previous = current;
          node = node.next;
      }

      node = head;
      current = newHead;

      for(RandomListNode node2 : pending){
          current = nodeMappings.get(node2);
          current.random = nodeMappings.get(node2.random);
      }
      return newHead;
    }

    public static void main(String[] args){
      return;
    }
}
