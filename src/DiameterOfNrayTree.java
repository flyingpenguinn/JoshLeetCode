import java.util.ArrayList;
import java.util.List;

public class DiameterOfNrayTree {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    private int max = 0;

    public int diameter(Node root) {
        dfs(root);
        return max;
    }

    private int dfs(Node n) {
        if (n == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        int maxPath = 0;
        int maxsum = 0;
        for (Node ch : n.children) {
            int path = dfs(ch) + 1;
            int sum = path + maxPath;
            maxsum = Math.max(maxsum, sum);
            maxPath = Math.max(maxPath, path);
        }
        max = Math.max(max, maxsum);
        return maxPath;
    }
}
