public class MaxNumberOfAchievableTransfers {
    // note if we dp on request i and status, it's no better than direct brute force because in the end we have n*2^req time to check
    // here 2^requests is way better than possibilities of statuses
    public int maximumRequests(int n, int[][] reqs) {
        int rs = reqs.length;
        int max = 0;
        for (int st = 0; st < (1 << rs); st++) {
            int[] balance = new int[n];
            for (int i = 0; i < rs; i++) {
                if (((st >> i) & 1) == 1) {
                    balance[reqs[i][0]]--;
                    balance[reqs[i][1]]++;
                }
            }
            boolean bad = false;

            for (int i = 0; i < balance.length; i++) {
                if (balance[i] != 0) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                max = Math.max(max, Integer.bitCount(st));
            }
        }
        return max;
    }
}
