import java.util.*;

public class FindSubtreeSizeAfterChanges {
    private Map<Integer, Set<Integer>> t = new HashMap<>();
    private Map<Integer, Set<Integer>> nt = new HashMap<>();
    private int[] parent;
    private String s;
    private int[] res;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int[] m = new int[26];
        int n = parent.length;
        Arrays.fill(m, -1);
        this.s = s;
        this.parent = parent;
        for (int i = 0; i < n; ++i) {
            int cp = parent[i];
            if (cp != -1) {
                t.computeIfAbsent(cp, k -> new HashSet<>()).add(i);
                nt.computeIfAbsent(cp, k -> new HashSet<>()).add(i);
            }
        }

        dfs1(0, m);
        t = nt;
        res = new int[n];
        dfs2(0);
        return res;
    }

    private void dfs1(int i, int[] m) {
        int cind = s.charAt(i) - 'a';
        if (m[cind] != -1) {
            int oldp = parent[i];
            int newp = m[cind];
            if (oldp != newp) {
                nt.get(oldp).remove(i);
                nt.computeIfAbsent(newp, k -> new HashSet<>()).add(i);
                //  System.out.println("remove "+i+" to "+oldp+" direct to "+newp);
            }
        }
        int oldcind = m[cind];
        m[cind] = i;
        for (int ne : t.getOrDefault(i, new HashSet<>())) {
            dfs1(ne, m);
        }
        m[cind] = oldcind;
    }

    private int dfs2(int i) {
        int cres = 1;
        for (int ne : t.getOrDefault(i, new HashSet<>())) {
            int ctree = dfs2(ne);
            cres += ctree;
        }
        res[i] = cres;
        return cres;
    }
}
