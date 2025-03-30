public class LongestPalindromeAfterSubstringConcatIandII {

    class Trie {
        char c;
        Trie[] ch = new Trie[26];

        int len1 = 0;
        int lenall = 0;

        public Trie(char c) {
            this.c = c;
        }
    }

    private Trie root = new Trie('*');

    public int longestPalindrome(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int[][] dps = new int[sn][sn];
        int[][] dpt = new int[tn][tn];
        int res = Math.max(maxpalinsub(s, dps), maxpalinsub(t, dpt));
        int[] maxs = new int[sn];
        for (int i = 0; i < sn; ++i) {
            for (int j = i; j < sn; ++j) {
                if (dps[i][j] == 1) {
                    maxs[i] = j - i + 1;
                }
            }
        }
        int[] maxt = new int[tn];
        for (int i = tn - 1; i >= 0; --i) {
            for (int j = i; j >= 0; --j) {
                if (dpt[j][i] == 1) {
                    maxt[i] = i - j + 1;
                }
            }
        }
        for (int i = 0; i < sn; ++i) {
            Trie p = root;
            for (int j = i; j < sn; ++j) {
                char c = s.charAt(j);
                int cind = c - 'a';
                if (p.ch[cind] == null) {
                    p.ch[cind] = new Trie(c);
                }
                int lencontri = (j + 1 == sn ? 0 : maxs[j + 1]) + (j - i + 1);
                p.ch[cind].lenall = Math.max(p.ch[cind].lenall, lencontri);
                p.ch[cind].len1 = Math.max(p.ch[cind].len1, j - i + 1);
                ;
                p = p.ch[cind];
            }
        }

        for (int i = tn - 1; i >= 0; --i) {
            Trie p = root;
            for (int j = i; j >= 0; --j) {
                char c = t.charAt(j);
                int cind = c - 'a';
                if (p.ch[cind] == null) {
                    break;
                }
                int len1 = p.ch[cind].len1;
                int len2 = i - j + 1;

                int way1 = p.ch[cind].lenall + len2;
                int way2 = len1 + len2 + (j == 0 ? 0 : maxt[j - 1]);

                int clen = Math.max(way1, way2);
                res = Math.max(res, clen);
                p = p.ch[cind];
            }
        }
        return res;
    }

    private int maxpalinsub(String s, int[][] dp) {
        int n = s.length();

        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;

                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 0;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dp[i][j] == 1) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
