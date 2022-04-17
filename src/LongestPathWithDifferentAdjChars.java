import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestPathWithDifferentAdjChars {
    private Map<Node, Integer> dp = new HashMap<>();
    private int res = 1;

    private class Node {
        char c;
        List<Node> ch = new ArrayList<>();

        public Node(char c) {
            this.c = c;
        }
    }

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; ++i) {
            nodes[i] = new Node(s.charAt(i));
        }
        for (int i = 1; i < n; ++i) {
            nodes[parent[i]].ch.add(nodes[i]);
        }
        Node root = nodes[0];
        solve(root);
        return res;
    }


    private int solve(Node n) {
        if (n == null) {
            return 0;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int cres = 1;
        int v1 = 0;
        int v2 = 0;
        for (Node ne : n.ch) {
            int solved = solve(ne);
            if (ne.c != n.c) {
                cres = Math.max(cres, 1 + solved);
                if (solved > v1) {
                    v2 = v1;
                    v1 = solved;
                } else if (solved > v2) {
                    v2 = solved;
                }
                int cur = v1 + v2 + 1;
                res = Math.max(res, cur);
            }
        }
        dp.put(n, cres);
        return cres;
    }
}
