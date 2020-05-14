import java.util.List;

public class ReplaceWords {
    class TrieNode {
        char v;
        boolean end = false;
        TrieNode[] ch = new TrieNode[26];

        public TrieNode(char v) {
            this.v = v;
        }

        void insert(String s, int i) {
            if (i == s.length()) {
                this.end = true;
                return;
            }
            char c = s.charAt(i);
            TrieNode next = ch[c - 'a'];
            if (next == null) {
                next = new TrieNode(c);
                ch[c - 'a'] = next;
            }
            next.insert(s, i + 1);
        }

        String lookup(String s, int i, String path) {
            if (this.end) {
                return path;
            }
            if (i == s.length()) {
                return null;
            }
            char c = s.charAt(i);
            TrieNode next = ch[c - 'a'];
            if (next == null) {
                return null;
            }
            return next.lookup(s, i + 1, path + c);
        }

    }

    TrieNode root = new TrieNode('-');

    public String replaceWords(List<String> dict, String s) {
        StringBuilder sb = new StringBuilder();
        for (String d : dict) {
            root.insert(d, 0);
        }
        String[] split = s.split(" ");
        for (String sp : split) {
            String rp = root.lookup(sp, 0, "");
            sb.append(rp == null ? sp : rp);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
