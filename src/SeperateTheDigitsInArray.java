import java.util.ArrayList;
import java.util.List;

public class SeperateTheDigitsInArray {
    public int[] separateDigits(int[] a) {
        List<Integer> res = new ArrayList<>();
        int n = a.length;
        for (int i = n - 1; i >= 0; --i) {
            int t = a[i];
            while (t > 0) {
                res.add(t % 10);
                t /= 10;
            }
        }
        int[] rres = new int[res.size()];
        int ri = 0;
        for (int i = res.size() - 1; i >= 0; --i) {
            rres[ri++] = res.get(i);
        }
        return rres;
    }
}
