public class MaxManhattanDistAfterKChanges {
    // do the greedy operation at each turn not just at the end
    public int maxDistance(String s, int k) {
        int n = s.length();
        int ns = 0;
        int ss = 0;
        int ws = 0;
        int es = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int ck = k;
            char c = s.charAt(i);
            if (c == 'N') {
                ++ns;
            } else if (c == 'S') {
                ++ss;
            } else if (c == 'W') {
                ++ws;
            } else {
                ++es;
            }
            int maxns = Math.max(ns, ss);
            int minns = Math.min(ns, ss);
            int maxwe = Math.max(ws, es);
            int minwe = Math.min(ws, es);
            int nsreduce = Math.min(minns, ck);
            ck -= nsreduce;
            int wereduce = Math.min(minwe, ck);
            minns -= nsreduce;
            maxns += nsreduce;
            minwe -= wereduce;
            maxwe += wereduce;
            int dns = maxns - minns;
            int dwe = maxwe - minwe;
            int cur = dns + dwe;
            res = Math.max(res, cur);
        }
        return res;
    }
}
