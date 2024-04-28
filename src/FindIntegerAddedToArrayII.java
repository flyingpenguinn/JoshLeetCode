import java.util.Arrays;

public class FindIntegerAddedToArrayII {
    public int minimumAddedInteger(int[] a, int[] b) {
        int n = a.length;
        int bn = b.length;
        int res = (int) 1e9;
        Arrays.sort(b);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int[] na = new int[bn];
                int nai = 0;
                for (int k = 0; k < n; ++k) {
                    if (k == i || k == j) {
                        continue;
                    }
                    na[nai++] = a[k];
                }
                Arrays.sort(na);
                //   System.out.println(i+" "+j+" "+Arrays.toString(na));
                boolean bad = false;
                int delta = b[0] - na[0];
                //  System.out.println("delta="+delta);
                for (int k = 1; k < bn; ++k) {
                    if (na[k] + delta != b[k]) {
                        bad = true;
                        break;
                    }
                }
                if (!bad) {
                    res = Math.min(res, delta);
                }
            }
        }
        return res;
    }
}
