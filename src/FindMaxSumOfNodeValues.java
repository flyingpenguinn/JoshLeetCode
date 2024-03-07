public class FindMaxSumOfNodeValues {
    // greedy....we can always change even numbers of node in a tree
    public long maximumValueSum(int[] a, int k, int[][] edges) {
        int n = a.length;

        long minsac = (long) 1e9;
        long res = 0;
        int flips = 0;
        for (int i = 0; i < n; ++i) {
            long way1 = a[i];
            long way2 = a[i] ^ k;
            if (way1 > way2) {
                res += way1;
                minsac = Math.min(minsac, way1 - way2);
            } else {
                flips += 1;
                res += way2;
                minsac = Math.min(minsac, way2 - way1);
            }
        }
        if (flips % 2 == 0) {
            return res;
        } else {
            return res - minsac;
        }
    }
}
