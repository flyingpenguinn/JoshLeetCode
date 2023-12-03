import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountCompleteSubstring {
    // break into two problems. one in sliding window with fixed window size, one in binary search
    public int countCompleteSubstrings(String w, int k) {
        return count_substrings(w, k);
    }

    private boolean allFreqK(int[] freq, int k) {
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0 && freq[i] != k) {
                return false;
            }
        }
        return true;
    }

    private int count_substrings(String s, int k) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = s.charAt(i) - 'a';
        }
        int count = 0;
        int distinct = 0;
        boolean[] found = new boolean[26];
        Arrays.fill(found, false);
        int[] good = new int[n];
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && Math.abs(a[j] - a[j - 1]) <= 2) {
                ++j;
            }
            good[i] = j - 1;
            list.add(i);
            i = j;
        }
        for (i = 0; i < s.length(); i++) {
            found[a[i]] = true;
        }
        for (i = 0; i < 26; i++) {
            if (found[i]) {
                distinct++;
            }
        }
        for (int d = 1; d <= distinct; d++) {
            int len = d * k;
            int[] freq = new int[26];
            Arrays.fill(freq, 0);
            int cstart = 0;
            int cend = cstart + len - 1;
            for (i = cstart; i <= Math.min(cend - 1, n - 1); ++i) {
                ++freq[a[i]];
            }
            for (i = cend; i < n; ++i) {
                ++freq[a[i]];
                if (cover(cstart, list, good) >= i && allFreqK(freq, k)) {
                    count++;
                }
                --freq[a[cstart]];
                ++cstart;
            }
        }
        return count;
    }

    private int cover(int t, List<Integer> a, int[] g) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) == t) {
                return g[a.get(mid)];
            } else if (a.get(mid) > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return g[a.get(u)];
    }
}
