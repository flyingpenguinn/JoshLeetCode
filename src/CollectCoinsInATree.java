import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CollectCoinsInATree {

    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        Set<Integer>[] tree = new HashSet[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new HashSet();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        Deque<Integer> leaf = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int u = i;
            while (tree[u].size() == 1 && coins[u] == 0) {
                int v = tree[u].iterator().next();
                tree[u].remove(v);
                tree[v].remove(u);
                u = v;
            }
            if (tree[u].size() == 1) {
                leaf.offerLast(u);
            }
        }

        for (int times = 0; times < 2; ++times) {
            int size = leaf.size();
            for (int i = 0; i < size; ++i) {
                int u = leaf.pollFirst();
                if (tree[u].isEmpty()) {
                    // u could be empty already
                    continue;
                }
                int v = tree[u].iterator().next();
                tree[u].remove(v);
                tree[v].remove(u);
                if (tree[v].size() == 1) {
                    leaf.offerLast(v);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += tree[i].size();
        }
        return res;
    }
}
