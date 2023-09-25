import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BeautifulTowersI {

    public long maximumSumOfHeights(List<Integer> a) {
        int n = a.size();
        long res = 0;
        for (int i = 0; i < n; ++i) {
            List<Integer> ia = new ArrayList<>(a);
            long cres = ia.get(i);
            for (int j = i - 1; j >= 0; --j) {
                int later = ia.get(j + 1);
                int cv = Math.min(later, ia.get(j));
                ia.set(j, cv);
                cres += cv;
            }
            for (int j = i + 1; j < n; ++j) {
                int later = ia.get(j - 1);
                int cv = Math.min(later, ia.get(j));
                ia.set(j, cv);
                cres += cv;
            }
            //   System.out.println(ia);
            res = Math.max(res, cres);
        }
        return res;
    }

}
