import java.util.*;

public class CountVisitedNodesInDirectedGraph {
    // 1. find scc
    // 2. condense the sccs to nodes
    // 3. find the results for each scc

    // Kosaraju's algorithm to get strongly connected component
    private List<Integer>[] tg;
    private List<Integer>[] g;
    // vertex to condensed graph map
    private Map<Integer, Integer> vgm = new HashMap<>();
    // nodes in each g
    private Map<Integer, Integer> gcount = new HashMap<>();
    private List<Integer>[]  cg;
    private int[] compres;
    private int[] st;
    private Deque<Integer> stack = new ArrayDeque<>();


    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        g = new ArrayList[n];
        tg = new ArrayList[n];
        st = new int[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
            tg[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            int v1 = i;
            int v2 = edges.get(i);
            g[v1].add(v2);
            // transpose the graph
            tg[v2].add(v1);
        }
        for (int i = 0; i < n; ++i) {
            if (st[i] == 0) {
                dfs1(g, i);
            }
        }
        int comp = 0;
        Arrays.fill(st, 0);
        while (!stack.isEmpty()) {
            int top = stack.pollLast();
            if (st[top] == 0) {
                int nodes = dfs2(tg, top, vgm, comp);
                gcount.put(comp, nodes);
                ++comp;
            }
        }
        cg = new ArrayList[comp];
        for(int i=0; i<comp; ++i){
            cg[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            int v1 = i;
            int v2 = edges.get(i);
            int v1c = vgm.get(v1);
            int v2c = vgm.get(v2);
            if (v1c != v2c) {
                cg[v1c].add(v2c);
            }
        }
        st = new int[comp];
        int[] res = new int[n];
        compres = new int[comp];
        for (int i = 0; i < n; ++i) {
            int c = vgm.get(i);
            // each SCC that does not have crystal or cannot be reached via a crystal-containing SCC needs one connection
            if (st[c] == 0) {
                int nodes = dfs3(cg, c);
                res[i] = nodes;
                compres[c] = nodes;
            } else {
                res[i] = compres[c];
            }
        }
        return res;
    }

    private int dfs3(List<Integer>[] cg, int ci) {
        st[ci] = 1;
        int res = gcount.get(ci);
        for (int ne : cg[ci]) {
            if (st[ne] == 0) {
                int cur = dfs3(cg, ne);
                res += cur;
            } else {
                res += compres[ne];
            }
        }
        st[ci] = 2;
        compres[ci] = res;
        return res;
    }

    private int dfs2(List<Integer>[] tg, int i, Map<Integer, Integer> vgm, int cindex) {
        st[i] = 1;
        int res = 1;
        vgm.put(i, cindex);
        for (int ne : tg[i]) {
            if (st[ne] == 0) {
                int cur = dfs2(tg, ne, vgm, cindex);
                res += cur;
            }
        }
        st[i] = 2;
        return res;
    }

    private void dfs1(List<Integer>[] g, int i) {
        st[i] = 1;
        for (int ne : g[i]) {
            if (st[ne] == 0) {
                dfs1(g, ne);
            }
        }
        st[i] = 2;
        stack.offerLast(i);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountVisitedNodesInDirectedGraph().countVisitedNodes(List.of(3,6,1,0,5,7,4,3))));
    }
}
