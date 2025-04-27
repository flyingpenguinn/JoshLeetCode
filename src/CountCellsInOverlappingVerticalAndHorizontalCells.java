import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountCellsInOverlappingVerticalAndHorizontalCells {
    public int countCells(char[][] g, String p) {
        int m = g.length;
        int n = g[0].length;
        char[] horic = new char[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int pos = i * n + j;
                horic[pos] = g[i][j];
            }
        }

        char[] vertic = new char[m * n];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                int pos = j * m + i;
                vertic[pos] = g[i][j];
            }
        }
        int[] lps = computeLPSArray(p.toCharArray());
        List<Integer> horis = getMatchingIndices(p, horic, lps);
        List<Integer> vertis = getMatchingIndices(p, vertic, lps);
        int pn = p.length();
        Set<Integer> horiset = makeset(horis, pn);
        Set<Integer> vertiset = makeset(vertis, pn);
        int res = 0;
        for (int vi : vertiset) {
            int col = vi / m;
            int row = vi % m;
            int code = row * n + col;
            if (horiset.contains(code)) {
                ++res;
            }
        }
        return res;
    }

    private Set<Integer> makeset(List<Integer> v, int pn) {
        int n = v.size();
        Set<Integer> res = new HashSet<>();
        if (n == 0) {
            return res;
        }
        int start = v.get(0);
        int end = start + pn - 1;
        for (int i = 1; i < n; ++i) {
            int cs = v.get(i);
            int ce = cs + pn - 1;
            if (cs <= end) {
                end = Math.max(end, ce);
            } else {
                for (int j = start; j <= end; ++j) {
                    res.add(j);
                }
                start = cs;
                end = ce;
            }
        }
        for (int j = start; j <= end; ++j) {
            res.add(j);
        }
        return res;
    }

    protected List<Integer> getMatchingIndices(String p, char[] sc, int[] lps) {

        int j = 0; // index for a (pattern)
        int i = 0; // index for s (text)
        List<Integer> indices = new ArrayList<>();
        while (i < sc.length) {
            if (p.charAt(j) == sc[i]) {
                j++;
                i++;
            }
            if (j == p.length()) {
                indices.add(i - j);
                j = lps[j - 1];
            } else if (i < sc.length && p.charAt(j) != sc[i]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return indices;
    }

    private int[] computeLPSArray(char[] a) {
        int[] lps = new int[a.length];
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < a.length) {
            if (a[i] == a[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
