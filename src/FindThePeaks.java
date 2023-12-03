import java.util.ArrayList;
import java.util.List;

public class FindThePeaks {
    public List<Integer> findPeaks(int[] a) {
        int n = a.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < n - 1; ++i) {
            if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                res.add(i);
            }
        }
        return res;
    }
}
