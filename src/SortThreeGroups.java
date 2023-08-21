import java.util.List;

public class SortThreeGroups {
    public int minimumOperations(List<Integer> a) {
        int n = a.size();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int minlater = a.get(i);
            for (int j = i + 1; j < n; ++j) {
                minlater = Math.min(minlater, a.get(j));
            }
            // System.out.println(i+" "+maxlater);
            if (minlater < a.get(i)) {
                ++res;
            }
        }
        return res;
    }


}
