import java.util.Collections;
import java.util.List;

public class MinProcessingTime {
    public int minProcessingTime(List<Integer> p, List<Integer> t) {
        Collections.sort(p);
        Collections.sort(t, Collections.reverseOrder());
        //   System.out.println(t);
        int n4 = t.size();
        int pi = 0;
        int res = 0;
        for (int i = 0; i < n4; i += 4) {
            res = Math.max(res, p.get(pi++) + t.get(i));
        }
        return res;
    }
}
