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


    public static void main(String[] args) {
        System.out.println(new LongestWellformedInterval().longestWPI(ArrayUtils.read1d("[9,6,9]")));
    }
}

class LongestWellformedIntervalStack {
    // can't do direct binary search: 1 may work, 2 may not, 3 may work again. it doesnt have monotonic feature
    // but if we turn hours to 1 and 0, then it becomes finding
    // 2*(sumi-sumj) > i-j, 2*sumi-i > 2*sumj-j
    // and now we find the smallest j for each 2*sumi-i. to do so we make a mono decreasing list and do binary search to find the first index that is < given number
    // we can also use the deque solution above to find longest j so that 2*sumj-j < 2*sumi-i
    public int longestWPI(int[] a) {
        int n = a.length;
        int[] na = new int[n];
        List<int[]> l = new ArrayList<>();
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int vi = a[i] > 8 ? 1 : 0;
            sum += vi;
            int t = 2 * sum - i;
            if (2 * sum > (i + 1)) {
                max = Math.max(max, i + 1);
            }
            int index = binarySearchFirstSmaller(l, t);
            if (index != -1) {
                max = Math.max(max, i - l.get(index)[1]);
            }
            if (l.isEmpty() || t < l.get(l.size() - 1)[0]) {
                l.add(new int[]{t, i});
            }
        }
        return max;
    }

    // binary search in a reversed array
    private int binarySearchFirstSmaller(List<int[]> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid)[0] < t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int rt = l >= a.size() ? -1 : l;
        return rt;
    }
}
