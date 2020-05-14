import java.util.*;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            if (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum swm = new SlidingWindowMaximum();
        int[] a = {1, 8, -1, -3, 5, 8, 6, 7};
        System.out.println(Arrays.toString(swm.maxSlidingWindow(a, 3)));
    }

}

class Alternative {



    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < k - 1; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        for (int i = k - 1; i < a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            r[ri++] = map.firstKey();
            Integer cur = map.get(a[i - k + 1]);
            if (cur == 1) {
                map.remove(a[i - k + 1]);
            } else {
                map.put(a[i - k + 1], cur - 1);
            }
        }
        return r;
    }
}
