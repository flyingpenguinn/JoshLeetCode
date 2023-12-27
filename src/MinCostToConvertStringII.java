import java.util.*;

public class MinCostToConvertStringII {
    // floyd + dp
    // in dp iterate on length of the strings !
    private Map<String, Integer> im = new HashMap<>();
    private long[][] g;
    private long[][] dist;
    private long MAX = (long) 1e15;
    private Long[] dp;
    private Set<Integer> lens = new HashSet<>();

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Set<String> strings = new HashSet<>();
        for (String o : original) {
            strings.add(o);
            lens.add(o.length());
        }
        for (String c : changed) {
            strings.add(c);
            lens.add(c.length());
        }
        lens.add(1);
        List<String> stringlist = new ArrayList<>(strings);
        int n = stringlist.size();
        for (int i = 0; i < n; ++i) {
            im.put(stringlist.get(i), i);
        }
        g = new long[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], MAX);
        }
        for (int i = 0; i < original.length; ++i) {
            String os = original[i];
            String cs = changed[i];
            int oi = im.get(os);
            int ci = im.get(cs);
            g[oi][ci] = Math.min(g[oi][ci], cost[i]);
        }
        dist = new long[n][n];
        dp = new Long[source.length()];
        floyd();
        long rt = solve(source, target, 0);
        if (rt >= MAX) {
            return -1;
        } else {
            return rt;
        }
    }

    private long solve(String source, String target, int i) {
        int n = source.length();
        if (i == n) {
            return 0;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        StringBuilder cs = new StringBuilder();
        long res = MAX;
        for(int len: lens){
            if(i+len>n){
                continue;
            }
            String s1 = source.substring(i, i+len);
            String t1 = target.substring(i, i+len);
            long cur = MAX;
            if(s1.equals(t1)){
                cur = solve(source, target, i+len);
            }else if(im.containsKey(s1) && im.containsKey(t1)){
                int si = im.get(s1);
                int ti = im.get(t1);
                cur = dist[si][ti] + solve(source, target, i+len);
            }
            res = Math.min(cur, res);
        }
        dp[i] = res;
        return res;
    }

    private void floyd() {
        int n = g.length;
        for (int i = 0; i < n; ++i) {
            dist[i][i] = 0;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dist[i][j] = g[i][j];
            }
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinCostToConvertStringII().minimumCost("abcdefgh", "acdeeghh", new String[]{"bcd","fgh","thh"}, new String[]{"cde","thh","ghh"}, new int[]{1,3,5}));
    }
}
