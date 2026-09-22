import base.ArrayUtils;

import java.util.Arrays;

public class FindXvalueOfArrayI {

    public long[] resultArray(int[] a, int k) {
        int n = a.length;
        long[][] left = new long[n][k];
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            for (int j = 0; j < k; ++j) {
                int nv = (int) ((j * v) % k);
                left[i][nv] += (i == 0 ? 0 : left[i - 1][j]);
            }
            left[i][(int) (v % k)] += 1;
        }

        long[] res = new long[k];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                long cur = left[i][j] ;
                res[j] += cur;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindXvalueOfArrayI().resultArray(ArrayUtils.read1d("1,2,3,4,5"), 3)));
    }
}
