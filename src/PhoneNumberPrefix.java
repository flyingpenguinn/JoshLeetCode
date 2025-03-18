import java.util.Arrays;

public class PhoneNumberPrefix {
    private class Trie {
        int c;

        public Trie(int c) {
            this.c = c;
        }

        Trie[] ch = new Trie[10];
    }

    private Trie t = new Trie('*');

    private void insert(String s) {
        int n = s.length();
        Trie p = t;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            if (p.ch[cind] == null) {
                p.ch[cind] = new Trie(cind);
            }
            p = p.ch[cind];
        }
    }

    private boolean find(String s) {
        int n = s.length();
        Trie p = t;
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            if (p.ch[cind] == null) {
                return false;
            }
            p = p.ch[cind];
        }
        return true;
    }

    public boolean phonePrefix(String[] s) {
        Arrays.sort(s, (x, y) -> Integer.compare(x.length(), y.length()));
        for (int i = s.length - 1; i >= 0; --i) {
            if (find(s[i])) {
                return false;
            }
            insert(s[i]);

        }
        return true;

    }
}
