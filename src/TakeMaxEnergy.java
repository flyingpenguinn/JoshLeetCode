import java.util.ArrayList;
import java.util.List;

public class TakeMaxEnergy {
    // suffix sum
    public int maximumEnergy(int[] a, int k) {
        int n = a.length;
        int res = (int) -1e9;
        for (int i = 0; i <= k - 1; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < n; j += k) {
                list.add(a[j]);
            }
            //  System.out.println(list);
            int sum = 0;
            int ln = list.size();
            int j = ln - 1;
            while (j >= 0) {
                sum += list.get(j);
                res = Math.max(res, sum);
                --j;
            }
        }
        return res;
    }
}
