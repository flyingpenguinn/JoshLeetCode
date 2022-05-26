import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfVisiblePeopleOnaQueue {
    // from left: add stack back each time as this is the person it can see.
    public int[] canSeePersonsCount(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                int j = st.pop();
                res[j]++;
            }
            if (!st.isEmpty()) {
                res[st.peek()]++;
            }
            st.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        NumberOfVisiblePeopleOnaQueueFromRight np = new NumberOfVisiblePeopleOnaQueueFromRight();
        System.out.println(np.canSeePersonsCount(ArrayUtils.read1d("[10,6,8,5,11,9]")));
    }
}


class NumberOfVisiblePeopleOnaQueueFromRight {
    public int[] canSeePersonsCount(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int accu = 1;
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                res[i] = accu++;
                st.pop();
            }
            if (!st.isEmpty()) {
                res[i]++;
            }
            st.push(i);
        }
        return res;
    }
}