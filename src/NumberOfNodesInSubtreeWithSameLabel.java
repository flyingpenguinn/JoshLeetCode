import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfNodesInSubtreeWithSameLabel {
    class Solution {
        public int[] countSubTrees(int n, int[][] edges, String labels) {
            // verify input...
            Map<Integer, Set<Integer>> tree = buildTree(n, edges);
            int[] r = new int[n];
            dfs(0, -1, tree, labels, r);
            return r;
        }

        // count of labels in subtree of i inclusive
        private int[] dfs(int i, int parent, Map<Integer, Set<Integer>> tree, String labels, int[] r) {
            int[] cur = new int[26];
            if (i >= labels.length()) {
                return cur;
            }
            Set<Integer> nexts = tree.getOrDefault(i, new HashSet<>());
            for (int next : nexts) {
                if (next == parent) {
                    continue;
                }
                int[] nr = dfs(next, i, tree, labels, r);
                for (int j = 0; j < 26; j++) {
                    cur[j] += nr[j];
                }
            }
            int iIndex = labels.charAt(i) - 'a';
            cur[iIndex]++;
            r[i] = cur[iIndex];
            return cur;
        }

        private Map<Integer, Set<Integer>> buildTree(int n, int[][] edges) {
            Map<Integer, Set<Integer>> m = new HashMap<>();
            for (int i = 0; i < n; i++) {
                m.put(i, new HashSet<>());
            }
            for (int[] e : edges) {
                m.get(e[0]).add(e[1]);
                m.get(e[1]).add(e[0]);
            }
            return m;
        }
    }
}
