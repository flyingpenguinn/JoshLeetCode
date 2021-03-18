import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindTheRootOfNrayTree {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public Node findRoot(List<Node> tree) {
        Set<Node> all = new HashSet<>();
        for(Node node: tree){
            all.add(node);
        }
        for(Node node:tree){
            for(Node o: node.children){
                all.remove(o);
            }
        }
        return all.iterator().next();
    }
}
