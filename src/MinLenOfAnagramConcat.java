import java.util.Arrays;

public class MinLenOfAnagramConcat {
    public int minAnagramLength(String s) {
        int n = s.length();
        int res = n;
        for (int q = 1; q * q <= n; ++q) {
            if (n % q == 0) {
                if (good(s, q) && q < res) {
                    res = Math.min(res, q);
                }
                if (good(s, n / q) && n / q < res) {
                    res = Math.min(res, n / q);
                }
            }
        }
        return res;
    }

    private boolean good(String s, int len) {
        //   System.out.println(len);
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < len; ++i) {
            int cind = s.charAt(i) - 'a';
            ++count[cind];
        }
        //  System.out.println(Arrays.toString(count));
        int[] scount = new int[26];
        for (int i = len; i < n; ++i) {
            if (i % len == 0) {
                Arrays.fill(scount, 0);
            }
            int cind = s.charAt(i) - 'a';
            ++scount[cind];
            if ((i + 1) % len == 0) {
                //    System.out.println(Arrays.toString(scount));
                if (!Arrays.equals(count, scount)) {
                    return false;
                }
            }
        }
        return true;
    }
}
