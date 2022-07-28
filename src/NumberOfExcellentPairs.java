import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberOfExcellentPairs {
    public long countExcellentPairs(int[] input, int k) {
        Set<Integer> set = new HashSet<>();
        for (int ii : input) {
            set.add(ii);
        }
        int[] a = new int[set.size()];
        int ai = 0;
        for (int si : set) {
            a[ai++] = Integer.bitCount(si);
        }
        Arrays.sort(a);
        //     System.out.println(Arrays.toString(a));
        int n = a.length;
        long res = 0L;
        for (int i = 0; i < n; ++i) {
            // pos of the first number that makes sum >=
            int pos = binarysearch(a, i, n - 1, k - a[i]);

            long counts = (0L + n - pos);
            int dist = pos - i;
            if (pos == i) {
                res += 1 + 2L * (counts - 1);
            } else {
                res += 2L * counts;
            }
        }
        return res;
    }

    // first number >=t
    private int binarysearch(int[] a, int l, int u, int t) {
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
