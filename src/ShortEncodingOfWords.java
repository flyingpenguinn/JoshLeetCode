import java.util.Arrays;
import java.util.Comparator;

public class ShortEncodingOfWords {
    // suffix trie tree
    class TrieNode {
        char v;
        TrieNode[] ch = new TrieNode[26];

        public TrieNode(char v) {
            this.v = v;
        }

        // return whether it's fully incorporated in the tree i.e. no new node
        boolean insert(String w, int i, boolean created) {
            if (i == -1) {
                return created;
            }
            char c = w.charAt(i);
            int index = c - 'a';
            TrieNode child = ch[index];
            if (child == null) {
                ch[index] = new TrieNode(c);
                return ch[index].insert(w, i - 1, true);
            } else {
                return ch[index].insert(w, i - 1, created);
            }
        }

    }

    TrieNode root = new TrieNode('-');

    class StringComparator implements Comparator<String> {

        @Override
        public int compare(String x, String y) {
            return x.length() == y.length() ? x.compareTo(y) : Integer.compare(x.length(), y.length());
        }
    }

    public int minimumLengthEncoding(String[] words) {
        // sort by len first
        Arrays.sort(words, new StringComparator());
        int n = words.length;
        int len = 0;
        for (int i = n - 1; i >= 0; i--) {
            boolean created = root.insert(words[i], words[i].length() - 1, false);
            if (created) {
                len += words[i].length() + 1;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        String[] words = {"time", "atime", "btime"};
        System.out.println(new ShortEncodingOfWords().minimumLengthEncoding(words));
    }
}
