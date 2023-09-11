import java.util.ArrayList;
import java.util.List;

public class StringTransformation {
    // TODO: do it myself
    class StringAlgorithm {
        public int[] getNext(String s) {
            int n = s.length();
            int[] pi = new int[n];
            for (int i = 1; i < n; ++i) {
                int j = pi[i - 1];
                while (j > 0 && s.charAt(j) != s.charAt(i)) j = pi[j - 1];
                if (j == 0 && s.charAt(0) != s.charAt(i)) pi[i] = 0;
                else pi[i] = j + 1;
            }
            return pi;
        }

        public List<Integer> kmp(String s, String t) {
            int[] pi = getNext(t);
            int m = s.length(), n = t.length(), j = 0;
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                while (j >= n || (j > 0 && s.charAt(i) != t.charAt(j))) j = pi[j - 1];
                if (s.charAt(i) == t.charAt(j)) j++;
                if (j == n) res.add(i - n + 1);
            }
            return res;
        }
    }

    class Solution {
        public long pow(long a, long b, int M) {
            long r = 1, t = a;
            while (b != 0) {
                if ((b & 1) != 0) r = (r * t) % M;
                t = (t * t) % M;
                b >>= 1;
            }
            return r;
        }

        public int numberOfWays(String s, String t, long k) {
            int n = s.length(), M = 1000000007;
            List<Integer> pos = new StringAlgorithm().kmp(s + s, t);
            if (pos.size() > 0 && pos.get(pos.size() - 1).equals(n))
                pos.remove(pos.size() - 1);
            long[] f_k = {0, 0};
            f_k[1] = (pow(n - 1, k, M) + (k % 2 * 2 - 1) + M) % M * pow(n, M - 2, M) % M;
            f_k[0] = (f_k[1] - (k % 2 * 2 - 1) + M) % M;

            int res = 0;
            for (Integer p : pos) {
                if (p == 0) res = (res + (int) f_k[0]) % M;
                else res = (res + (int) f_k[1]) % M;
            }
            return res;
        }
    }

}
