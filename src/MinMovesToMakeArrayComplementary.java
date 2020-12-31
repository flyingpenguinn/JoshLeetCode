import base.ArrayUtils;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class MinMovesToMakeArrayComplementary {
    public int minMoves(int[] nums, int limit) {
        // given a t, if t
        // between 2.. min(a,b), lower both numbers,2 changes
        // min(a,b)+1... a+b-1, lower one number, 1 change
        // a+b, 0 change
        // a+b+1... max(a,b)+limit, 1 change
        // max(a,b)+limit+1...2* limit, 2 changes
        // then we can form these intervals for each pair sum
        // then use a trick similar to meeting rooms overlap
        int n = nums.length;
        int[] d = new int[2 * limit + 2];
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - i - 1];
            d[2] += 2;
            d[min(a, b) + 1] -= 1;
            // comparing to the previous segment, from 2 to 1
            // actually -2 first then +1
            d[a + b] -= 1;
            d[a + b + 1] += 1;
            d[max(a, b) + limit + 1] += 1;
        }
        int cur = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 2; i <= 2 * limit; i++) {
            cur += d[i];
            res = min(res, cur);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MinMovesToMakeArrayComplementary().minMoves(ArrayUtils.read1d("[3,1,1,1,2,3,2,3,1,3,2,1]"), 3));
    }

}
