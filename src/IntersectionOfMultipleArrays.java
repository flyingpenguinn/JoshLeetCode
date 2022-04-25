import java.util.*;

public class IntersectionOfMultipleArrays {
    public List<Integer> intersection(int[][] a) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a[0].length; ++i) {
            set.add(a[0][i]);
        }
        for (int i = 1; i < a.length; ++i) {
            Set<Integer> cur = new HashSet<>();
            for (int j = 0; j < a[i].length; ++j) {
                cur.add(a[i][j]);
            }
            Set<Integer> good = new HashSet<>();
            for (int si : set) {
                if (cur.contains(si)) {
                    good.add(si);
                }
            }
            set = good;
        }
        List<Integer> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }
}
