import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
LC#429
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:







We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]


Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
 */
public class NrayTreeLevelOrder {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> r = new ArrayList<>();
        if(root == null){
            return r;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        List<Integer> curlevel = new ArrayList<>();
        int curcount = 1;
        // invariant: curlevel is the nodes on current level, curcount is how many left
        while(!q.isEmpty()){
            Node top = q.poll();
            curlevel.add(top.val);
            for(Node ch: top.children){
                q.offer(ch);
            }
            curcount--;
            if(curcount==0){
                r.add(curlevel);
                curlevel = new ArrayList<>();
                curcount = q.size();
            }
        }
        return r;
    }
}
