import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountMagicalSubstringPairsII {
    public long countMagicalPairs(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int limit = 27;
        int[][] leftocc = new int[n][limit];
        int[][] rightocc = new int[n][limit];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(rightocc[i], -1);
            Arrays.fill(leftocc[i], -1);
        }
        for (int i = n - 1; i >= 0; --i) {
            int cind = c[i] - 'a';
            if (i == n - 1) {
                rightocc[i] = new int[limit];
                Arrays.fill(rightocc[i], -1);
            } else {
                rightocc[i] = Arrays.copyOf(rightocc[i + 1], limit);
            }
            rightocc[i][cind] = i;
        }
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - 'a';
            if (i == 0) {
                leftocc[i] = new int[limit];
                Arrays.fill(leftocc[i], -1);
            } else {
                leftocc[i] = Arrays.copyOf(leftocc[i - 1], limit);
            }

            leftocc[i][cind] = i;
        }
        long[][] right = new long[n][limit + 1];
        for (int i = 0; i < n; ++i) {
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < limit; ++j) {
                if (rightocc[i][j] != -1) {
                    l.add(rightocc[i][j]);
                }
            }
            l.add(n);
            Collections.sort(l);
            for (int j = 0; j + 1 < l.size(); ++j) {
                // between j and j+1 th char there we have j+1 distinct chars
                int diff = l.get(j + 1) - l.get(j);
                right[i][j + 1] = diff;
            }
        }

        long[][] left = new long[n][limit + 1];
        for (int i = n - 1; i >= 0; --i) {
            int[] nf = new int[limit];
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < limit; ++j) {
                if (leftocc[i][j] != -1) {
                    l.add(leftocc[i][j]);
                }
            }
            l.add(-1);
            Collections.sort(l, Collections.reverseOrder());
            for (int j = 0; j + 1 < l.size(); ++j) {
                // between j and j+1 th char there we have j+1 distinct chars
                int diff = l.get(j) - l.get(j + 1);
                left[i][j + 1] = diff;
            }
        }
        long res = 0;
        for (int i = 0; i+1 < n; ++i) {
            for (int j = 1; j < limit; ++j) {
                long cres = right[i+1][j] * left[i][j];
                res += cres;
            }
        }
        return res;
    }
}
