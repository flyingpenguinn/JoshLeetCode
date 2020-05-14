import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.TreeMap;

public class LongestWellformedInterval {
    // change problem to finding longest >0 block in 1,-1 array. note because it's 1,-1 array,
    // when we see prefix sum x, we must have x-1 before us...
    // note we want to find sum[i]-1 because if it's indeed the longest, we must have count of 1 = count of -1 +1, if the overall sum <0
    // can't do binary search: 1 may work, 2 may not, 3 may work again. it doesnt have monotonic feature
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int sum = 0;
        // sum - index pair.keep old index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (sum > 0) {
                max = Math.max(max, i + 1);
                // directly start from 0
            }
            Integer fd = map.get(sum - 1);
            if (fd != null) {
                int cl = i - fd;
                // or take some middle value
                max = Math.max(max, cl);
            }
            // for each sum save the first occurrence
            map.putIfAbsent(sum, i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestWellformedInterval().longestWPI(ArrayUtils.read1d("[9,6,9]")));
    }
}

class LongestWellformedIntervalStack {
    // generalizing to finding longest i....j subarray whos sum >=k
    // fix j, for  ai1, ai2, if ai1 <ai2, we dont have the reason to keep i2. so decreasing data structure, and throw away late comers
    // fix i, for all aj1, aj2, if both j1 and j2 work, we dont have reason to keep j1. so scan backward from n-1.
    // how do we find match: check with the end of the ds as it's decreasing. throw away qualified is
    // because we won't find better solutions for them. so it's a stack not queue

    // @SILU: compare this with shortest subarray sum at least k
    // if longest <=k not >k, mono increase stack compare with the tail rest is the same
    public int longestWPI(int[] hours) {
        int n = hours.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + (hours[i] > 8 ? 1 : -1);
        }

        // make mono stack
        for (int i = 0; i < n; i++) {
            if (!stack.isEmpty() && sum[i] >= sum[stack.peek()]) {
                // if big and later, throw away
                continue;
            }
            stack.push(i);
        }
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (sum[i] > 0) {
                max = Math.max(max, i + 1);
            }
            // pop up self
            if (!stack.isEmpty() && stack.peek() == i) {
                stack.pop();
            }
            while (!stack.isEmpty() && sum[stack.peek()] < sum[i]) {
                // this item in stack become useless as later is would only form a smaller range
                int diff = i - stack.pop();
                max = Math.max(max, diff);
            }
        }
        return max;
    }
}
