import java.util.List;
import java.lang.StringBuilder;
import java.util.Queue;
import java.util.LinkedList;


public class KDTree<V> {

    /* A node to hold the point and values.
      Also has a reference to its childrens/ */
    private class Node<V>{
        public V value;
        public int[] point;
        public Node first;
        public Node second;

        public Node(int[] point, V value){
            this.value = value;
            this.point = point;
            this.first = null;
            this.second = null;
        }
        @Override
        public String toString(){
            return String.format("{(%d, %d): %s}", this.point[0], this.point[1], this.value);
        }
    }

    private Node root;
    private int dimensions;
    // Constructor
    public KDTree(int dimensions){
        this.root = null;
        this.dimensions = dimensions;
    }
    /* Insert point and its value into KD tree */
    public void insert(int[] point, V value){
      if(root == null){
        this.root = new Node(point, value);
      }
      else{
         this.root = insertHelper(this.root, 0, point, value);
      }
      return;
    }
    private Node insertHelper(Node node, int level, int[] point, V value ){
        if(node == null){
            Node insertNode = new Node(point,value);
            return insertNode;
        }
        int dim = level % this.dimensions;
        int pivot = node.point[dim];
        if(point[dim] < pivot){
            node.first = insertHelper(node.first, level+1, point, value);
        }
        else{
            node.second = insertHelper(node.second, level+1, point, value);
        }
        return node;
    }

    @Override
    public String toString(){
        Queue<Node> processing = new LinkedList<Node>();
        processing.add(this.root);
        StringBuilder  sb = new StringBuilder();

        while(!processing.isEmpty()){
            Node current = processing.remove();
            sb.append(current.toString());
            if(current.first != null)
              processing.add(current.first);
            if(current.second != null)
              processing.add(current.second);
        }
        return sb.toString();
    }

    public static void main(String[] args){
       KDTree<String> tree = new KDTree<String>(2);

       int[] p1 = {100,100};
       tree.insert(p1, "India");

       int[] p2 = {50,100};
       tree.insert(p2, "Pak");

       int[] p3 = {120,100};
       tree.insert(p3, "China");

       int[] p4 = {120,30};
       tree.insert(p4, "West");

       int[] p5 = {40,120};
       tree.insert(p5, "East");

       System.out.println(tree);
    }

}
