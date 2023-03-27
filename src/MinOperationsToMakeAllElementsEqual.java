import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinOperationsToMakeAllElementsEqual {
    public List<Long> minOperations(int[] a, int[] queries) {
        int n = a.length;
        Arrays.sort(a);
        long[] psum = new long[n];
        psum[0] = a[0];
        for (int i = 1; i < n; ++i) {
            psum[i] = 0L + psum[i - 1] + a[i];
        }
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < queries.length; ++i) {
            long q = queries[i];
            // first one that is >=q
            int pos = binary(a, q);
            //    System.out.println(q+" "+pos);
            long pre = pos == 0 ? 0 : psum[pos - 1];
            long smaller = q * pos - pre;
            long nonsmaller = (psum[n - 1] - pre) -  q * (n - pos);
            res.add(smaller + nonsmaller);

        }
        return res;
    }

    private int binary(int[] a, long t) {
        int l = 0;
        int u = a.length - 1;
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
