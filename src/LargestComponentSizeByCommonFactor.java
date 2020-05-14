import base.ArrayUtils;
import base.Lists;

import java.util.*;

public class LargestComponentSizeByCommonFactor {

    // factor numbers to primes then union find on representatives of the primes
    class DisjointSet<T> {
        // from string to its representative
        Map<T, T> parent = new HashMap<>();
        // each representative's size
        Map<T, Integer> size = new HashMap<>();
        int maxSetSize = 0;
        int numOfSets = 0;

        public T makeSet(T s) {
            parent.put(s, s);
            size.put(s, 1);
            numOfSets++;
            maxSetSize = Math.max(maxSetSize, 1);
            return s;
        }

        // path compression
        public T find(T s) {
            T p = parent.get(s);
            if (p == null) {
                return makeSet(s);
            } else if (p.equals(s)) {
                return p;
            } else {
                T pt = find(p);
                parent.put(s, pt);
                return pt;
            }
        }


        // union by size
        public T union(T x, T y) {
            T px = find(x);
            T py = find(y);
            if (px.equals(py)) {
                // no merge! all equal
                return px;
            }
            int sizex = size.get(px);
            int sizey = size.get(py);
            T toReturn = null;
            numOfSets--;
            if (sizex < sizey) {
                parent.put(px, py);
                toReturn = py;
            } else if (sizex > sizey) {
                parent.put(py, px);
                toReturn = px;
            } else {
                parent.put(px, py);
                toReturn = py;
            }
            size.put(toReturn, sizex + sizey);
            maxSetSize = Math.max(maxSetSize, sizex + sizey);
            return toReturn;

        }

        public int numOfSets() {
            return numOfSets;
        }
    }

    Map<Integer, Integer> rep = new HashMap<>();


    public int largestComponentSize(int[] a) {
        int max = 0;
        DisjointSet<Integer> ds = new DisjointSet<>();
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
            ds.makeSet(a[i]);
        }
        boolean[] prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        for (int i = 2; i <= max; i++) {
            if (i % 2 == 0) {
                prime[i] = i == 2;
                continue;
            }
            if (!prime[i]) {
                continue;
            }
            for (int j = 2 * i; j <= max; j += i) {
                prime[j] = false;
            }
        }

        for (int i = 0; i < a.length; i++) {
            int n = a[i];
            boolean found = false;
            if (prime[n]) {
                if (!rep.containsKey(n)) {
                    rep.put(n, n);
                } else {
                    // add myself to myself's rep list
                    handleJ(n, n, ds);
                }
            } else {
                for (int j = 2; j * j <= n; j++) {
                    if (n % j == 0) {
                        if (prime[j]) {
                            handleJ(n, j, ds);
                        }
                        if (prime[n / j] && j != n / j) {
                            handleJ(n, n / j, ds);
                        }
                    }
                }
            }
        }
        return ds.maxSetSize;
    }

    private void handleJ(int n, int j, DisjointSet ds) {

        Integer currep = rep.get(j);
        if (currep == null) {
            rep.put(j, n);
        } else {
            Integer repp = (Integer) ds.find(currep);
            Integer np = (Integer) ds.find(n);
            ds.union(repp, np);
        }
    }


    public static void main(String[] args) {
        System.out.println(new LargestComponentSizeByCommonFactor().largestComponentSize(ArrayUtils.read1d("[83,99,39,11,19,30,31]")));
    }
}

class LargestCommonComponentDfs {
    Set<Integer> visited = new HashSet<>();
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> rep = new HashMap<>();

    public int largestComponentSize(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        boolean[] prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        for (int i = 2; i <= max; i++) {
            if (i % 2 == 0) {
                prime[i] = i == 2;
                continue;
            }
            if (!prime[i]) {
                continue;
            }
            for (int j = 2 * i; j <= max; j += i) {
                prime[j] = false;
            }
        }

        for (int i = 0; i < a.length; i++) {
            int n = a[i];
            boolean found = false;
            for (int j = 2; j * j <= n; j++) {
                if (n % j == 0) {
                    found = true;
                    if (prime[j]) {
                        handleJ(n, j);
                    }
                    if (prime[n / j] && j != n / j) {
                        handleJ(n, n / j);
                    }
                }
            }
            if (!found) {
                if (!rep.containsKey(n)) {
                    rep.put(n, n);
                } else {
                    // add myself to myself's rep list
                    handleJ(n, n);
                }
            }
            if (!graph.containsKey(n)) {
                graph.put(n, new HashSet<>());
            }
        }
        int maxr = 0;
        for (Integer key : graph.keySet()) {
            if (!visited.contains(key)) {
                int size = dfs(key);
                maxr = Math.max(size, maxr);
            }
        }
        return maxr;
    }

    private boolean handleJ(int n, int j) {

        Integer currep = rep.get(j);
        if (currep == null) {
            rep.put(j, n);
            return false;
        } else {
            Set<Integer> currepc = graph.getOrDefault(currep, new HashSet<>());
            currepc.add(n);
            graph.put(currep, currepc);
            Set<Integer> nc = graph.getOrDefault(n, new HashSet<>());
            nc.add(currep);
            graph.put(n, nc);
            return true;
        }
    }

    private int dfs(Integer key) {
        visited.add(key);
        int c = 1;
        for (Integer con : graph.getOrDefault(key, new HashSet<>())) {
            if (!visited.contains(con)) {
                c += dfs(con);
            }
        }
        return c;
    }
}
