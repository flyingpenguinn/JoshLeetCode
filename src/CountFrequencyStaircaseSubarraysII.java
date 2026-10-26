public class CountFrequencyStaircaseSubarraysII {
    // l has at most sqrt(2n) different values
    private int limit = (int) 1e5;

    public int countStaircase(int[] a) {
        int n = a.length;
        int[] seen = new int[limit + 1];
        int[] cnt = new int[limit + 1];
        int[] rank = new int[limit + 1];
        int round = 0;
        int res = 0;
        int maxrank = (int) (Math.sqrt(2*n) + 10);
        for (int i = 0; i < n; ++i) {
            ++round;
            int crank = 0;
         
            for (int j = i; j < n; ++j) {
                int v = a[j];
                if (seen[v] != round) {
                    seen[v] = round;
                    rank[v] = ++crank;
                    cnt[v] = 0;
                }
                ++cnt[v];
                if (cnt[v] > rank[v]) {
                    break;
                }
               
                int clen = j - i + 1;
                if (clen * 2 == crank * (crank + 1)) {
                    ++res;
                } 
                if (crank > maxrank ) {
                    break;
                }
            }
        }
        return res;
    }
}
