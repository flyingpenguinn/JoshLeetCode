import base.ArrayUtils;

import java.util.*;

public class MinRunesToAddToCastSpell {
    // Kosaraju's algorithm to get strongly connected component
    private List<Integer>[] tg;
    private List<Integer>[] g;
    // vertex to condensed graph map
    private Map<Integer, Integer> vgm = new HashMap<>();
    private Map<Integer, Set<Integer>> cg = new HashMap<>();
    private int[] st;
    private Deque<Integer> stack = new ArrayDeque<>();

    public int minRunesToAdd(int n, int[] crs, int[] from, int[] to) {
        g = new ArrayList[n];
        tg = new ArrayList[n];
        st = new int[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
            tg[i] = new ArrayList<>();
        }
        int fn = from.length;
        for (int i = 0; i < fn; ++i) {
            int v1 = from[i];
            int v2 = to[i];
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
                dfs2(tg, top, vgm, comp);
                ++comp;
            }
        }
        for (int i = 0; i < fn; ++i) {
            int v1 = from[i];
            int v2 = to[i];
            int v1c = vgm.get(v1);
            int v2c = vgm.get(v2);
            if (v1c != v2c) {
                cg.computeIfAbsent(v1c, k -> new HashSet<>()).add(v2c);
            }
        }
        st = new int[comp];
        Set<Integer> crystals = new HashSet<>();
        for (int cr : crs) {
            int crc = vgm.get(cr);
            crystals.add(crc);
        }
        int res = 0;
        for (int i = 0; i < comp; ++i) {
            // each SCC that does not have crystal or cannot be reached via a crystal-containing SCC needs one connection
            if (st[i] == 0) {
                if (!crystals.contains(i)) {
                    ++res;
                }
                dfs3(cg, i);
            }
        }
        return res;
    }

    private void dfs3(Map<Integer, Set<Integer>> cg, int i) {
        st[i] = 1;
        for (int ne : cg.getOrDefault(i, new HashSet<>())) {
            if (st[ne] == 0) {
                dfs3(cg, ne);
            }
        }
        st[i] = 2;
    }

    private void dfs2(List<Integer>[] tg, int i, Map<Integer, Integer> vgm, int cindex) {
        st[i] = 1;
        vgm.put(i, cindex);
        for (int ne : tg[i]) {
            if (st[ne] == 0) {
                dfs2(tg, ne, vgm, cindex);
            }
        }
        st[i] = 2;
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
        System.out.println(new MinRunesToAddToCastSpell().minRunesToAdd(3, ArrayUtils.read1d("2"), ArrayUtils.read1d("1,1"), ArrayUtils.read1d("0,2")));
        //System.out.println(new MinRunesToAddToCastSpell().minRunesToAdd(7, ArrayUtils.read1d("3,5"), ArrayUtils.read1d("0,1,2,3,5"), ArrayUtils.read1d("1,2,0,4,6")));
        //  System.out.println(new MinRunesToAddToCastSpell().minRunesToAdd(6, ArrayUtils.read1d("0"), ArrayUtils.read1d("0,1,2,3"), ArrayUtils.read1d("1,2,3,0")));
    }


}
