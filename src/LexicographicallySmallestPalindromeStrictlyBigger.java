import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class LexicographicallySmallestPalindromePermGreater {
    public String lexPalindromicPermutation(String s, String target) {
        char[] sc = s.toCharArray();
        int n = sc.length;
        int[] cnts = new int[26];
        for (int i = 0; i < n; ++i) {
            char c = sc[i];
            int cind = c - 'a';
            ++cnts[cind];
        }
        String res = "";
        if (n % 2 == 0) {
            String halft = target.substring(0, n / 2);
            String revt = halft + new StringBuilder(halft).reverse().toString();
            if (doable(revt, cnts) && revt.compareTo(target) > 0) {
                return revt;
            }
        } else {
            String halft = target.substring(0, n / 2);
            String revt = halft + target.charAt(n / 2) + new StringBuilder(halft).reverse().toString();
            if (doable(revt, cnts) && revt.compareTo(target) > 0) {
                return revt;
            }
        }
        int odds = 0;
        int oddi = -1;
        for (int i = 0; i < 26; ++i) {
            if (cnts[i] % 2 == 1) {
                ++odds;
                oddi = i;
            }
            cnts[i] /= 2;
            if (odds > 1) {
                return "";
            }
        }

        String mid = String.valueOf((char) ('a' + oddi));

        char[] tc = target.toCharArray();
        int end = n % 2 == 1 ? n / 2 : n / 2 - 1;
        for (int diff = 0; diff <= end; ++diff) {
            int[] ccnt = Arrays.copyOf(cnts, 26);
            boolean bad = false;
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < diff; ++i) {
                char ctc = tc[i];
                int cindt = ctc - 'a';
                if (ccnt[cindt] <= 0) {
                    bad = true;
                    break;
                }
                --ccnt[cindt];
                cur.append(ctc);
            }
            if (bad) {
                continue;
            }

            char ctc = tc[diff];
            int cindt = ctc - 'a';
            if (n % 2 == 1 && diff == n / 2) {
                if (ctc - 'a' >= oddi) {
                    continue;
                }
            } else {
                boolean found = false;
                for (int j = cindt + 1; j < 26; ++j) {
                    if (ccnt[j] > 0) {
                        found = true;
                        --ccnt[j];
                        cur.append((char) ('a' + j));
                        break;
                    }
                }
                if (!found) {
                    continue;
                }


                Deque<Integer> rem = new ArrayDeque<>();
                for (int j = 0; j < 26; ++j) {
                    int times = ccnt[j];
                    for (int k = 0; k < times; ++k) {
                        rem.add(j);
                    }
                }
                for (int i = diff + 1; i < n / 2; ++i) {
                    int cc = rem.pollFirst();
                    --ccnt[cc];
                    cur.append((char) (cc + 'a'));
                }
            }
            StringBuilder rev = new StringBuilder(cur).reverse();
            String cres = null;
            if (n % 2 == 1) {
                cres = cur.toString() + mid + rev.toString();
            } else {
                cres = cur.toString() + rev.toString();
            }
            if (res.isEmpty() || res.compareTo(cres) > 0) {
                res = cres;
            }
        }
        return res;
    }

    private boolean doable(String revt, int[] cnts) {
        int[] realcnt = new int[26];
        for (char c : revt.toCharArray()) {
            ++realcnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (realcnt[i] != cnts[i]) {
                return false;
            }
        }
        return true;
    }
}
