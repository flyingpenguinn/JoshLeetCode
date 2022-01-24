import java.util.ArrayList;
import java.util.List;

public class RearrangeArrayElementsBySign {
    public int[] rearrangeArray(int[] a) {
        int n = a.length;
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        for (int ai : a) {
            if (ai < 0) {
                neg.add(ai);
            } else {
                pos.add(ai);
            }
        }
        int[] res = new int[n];
        int ri = 0;
        int i = 0;
        int j = 0;
        while (ri < n) {
            if (ri % 2 == 0) {
                res[ri++] = pos.get(j++);
            } else {
                res[ri++] = neg.get(i++);
            }
        }
        return res;
    }
}
