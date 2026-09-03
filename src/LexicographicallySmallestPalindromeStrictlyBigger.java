import java.util.Arrays;

class LexicographicallySmallestPalindromePermGreater {
    public String lexPalindromicPermutation(String s, String t) {

        int[] cnt = new int[26];
        int n = s.length();
        if (n == 1) {
            return s.compareTo(t) > 0 ? s : "";
        }
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++cnt[cind];
        }
        int odd = -1;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0 && cnt[i] % 2 == 1) {
                if (odd != -1) {
                    return "";
                }
                odd = i;
            }
        }
        for (int i = 0; i < 26; ++i) {
            cnt[i] /= 2;
        }
        int mid = n / 2 - 1;
        for (int i = mid; i >= 0; --i) {
            boolean bad = false;
            int[] ncnt = Arrays.copyOf(cnt, 26);
            for (int j = 0; j < i; ++j) {
                int tind = t.charAt(j) - 'a';
                --ncnt[tind];
                if (ncnt[tind] < 0) {
                    bad = true;
                    break;
                }
            }
            if (bad) {
                continue;
            }
            int tind = t.charAt(i) - 'a';
            for (int j = tind; j < 26; ++j) {

                if (ncnt[j] > 0) {
                    // this should be at most twice.
                    int[] nncnt = Arrays.copyOf(ncnt, 26);
                    StringBuilder later = new StringBuilder();
                    later.append((char) ('a' + j));
                    --nncnt[j];
                    for (int k = i + 1; k <= mid; ++k) {
                        for (int p = 0; p < 26; ++p) {
                            if (nncnt[p] > 0) {
                                later.append((char) ('a' + p));
                                --nncnt[p];
                                break;
                            }
                        }
                    }
                    String firsthalf = t.substring(0, i) + later.toString();
                    String revfirst = new StringBuilder(firsthalf).reverse().toString();
                    String cur = "";
                    if (odd != -1) {
                        cur = firsthalf + (char) ('a' + odd) + revfirst;
                    } else {
                        cur = firsthalf + revfirst;
                    }
                    if (cur.compareTo(t) > 0) {
                        return cur;
                    }
                }
            }
        }
        return "";
    }
}
