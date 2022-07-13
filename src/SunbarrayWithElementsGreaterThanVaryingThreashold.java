import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SunbarrayWithElementsGreaterThanVaryingThreashold {
    // min number needs to be bigger
    // so we iterate to see what if each number is the min number
    public int validSubarraySize(int[] a, int t) {
        int n = a.length;
        int[] right = new int[n];
        int[] left = new int[n];
        Arrays.fill(right, -1);
        Arrays.fill(left, -1);
        Deque<Integer> rst = new ArrayDeque<>();
        Deque<Integer> lst = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            while (!rst.isEmpty() && a[rst.peek()] > a[i]) {
                right[rst.pop()] = i;
            }
            rst.push(i);
        }
        //   System.out.println(Arrays.toString(right));
        for (int i = n - 1; i >= 0; --i) {
            while (!lst.isEmpty() && a[lst.peek()] > a[i]) {
                left[lst.pop()] = i;
            }
            lst.push(i);
        }

        for (int i = 0; i < n; ++i) {
            int lind = left[i] == -1 ? 0 : left[i] + 1;
            int rind = right[i] == -1 ? n - 1 : right[i] - 1;
            int needed = 0;
            if (t % a[i] == 0) {
                needed = t / a[i] + 1;
            } else {
                needed = (int) Math.ceil(1.0 * t / a[i]);
            }
            if (rind - lind + 1 >= needed) {
                return rind - lind + 1;
            }
        }
        return -1;
    }
}
