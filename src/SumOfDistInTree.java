import base.ArrayUtils;

import java.util.*;

public class SumOfDistInTree {

    private class GraphNode {
        public int val;
        Set<Integer> children = new HashSet<>();
        int nodeCount;

        public GraphNode(int x) {
            val = x;
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {

        int[] r = new int[n];
        buildGraph(edges);
        GraphNode first = map.get(0);
        if (first == null) {
            return r;
        }
        int dist = dfs1(first, null);
        r[0] = dist;
        dfs2(first, null, r, n);
        return r;
    }

    // don't really need to be root, any graph node can be root
    private void dfs2(GraphNode root, GraphNode parent, int[] r, int n) {
        if (parent != null) {
            int base = r[parent.val];
            int above = n - parent.nodeCount + 1;
            int sibling = parent.nodeCount - root.nodeCount - 1;
            int cur = root.nodeCount;
            r[root.val] = base + above + sibling - cur;
        }
        for (int cid : root.children) {
            GraphNode node = map.get(cid);
            if (node != parent) {
                dfs2(node, root, r, n);
            }
        }
    }

    private int dfs1(GraphNode root, GraphNode parent) {
        int dist = 0;
        int count = 1;
        for (int cid : root.children) {
            GraphNode node = map.get(cid);
            if (node != parent) {
                int cd = dfs1(node, root);
                dist += node.nodeCount + cd;
                count += node.nodeCount;
            }
        }
        root.nodeCount = count;
        return dist;
    }

    Map<Integer, GraphNode> map = new HashMap<>();

    private void buildGraph(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            int n1v = edges[i][0];
            int n2v = edges[i][1];
            GraphNode n1 = map.getOrDefault(n1v, new GraphNode(n1v));
            GraphNode n2 = map.getOrDefault(n2v, new GraphNode(n2v));
            n1.children.add(n2v);
            n2.children.add(n1v);
            map.put(n1v, n1);
            map.put(n2v, n2);
        }
    }

    public static void main(String[] args) {
        //      int[][] edges = ArrayUtils.read("[[0,1],[0,2],[2,3],[2,4],[2,5],[3,6],[3,7],[4,8],[5,9]]");
        int[][] edges = ArrayUtils.read("[[0,1],[0,2],[2,3],[2,4],[2,5],[3,6],[3,7],[4,8],[5,9]]");
        System.out.println(Arrays.toString(new SumOfDistInTree().sumOfDistancesInTree(10, edges)));
    }
}
