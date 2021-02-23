import base.ArrayUtils;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class MinMovesToMakeArrayComplementary {
    public int minMoves(int[] a, int limit) {
        int n = a.length;
        // if the target is from [2 to 1+ min), need 2 changes
        // from 1 + min to sum), need 1 change
        // sum, sum+1) 0 change
        // [sum+1 to limit+max+1), need 1 change
        // from limit+max+1 to 2*limit+1), need 2 changes
        // use the res array to get a running total at each possible point of target sum
        int[] res = new int[2 * limit + 2];
        // from 2 to 2*limit
        for (int i = 0; i < n / 2; i++) {
            int sum = a[i] + a[n - i - 1];
            int min = Math.min(a[i], a[n - i - 1]);
            int max = Math.max(a[i], a[n - i - 1]);
            res[2] += 2;
            res[1 + min] -= 1;
            res[sum] -= 1;
            res[sum + 1] += 1;
            res[limit + max + 1] += 1;
            res[2 * limit + 1] -= 2;
        }
        int rt = 0;
        int min = 2 * n + 1;
        for (int i = 2; i <= 2 * limit; i++) {
            rt += res[i];
            min = Math.min(min, rt);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new MinMovesToMakeArrayComplementary().minMoves(ArrayUtils.read1d("[3,1,1,1,2,3,2,3,1,3,2,1]"), 3));
    }

}
