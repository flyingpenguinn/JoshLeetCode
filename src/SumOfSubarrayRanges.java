import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SumOfSubarrayRanges {
    // similar to SumOfSubarrayMinimums. here we turn sum of (max-min) to sum(max) - sum(min)
    public long subArrayRanges(int[] a) {
        int n = a.length;
        Deque<Integer> st1 = new ArrayDeque<>();
        Deque<Integer> st2 = new ArrayDeque<>();
        int[] lbigger = new int[n];
        int[] lsmaller = new int[n];
        int[] rbigger = new int[n];
        int[] rsmaller = new int[n];
        Arrays.fill(lbigger, -1);
        Arrays.fill(lsmaller, -1);
        Arrays.fill(rbigger, n);
        Arrays.fill(rsmaller, n);

        for (int i = 0; i < n; ++i) {
            while (!st1.isEmpty() && a[st1.peek()] <= a[i]) {
                st1.pop();
            }
            if (!st1.isEmpty()) {
                lbigger[i] = st1.peek();
            }
            while (!st2.isEmpty() && a[st2.peek()] >= a[i]) {
                st2.pop();
            }
            if (!st2.isEmpty()) {
                lsmaller[i] = st2.peek();
            }
            st1.push(i);
            st2.push(i);
        }

        st1.clear();
        st2.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!st1.isEmpty() && a[st1.peek()] < a[i]) {
                st1.pop();
            }
            if (!st1.isEmpty()) {
                rbigger[i] = st1.peek();
            }
            while (!st2.isEmpty() && a[st2.peek()] > a[i]) {
                st2.pop();
            }
            if (!st2.isEmpty()) {
                rsmaller[i] = st2.peek();
            }
            st1.push(i);
            st2.push(i);
        }

        long res = 0;

        for (int i = 0; i < n; ++i) {
            long leftbiglen = i - lbigger[i];
            long rightbiglen = rbigger[i] - i;
            long biglen = leftbiglen * rightbiglen;

            long leftsmallen = i - lsmaller[i];
            long rightsmallen = rsmaller[i] - i;
            long smalllen = leftsmallen * rightsmallen;
            long cur = (biglen - smalllen) * a[i];
            res += cur;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SumOfSubarrayRanges().subArrayRanges(ArrayUtils.read1d("1,3,3")));
    }
}
