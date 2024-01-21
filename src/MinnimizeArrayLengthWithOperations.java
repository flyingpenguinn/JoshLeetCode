import java.util.Arrays;

public class MinnimizeArrayLengthWithOperations {
    public int minimumArrayLength(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        boolean alldivide = true;
        for (int i = 1; i < n; ++i) {
            if (a[i] % a[0] != 0) {
                alldivide = false;
                break;
            }
        }
        if (alldivide) {
            int i = 0;
            while (i < n && a[i] == a[0]) {
                ++i;
            }
            return (i + 1) / 2;
        } else {
            return 1;
        }
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
