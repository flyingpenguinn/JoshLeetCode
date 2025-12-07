import java.util.Arrays;

public class SortIntegerByBinaryReflection {
    public int[] sortByReflection(int[] a) {
        int n = a.length;
        int[][] res = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            String str = Integer.toBinaryString(v);
            String rev = new StringBuilder(str).reverse().toString();
            //  System.out.println(rev);
            res[i][0] = Integer.parseInt(rev, 2);
            // System.out.println(res[i][0]);
            res[i][1] = v;
        }
        Arrays.sort(res, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });
        int[] rres = new int[n];
        for (int i = 0; i < n; ++i) {
            rres[i] = res[i][1];
        }
        return rres;
    }
}
