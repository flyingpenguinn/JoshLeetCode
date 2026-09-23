import java.util.ArrayDeque;
import java.util.Deque;

public class StepsToMakeArrayNonDecreasing {
    // each later number needs round. for the next one, it needs round+1, and the intrinsic steps of that number
    public int totalSteps(int[] a) {
        int n = a.length;
        Deque<int[]> st = new ArrayDeque<>();
        int res = 0;
        for (int i = n - 1; i >= 0; --i) {
            int v = a[i];
            int round = 0;
            while (!st.isEmpty() && st.peek()[0] < v) {
                round = Math.max(round + 1, st.pop()[1]);
            }
            res = Math.max(res, round);
            //System.out.println(v+", round="+round);
            st.push(new int[]{v, round});
        }
        return res;
    }
}
