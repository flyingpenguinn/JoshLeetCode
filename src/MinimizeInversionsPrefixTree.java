import java.util.*;

public class MinimizeInversionsPrefixTree {
    // TODO bitmask dp + merging on tree...
    static class Node {
        Node[] ch = new Node[10];
        Node parent;
        int edge;
        int word = -1;
        int degree = 0;
        int[] cnt;
        long[] cost;

        Node(Node p, int e) {
            parent = p;
            edge = e;
        }
    }

    public int minInversions(String[] words, int[] target) {
        int n = words.length;
        int[] pos = new int[n];

        for (int i = 0; i < n; ++i) {
            pos[target[i]] = i;
        }

        Node root = new Node(null, -1);
        Node[] terminal = new Node[n];
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);

        for (int i = 0; i < n; ++i) {
            Node cur = root;
            for (char c : words[i].toCharArray()) {
                int x = c - 'a';
                if (cur.ch[x] == null) {
                    cur.ch[x] = new Node(cur, x);
                    ++cur.degree;
                    nodes.add(cur.ch[x]);
                }
                cur = cur.ch[x];
            }
            cur.word = i;
            terminal[i] = cur;
        }

        for (Node u : nodes) {
            if (u.degree >= 2) {
                u.cnt = new int[10];
                u.cost = new long[100];
            }
        }

        long res = 0;

        for (int p = 0; p < n; ++p) {
            int w = target[p];
            Node cur = terminal[w];

            while (cur.parent != null) {
                Node par = cur.parent;
                int x = cur.edge;

                if (par.word != -1 && pos[par.word] > p) {
                    ++res;
                }

                if (par.cost != null) {
                    for (int y = 0; y < 10; ++y) {
                        if (y != x) {
                            par.cost[x * 10 + y] += par.cnt[y];
                        }
                    }
                    ++par.cnt[x];
                }

                cur = par;
            }
        }

        for (Node u : nodes) {
            if (u.cost == null) continue;

            int m = u.degree;
            int[] ids = new int[m];
            int z = 0;

            for (int i = 0; i < 10; ++i) {
                if (u.ch[i] != null) {
                    ids[z++] = i;
                }
            }

            int full = 1 << m;
            long[] dp = new long[full];
            Arrays.fill(dp, Long.MAX_VALUE / 4);
            dp[0] = 0;

            for (int st = 0; st < full; ++st) {
                for (int i = 0; i < m; ++i) {
                    if ((st & (1 << i)) != 0) continue;

                    long add = 0;
                    for (int j = 0; j < m; ++j) {
                        if ((st & (1 << j)) != 0) {
                            add += u.cost[ids[j] * 10 + ids[i]];
                        }
                    }

                    int ns = st | (1 << i);
                    dp[ns] = Math.min(dp[ns], dp[st] + add);
                }
            }

            res += dp[full - 1];
        }

        return (int) res;
    }
}
