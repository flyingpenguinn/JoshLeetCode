import java.util.Arrays;

public class MinTimeToVisitAllHouses {
    public long minTotalTime(int[] f, int[] b, int[] qs) {
        int n = f.length;
        long[] fsum = new long[n];
        fsum[0] = f[0];
        for (int i = 1; i < n; ++i) {
            fsum[i] = fsum[i - 1] + f[i];
        }
        long[] bsum = new long[n];
        bsum[n - 1] = b[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            bsum[i] = bsum[i + 1] + b[i];
        }
        System.out.println(Arrays.toString(fsum));

        System.out.println(Arrays.toString(bsum));
        long res = 0;
        int q1 = 0;
        for (int q2 : qs) {
            int v1 = Math.min(q1, q2);
            int v2 = Math.max(q1, q2);
            long way1 = (v2 == 0 ? 0 : fsum[v2 - 1]) - (v1 == 0 ? 0 : fsum[v1 - 1]);
            long way2 = bsum[v1 + 1] - (v2 == n - 1 ? 0 : bsum[v2 + 1]);
            long way3 = fsum[n - 1] - (v2 == 0 ? 0 : fsum[v2 - 1]) + (v1 == 0 ? 0 : fsum[v1 - 1]);
            long way4 = bsum[0] - (v1 == n - 1 ? 0 : bsum[v1 + 1]) + (v2 == n - 1 ? 0 : bsum[v2 + 1]);
            long cur = 0;
            if (q1 < q2) {
                cur = Math.min(way1, way4);
            } else {
                cur = Math.min(way2, way3);
            }
            //    System.out.println(q1+"->"+q2+" way1="+way1+" way2="+way2+" way3="+way3+" way4="+way4);
            res += cur;
            q1 = q2;
        }
        return res;
    }
}
