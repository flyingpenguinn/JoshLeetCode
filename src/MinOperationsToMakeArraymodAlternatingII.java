public class MinOperationsToMakeArraymodAlternatingII {
    // TODO
    public long minOperations(int[] nums, int k) {
        long[] evenCnt = new long[k];
        long[] oddCnt = new long[k];

        for (int i = 0; i < nums.length; i++) {
            int r = nums[i] % k;
            if (i % 2 == 0) evenCnt[r]++;
            else oddCnt[r]++;
        }

        long[] evenCost = buildCost(evenCnt, k);
        long[] oddCost = buildCost(oddCnt, k);

        // best and second-best target residues for odd positions
        int best1 = -1, best2 = -1;
        for (int r = 0; r < k; r++) {
            if (best1 == -1 || oddCost[r] < oddCost[best1]) {
                best2 = best1;
                best1 = r;
            } else if (best2 == -1 || oddCost[r] < oddCost[best2]) {
                best2 = r;
            }
        }

        long ans = Long.MAX_VALUE;

        for (int x = 0; x < k; x++) {
            int y = (x != best1) ? best1 : best2;
            ans = Math.min(ans, evenCost[x] + oddCost[y]);
        }

        return ans;
    }

    private long[] buildCost(long[] cnt, int k) {
        int len = 3 * k + 5;

        long[] prefCnt = new long[len + 1];
        long[] prefSum = new long[len + 1];

        for (int i = 0; i < len; i++) {
            long c = cnt[i % k];
            prefCnt[i + 1] = prefCnt[i] + c;
            prefSum[i + 1] = prefSum[i] + c * i;
        }

        long[] cost = new long[k];
        int half = k / 2;

        for (int t = 0; t < k; t++) {
            int center = t + k;

            int L;
            int R;

            if (k % 2 == 1) {
                // distance window length k:
                // [center - half, center + half]
                L = center - half;
                R = center + half;
            } else {
                // avoid counting the opposite residue twice
                // choose one side only:
                // [center - half + 1, center + half]
                L = center - half + 1;
                R = center + half;
            }

            // left side: positions <= center
            long leftCnt = rangeCnt(prefCnt, L, center);
            long leftSum = rangeSum(prefSum, L, center);

            // right side: positions > center
            long rightCnt = rangeCnt(prefCnt, center + 1, R);
            long rightSum = rangeSum(prefSum, center + 1, R);

            long leftCost = (long) center * leftCnt - leftSum;
            long rightCost = rightSum - (long) center * rightCnt;

            cost[t] = leftCost + rightCost;
        }

        return cost;
    }

    private long rangeCnt(long[] pref, int l, int r) {
        if (l > r) return 0;
        return pref[r + 1] - pref[l];
    }

    private long rangeSum(long[] pref, int l, int r) {
        if (l > r) return 0;
        return pref[r + 1] - pref[l];
    }
}
