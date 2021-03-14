import java.util.*;

public class MaxScoreOfAGoodSubarray {
    // @canan!
    // <<<<>>>>> shape.
    // we ask this question: if we pick current i or j as the min, we are fixing both min and i. so how far can j go? either binary search or 2 pointers
    public int maximumScore(int[] a, int k) {
        int n = a.length;
        int[] mins = new int[n];
        mins[k] = a[k];
        for (int i = k - 1; i >= 0; i--) {
            mins[i] = Math.min(mins[i + 1], a[i]);
        }
        for (int i = k + 1; i < n; i++) {
            mins[i] = Math.min(mins[i - 1], a[i]);
        }
        int max = 0;
        int j = k;
        for (int i = k; i >= 0; i--) {
            while (j < n && mins[j] >= mins[i]) {
                j++;
            }
            int cur = mins[i] * (j - i);
            max = Math.max(cur, max);
        }
        int i = k;
        for (j = k; j < n; j++) {
            while (i >= 0 && mins[i] >= mins[j]) {
                i--;
            }
            int cur = mins[j] * (j - i);
            max = Math.max(cur, max);
        }
        return max;
    }
}

class MaxScoreOfGoodSubarrayUsingStack {
    // this is basically largest histo on mins array...
    public int maximumScore(int[] a, int k) {
        int n = a.length;
        int[] mins = new int[n];
        mins[k] = a[k];
        int max = a[k];
        for (int i = k - 1; i >= 0; i--) {
            mins[i] = Math.min(mins[i + 1], a[i]);
            max = Math.max(max, mins[i] * (k - i + 1));
        }
        for (int i = k + 1; i < n; i++) {
            mins[i] = Math.min(mins[i - 1], a[i]);
        }
        return largest(mins, k);
    }

    public int largest(int[] a, int k) {
        // when we meet a cliff we know what's in stack is ascending. then we know for each in stack it can be a starting point for its own height. nothing on the left is capable to be in that rect
        int n = a.length;
        Deque<int[]> st = new ArrayDeque<>();
        // index, height
        int res = 0;
        for (int i = 0; i <= n; i++) {
            int ch = i == n ? 0 : a[i];
            int lasti = i;
            while (!st.isEmpty() && st.peek()[1] > ch) {
                lasti = st.peek()[0];
                int lasth = st.peek()[1];
                // from lasti as starting point to i-1 all >=lasth, can for a rect

                if (i >= k && lasti <= k) {
                    int cur = lasth * (i - lasti);
                    res = Math.max(res, cur);
                }
                st.pop();
            }
            st.push(new int[]{lasti, ch});
        }
        st.pop();
        return res;
    }

}


class MaxScoreOfGoodSubarrayAnotherWay {
    // min must come from some number. suppose it's a[i]. we need to find the left and right boundaries of a[i] and these boundaries must be on either side of k
    public int maximumScore(int[] a, int k) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] left = new int[n]; // first < on the left side, or -1 if all >=
        Arrays.fill(left, -1);
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[st.peek()] >= a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                left[i] = st.peek();
            }
            st.push(i);
        }
        st.clear();
        int[] right = new int[n]; // first > on the right side
        Arrays.fill(right, n);
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && a[st.peek()] >= a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                right[i] = st.peek();
            }
            st.push(i);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] + 1 <= k && right[i] - 1 >= k) {
                // left[i]+1... right[i]-1 is the range for a[i]
                int cur = a[i] * (right[i] - left[i] - 1);
                max = Math.max(max, cur);
            }
        }
        return max;
    }
}
