import java.util.Arrays;

public class MaxProductOfThreeElementsAfterOneReplacement {
    public long maxProduct(int[] a) {
        Arrays.sort(a);
        long cand1 = (long)1e5;
        long cand2 = -cand1;
        int n = a.length;
        long v1 = a[n-1];
        long v2 = a[n-2];
        long way1 = v1*v2*cand1;

        long v3 = a[0];
        long v4 = a[1];
        long way2 = v3*v4*cand1;

        long v5 = a[n-1];
        long v6 = a[0];
        long way3 = v5*v6*cand2;

        long res = Math.max(way1, Math.max(way2, way3));
        return res;
    }
}
