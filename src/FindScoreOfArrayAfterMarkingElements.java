import java.util.TreeSet;

public class FindScoreOfArrayAfterMarkingElements {
    public long findScore(int[] a) {
        int n = a.length;
        // value, index
        TreeSet<int[]> ts = new TreeSet<>((x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });
        for (int i = 0; i < n; ++i) {
            ts.add(new int[]{a[i], i});
        }
        long res = 0;
        while (!ts.isEmpty()) {
            int[] top = ts.pollFirst();
            // System.out.println(Arrays.toString(top));
            int index = top[1];
            int v = top[0];
            res += v;
            if (index - 1 >= 0) {
                ts.remove(new int[]{a[index - 1], index - 1});
            }
            if (index + 1 < n) {
                ts.remove(new int[]{a[index + 1], index + 1});
            }
        }
        return res;
    }
}
