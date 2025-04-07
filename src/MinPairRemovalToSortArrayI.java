import java.util.ArrayList;
import java.util.List;

public class MinPairRemovalToSortArrayI {

    private boolean shouldWork(List<Integer> a) {
        int n = a.size();
        for (int i = 0; i + 1 < n; ++i) {
            if (a.get(i) > a.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public int minimumPairRemoval(int[] a) {
        int n = a.length;
        List<Integer> ca = new ArrayList<>();
        for (int ai : a) {
            ca.add(ai);
        }
        int res = 0;
        while (shouldWork(ca)) {
            System.out.println(ca);
            int minsum = (int) 1e9;
            int mini = -1;
            for (int i = 0; i + 1 < ca.size(); ++i) {
                if (ca.get(i) + ca.get(i + 1) < minsum) {
                    minsum = ca.get(i) + ca.get(i + 1);
                    mini = i;
                }
            }
            List<Integer> list = new ArrayList<>();
            int i = 0;
            while (i < ca.size()) {
                if (i == mini) {
                    list.add(minsum);
                    i += 2;
                } else {
                    list.add(ca.get(i));
                    ++i;
                }
            }
            ca = list;
            ++res;
        }
        // System.out.println(ca);
        return res;
    }
}
