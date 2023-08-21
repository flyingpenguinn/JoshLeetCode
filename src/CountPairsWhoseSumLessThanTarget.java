import java.util.Collections;
import java.util.List;

public class CountPairsWhoseSumLessThanTarget {
    public int countPairs(List<Integer> a, int t) {
        int n = a.size();
        Collections.sort(a);
        //   System.out.println(a);
        int l = 0;
        int u = n - 1;
        int res = 0;
        while (l < u) {
            int sum = a.get(l) + a.get(u);
            if (sum >= t) {
                --u;
            } else {
                //    System.out.println(l+" "+u);
                int count = u - l;
                res += count;
                ++l;
            }
        }
        return res;
    }
}
