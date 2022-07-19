import java.util.Arrays;

public class MinDeletionToMakeArrayDivisible {
    public int minOperations(int[] a, int[] b) {
        int n = b.length;
        int cgcd = b[0];
        for (int i = 1; i < n; ++i) {
            cgcd = gcd(cgcd, b[i]);
        }
        Arrays.sort(a);
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == cgcd || cgcd % a[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
