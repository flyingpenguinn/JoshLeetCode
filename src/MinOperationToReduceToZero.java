import java.util.HashMap;
import java.util.Map;

public class MinOperationToReduceToZero {
    public int minOperations(int[] a, int x) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        int t = sum - x;
        if (t == 0) {
            return n;
        }
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int psum = 0;
        int max = -1;
        for (int i = 0; i < n; i++) {
            psum += a[i];
            //  System.out.println(psum+" "+t+" "+(psum-t));
            if (m.containsKey(psum - t)) {
                int cur = i - m.get(psum - t);
                max = Math.max(cur, max);
            }
            m.putIfAbsent(psum, i);
        }
        return max == -1 ? -1 : n - max;
    }
}
