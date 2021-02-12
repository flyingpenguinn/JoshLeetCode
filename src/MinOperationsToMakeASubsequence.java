import java.util.*;

public class MinOperationsToMakeASubsequence {
    // actually finding LCS. but it's n^2
    // LCS can be converted to LIS and solve in nlogn if one of the string is of unique values!  we convert the other array to indxes of the first
    public int minOperations(int[] t, int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < t.length; i++) {
            m.put(t[i], i);
        }
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (m.containsKey(a[i])) {
                int index = m.get(a[i]);
                int pos = binary(list, index);
                if (pos == list.size()) {
                    list.add(index);
                } else {
                    list.set(pos, index);
                }
                max = Math.max(max, list.size());
            }
        }
        return t.length - max;
    }

    private int binary(List<Integer> list, int index) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) >= index) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
