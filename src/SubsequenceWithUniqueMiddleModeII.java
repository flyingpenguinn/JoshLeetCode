import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class SubsequenceWithUniqueMiddleModeII {


    // TODO: review this myself
    private static final int M = 1000000007;
    private static final int RM = (M + 1) / 2;

    private int mul(long x, long y) {
        return (int) (x * y % M);
    }

    private int sqr(int x) {
        return mul(x, x);
    }

    private int add(int x, int y) {
        x += y;
        if (x >= M) {
            x -= M;
        }
        return x;
    }

    private int sub(int x, int y) {
        x -= y;
        if (x < 0) {
            x += M;
        }
        return x;
    }

    private int C2(int n) {
        return mul(mul(n, n - 1), RM);
    }

    private int count(int[] v, int[] c, boolean countIfEqual) {
        int n = v.length, m = c.length;
        int[] t = new int[m];
        int r = 0, R2 = 0;

        for (int i = 0; i < m; ++i) {
            R2 = add(R2, sqr(c[i]));
        }

        int RT = 0, R2T = 0;
        for (int i = 0; i < n; ++i) {
            int x = v[i];
            int rx = c[x] - t[x];
            int r2 = sub(R2, sqr(rx));
            int rt = sub(RT, mul(rx, t[x]));
            int r2t = sub(R2T, mul(sqr(rx), t[x]));
            int p = n - i - rx;
            int sumt = i - t[x];

            int temp = mul(sub(sqr(p), r2), sumt);
            temp = sub(temp, mul(mul(2, p), rt));
            temp = add(temp, mul(2, r2t));
            temp = mul(temp, mul(t[x], RM));
            r = add(r, temp);

            r = add(r, mul(C2(t[x]), C2(p)));

            --rx;
            r = add(r, mul(C2(t[x]), mul(rx, p)));

            if (countIfEqual) {
                r = add(r, mul(mul(t[x], sumt), mul(rx, p)));
                r = add(r, mul(C2(t[x]), C2(rx)));
            }

            ++t[x];
            R2 = add(r2, sqr(rx));
            RT = add(rt, mul(rx, t[x]));
            R2T = add(r2t, mul(sqr(rx), t[x]));
        }
        return r;
    }

    public int subsequencesWithMiddleMode(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int m = 0;

        for (int i = 0; i < nums.length; ++i) {
            mp.putIfAbsent(nums[i], m++);
            nums[i] = mp.get(nums[i]);
        }

        int[] c = new int[m];
        for (int x : nums) {
            ++c[x];
        }

        int r = count(nums, c, true);
        reverse(nums);
        return add(r, count(nums, c, false));
    }

    private void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        System.out.println(new SubsequenceWithUniqueMiddleModeII().subsequencesWithMiddleMode(ArrayUtils.read1d("[0,1,2,3,4,5,6,7,8]")));
    }

}
