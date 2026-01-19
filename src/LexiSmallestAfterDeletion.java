import java.util.HashSet;
import java.util.Set;

public class LexiSmallestAfterDeletion {
    public String lexSmallestAfterDeletion(String s) {
        int n = s.length();
        int[][] sufcnt = new int[n][26];
        int[][] nxt = new int[n][26];
        Set<Character> set = new HashSet<>();
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            int cind = c - 'a';
            for (int j = 0; j < 26; ++j) {
                sufcnt[i][j] = (i == n - 1 ? 0 : sufcnt[i + 1][j]);
                nxt[i][j] = (i == n - 1 ? -1 : nxt[i + 1][j]);
            }
            ++sufcnt[i][cind];
            nxt[i][cind] = i;
            set.add(c);
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (!set.isEmpty()) {

            for (char c = 'a'; c <= 'z'; ++c) {
                boolean good = true;
                int cind = c - 'a';
                if (nxt[i][cind] == -1) {
                    continue;
                }
                int newi = nxt[i][cind];
                for (char sc : set) {
                    int sind = sc - 'a';
                    if (sind == cind) {
                        continue;
                    }
                    if (set.contains(sc)) {
                        if (newi == n - 1 || sufcnt[newi + 1][sind] == 0) {
                            // it makes us cannot choose a required one
                            good = false;
                            break;
                        }
                    }
                }
                if (!good) {
                    continue;
                }
                sb.append(c);
                if (set.contains(c)) {
                    set.remove(c);
                }
                i = newi + 1;
                break;
            }
        }

        return sb.toString();
    }
}
