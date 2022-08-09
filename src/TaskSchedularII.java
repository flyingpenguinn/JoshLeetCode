import java.util.HashMap;
import java.util.Map;

public class TaskSchedularII {
    public long taskSchedulerII(int[] a, int space) {
        Map<Integer, Long> m = new HashMap<>();
        int n = a.length;
        long cur = 1;
        for (int i = 0; i < n; ++i) {
            Long last = m.get(a[i]);
            if (last != null) {
                long waittill = last + space + 1;
                cur = Math.max(cur, waittill);
            }
            //System.out.println(a[i]+" "+cur);
            m.put(a[i], cur);
            ++cur;
        }
        return cur - 1;
    }
}
