import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxOfMinInAllSubarrays {
    // think about the cases when a[i] is the min value
    public int[] findMaximums(int[] a) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        int[] right = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < n; i++) {
            process(st, right, a, i);
        }
        st.clear();
        int[] left = new int[n];
        Arrays.fill(left, -1);
        for (int i = n - 1; i >= 0; i--) {
            process(st, left, a, i);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 1;
            res[len - 1] = Math.max(res[len - 1], a[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            res[i] = Math.max(res[i], res[i + 1]);
        }
        return res;
    }

    protected void process(Deque<Integer> st, int[] pos, int[] a, int i) {
        while (!st.isEmpty() && a[st.peek()] > a[i]) {
            pos[st.peek()] = i;
            st.pop();
        }
        st.push(i);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxOfMinInAllSubarrays().findMaximums(ArrayUtils.read1d("[2,8,1,10,4,10,8,9,3]"))));
    }
}
