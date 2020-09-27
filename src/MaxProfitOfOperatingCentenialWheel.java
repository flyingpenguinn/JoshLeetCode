public class MaxProfitOfOperatingCentenialWheel {

    // can't add all up and then 4 a time: 1-1-1-1 is different from 4 together
    public int minOperationsMaxProfit(int[] a, int bc, int rc) {
        int max = 0;
        int maxi = 0;
        int waiting = 0;
        int cur = 0;
        int round = 1;
        for (int i = 0; i < a.length || waiting > 0; i++) {
            if (i < a.length) {
                waiting += a[i];
            }
            int up = Math.min(4, waiting);
            waiting -= up;
            int curb = bc * up - rc;
            //   System.out.println(waiting + " "+up+" "+curb);
            cur += curb;
            if (cur > max) {
                max = cur;
                maxi = round;
            }
            round++;
        }
        return max <= 0 ? -1 : maxi;
    }
}
