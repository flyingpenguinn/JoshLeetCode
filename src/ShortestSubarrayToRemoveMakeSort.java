import java.util.TreeMap;

public class ShortestSubarrayToRemoveMakeSort {
    public int findLengthOfShortestSubarray(int[] a) {
        int n = a.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int i = n - 1;
        for (; i >= 0; i--) {
            if (i + 1 < n && a[i + 1] < a[i]) {
                break;
            }
            tm.put(a[i], i);
        }
        int end = i;
        // delete 0... end in the worst case because i...n-1 are sorted already
        int min = end + 1;
        // we then check if 0...end can give us better results
        for (i = 0; i <= end; i++) {
            if (i > 0 && a[i - 1] > a[i]) {
                break;
            }
            Integer next = tm.ceilingKey(a[i]);
            int nindex = n;
            if (next != null) {
                nindex = tm.get(next);
            }
            // from i+1 to nindex-1 gone
            int cur = nindex - i - 1;
            min = Math.min(min, cur);
        }
        return min;
    }
}
