import java.util.TreeMap;

public class FindIndecesWithValueAndDiffIandII {
    public int[] findIndices(int[] a, int id, int vd) {
        int n = a.length;
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            int head = i - id;
            if (head >= 0) {
                ts.putIfAbsent(a[head], head);
            }
            if (!ts.isEmpty()) {
                Integer low = ts.firstKey();
                Integer high = ts.lastKey();
                if (Math.abs(low - a[i]) >= vd) {
                    int pid = ts.get(low);
                    return new int[]{pid, i};
                }
                if (Math.abs(high - a[i]) >= vd) {
                    int pid = ts.get(high);
                    return new int[]{pid, i};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
