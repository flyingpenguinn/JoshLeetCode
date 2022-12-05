import java.util.*;

public class DivideGraphIntoMaxGroups {
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private int Max = (int) (1e9);

    public int magnificentSets(int n, int[][] edges) {
        for (int[] ed : edges) {
            int s = ed[0];
            int e = ed[1];
            g.computeIfAbsent(s, k -> new HashSet<>()).add(e);
            g.computeIfAbsent(e, k -> new HashSet<>()).add(s);
        }
        Set<Set<Integer>> comps = new HashSet<>();
        Map<Integer, Integer> color = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            if (!color.containsKey(i)) {
                color.put(i, 0);
                Set<Integer> cseen = new HashSet<>();
                boolean cur = dfs(i, color, cseen);
                if (!cur) {
                    return -1;
                }
                comps.add(cseen);
            }
        }
        int res = 0;
        for (Set<Integer> comp : comps) {
            int cur = 0;
            for (int k : comp) {
                int v = bfs(k);
                cur = Math.max(cur, v);
            }
            res += cur;
        }
        return res >= Max ? -1 : res;
    }


    private boolean dfs(int i, Map<Integer, Integer> cm, Set<Integer> seen) {
        seen.add(i);
        int cc = cm.get(i);
        int ecc = cc ^ 1;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {

            int ncc = cm.getOrDefault(ne, -1);
            if (ncc != -1 && ncc != ecc) {
                return false;
            }
            if (seen.contains(ne)) {
                continue;
            }
            cm.put(ne, ecc);
            boolean later = dfs(ne, cm, seen);
            if (!later) {
                return false;
            }

        }
        return true;
    }

    private int bfs(int i) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(i);
        m.put(i, 1);
        //System.out.println("start "+i);
        while (!dq.isEmpty()) {
            int top = dq.pollFirst();
            int cd = m.get(top);
            //  System.out.println("getting "+top+" "+cd);
            res = Math.max(res, cd);
            for (int ne : g.getOrDefault(top, new HashSet<>())) {
                if (m.containsKey(ne)) {
                    //      System.out.println("ignoring "+ne+" already got "+m.get(ne));
                    continue;
                }
                //  System.out.println("putting "+ne+" "+(cd+1));
                m.put(ne, (cd + 1));
                dq.offerLast(ne);
            }
        }
        return res;
    }
}
