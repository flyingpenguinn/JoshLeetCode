import java.util.Arrays;

public class NumberOfStringsWithDominantOnes {
    // key is jump to the next 0
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            a[i] = cind;
        }
        int[] next0 = new int[n + 1];
        Arrays.fill(next0, n);
        for (int i = n - 1; i >= 0; --i) {
            next0[i] = next0[i + 1];
            if (a[i] == 0) {
                next0[i] = i;
            }
        }
        int limit = (int) Math.sqrt(n) + 1;
        int res = 0;
        // start from i, looking at i itself, or a 0 at j (max sqrt(n) from i), next 0 is k
        for (int i = 0; i < n; ++i) {
            int c0 = 0;
            int j = i;
            while (j < n && c0 <= limit) {
                c0 += a[j] == 0 ? 1 : 0;
                int k = next0[j + 1];
                // now j+1.... k-1 is all 1
                // check i...k-1 situation
                int all = k - i;
                int ones = all - c0;
                // is i...k-1 qualified
                if (ones >= c0 * c0) {
                    // how many ones in j+1... k-1 can we "sacrifice"
                    // it's ok value from c0*c0 to ones, so in all = buf values we can take
                    int buf = ones - c0 * c0 + 1;
                    // balance it with the length of j+1... k-1
                    int cur = Math.min(buf, k - j);
                    res += cur;
                }
                j = next0[j + 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfStringsWithDominantOnes().numberOfSubstrings("00011"));
        System.out.println(new NumberOfStringsWithDominantOnes().numberOfSubstrings("101101"));

    }


}
