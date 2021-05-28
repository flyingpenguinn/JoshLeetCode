import base.ArrayUtils;

import java.util.*;

public class LongestWellformedInterval {
    // similar to shortest subarray with some >=k. here it's the longest
    // generalizing to finding longest i....j subarray whos sum >=k
    // fix j, for  ai1, ai2, if ai1 <ai2, we dont have the reason to keep i2. so decreasing data structure, and throw away late comers
    // fix i, for all aj1, aj2, if both j1 and j2 work, we dont have reason to keep the ones worked on j2 for j1. so scan backward from n-1.
    // how do we find match: check with the end of the ds as it's decreasing. throw away qualified is
    // because we won't find better solutions for them. so it's a stack not queue

    // @SILU: compare this with shortest subarray sum at least k
    // if longest <=k not >k, mono increase stack compare rest is the same: start from tail
    // so longest-> start from tail. shortest-> start from head. and either mono increase or decrease queue
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int sum = 0;
        int[] sums = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (sum > 0) {
                max = Math.max(max, i + 1);
            }
            sums[i] = sum;
            if (dq.isEmpty() || sums[i] < sums[dq.peekLast()]) {
                dq.offerLast(i);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            // covered -1 case earlier
            while (!dq.isEmpty() && sums[i] - sums[dq.peekLast()] > 0) {
                int last = dq.pollLast();
                max = Math.max(max, i - last);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new LongestWellformedInterval().longestWPI(ArrayUtils.read1d("[9,6,9]")));
    }
}

class LongestWellformedIntervalHashMap {
    // if >, all good
    // if <=0, if this is a new low, do nothing
    // otherwise if now sum = x, it must have come from 0...x-1....x because we +1 or -1 every time.
    // x-2 must be worse than x-1 as we want "longest" x-1 is on the left of x-2
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = hours[i] > 8 ? 1 : -1;
        }
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (sum[i] > 0) {
                res = Math.max(res, i + 1);
                continue;
            }
            int needed = sum[i] - 1;
            Integer j = m.get(needed);
            if (j != null) {
                res = Math.max(res, i - j);
            }
            // note down the first occurrence
            m.putIfAbsent(sum[i], i);
        }
        return res;
    }
}
