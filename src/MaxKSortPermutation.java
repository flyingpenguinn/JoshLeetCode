import java.util.ArrayList;
import java.util.List;

public class MaxKSortPermutation {


    public int sortPermutation(int[] a) {
        int n = a.length;
        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (a[i] != i) {
                if (res == -1) {
                    res = i;
                } else {
                    res &= i;
                }
            }
        }
        return res == -1 ? 0 : res;
    }
}
