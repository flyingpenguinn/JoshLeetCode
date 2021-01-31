import base.TreeNode;

import java.util.*;

/*
LC#987
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.



Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.


Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
 */
public class VerticalOrderTraversal {

    // put each node into correct position. note must use treemap as y values can be non-continuous
    private Map<Integer,List<int[]>> m = new HashMap<>();
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);

        List<List<Integer>> res = new ArrayList<>();
        for(int i=min; i<=max; i++){
            List<int[]> list = m.get(i);
            if(list==null){
                continue;
            }
            Collections.sort(list, (x,y) -> x[0]!= y[0]? Integer.compare(y[0], x[0]): Integer.compare(x[1], y[1]));
            List<Integer> rl = new ArrayList<>();
            for(int[] li: list){
                rl.add(li[1]);
            }
            res.add(rl);
        }
        return res;
    }

    private void dfs(TreeNode n, int x, int y){
        if(n==null){
            return;
        }
        min = Math.min(min, x);
        max = Math.max(max, x);
        m.computeIfAbsent(x, k-> new ArrayList<>()).add(new int[]{y, n.val});
        dfs(n.left, x-1, y-1);
        dfs(n.right, x+1, y-1);
    }
}
