import java.util.*;

public class MinOperationsToMakeASubsequence {
    // actually finding LCS. but it's n^2
    // LCS can be converted to LIS and solve in nlogn if one of the string is of unique values!  we convert the other array to indxes of the first
    public int minOperations(int[] a, int[] b) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            m.put(a[i], i);
        }
        int[] b2 = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            b2[i] = m.getOrDefault(b[i], -1);
        }
        // lis on b2
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < b2.length; i++) {
            if (b2[i] != -1) {
                int pos = Collections.binarySearch(list, b2[i]);
                if (pos < 0) {
                    pos = -(pos + 1);
                }
                //   System.out.println(list);
                //  System.out.println(b2[i]+" "+pos);
                if (pos == list.size()) {
                    list.add(b2[i]);
                } else {
                    list.set(pos, b2[i]);
                }
            }
        }
        return a.length - list.size();
    }
}
