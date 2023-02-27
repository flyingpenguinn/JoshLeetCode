public class LeftAndRightSumDiffs {
    public int[] leftRigthDifference(int[] a) {
        int allsum = 0;
        for (int ai : a) {
            allsum += ai;
        }
        int leftsum = 0;
        int n = a.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int rightsum = allsum - a[i] - leftsum;
            res[i] = Math.abs(leftsum - rightsum);
            leftsum += a[i];
        }
        return res;
    }
}
