import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
LC#648
In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.



Example 1:

Input: dict = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"


Constraints:

The input will only have lower-case letters.
1 <= dict.length <= 1000
1 <= dict[i].length <= 100
1 <= sentence words number <= 1000
1 <= sentence words length <= 1000
 */
public class ReplaceWords {
    // using trie we dont need .contains which could be == 100. so it becomes 1000*100
    class Trie {
        char data;
        Trie[] ch = new Trie[26];
        boolean isword = false;

        public Trie(char data) {
            this.data = data;
        }

        void insert(String s, int i) {
            if (i == s.length()) {
                isword = true;
                return;
            }
            char c = s.charAt(i);
            int cind = c - 'a';
            Trie next = ch[cind];
            if (next == null) {
                next = ch[cind] = new Trie(c);
            }
            next.insert(s, i + 1);
        }

    }

    Trie root = new Trie('-');

    public String replaceWords(List<String> dict, String sentence) {
        for (String d : dict) {
            root.insert(d, 0);
        }
        StringBuilder sb = new StringBuilder();
        String[] ss = sentence.split(" ");
        for (String s : ss) {
            String root = findroot(s);
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(root);
        }
        return sb.toString();
    }

    String findroot(String s) {
        Trie cur = root;
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            int cind = c - 'a';
            Trie next = cur.ch[cind];
            if (next == null) {
                // not found
                break;
            } else if (next.isword) {
                found = true;
                break;
            }
            cur = next;
        }
        return found ? sb.toString() : s;
    }
}

class ReplaceWordsNotrie {
    // the set.contains could be ==100, so overall could be 1000*100^2
    public String replaceWords(List<String> dict, String sentence) {
        Set<String> set = new HashSet<>();
        int maxlen = 0;
        for (String d : dict) {
            set.add(d);
            maxlen = Math.max(maxlen, d.length());
        }
        String[] ss = sentence.split(" ");
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            StringBuilder sb = new StringBuilder();
            boolean found = false;
            for (int j = 0; j < ss[i].length() && j <= maxlen; j++) {
                sb.append(ss[i].charAt(j));
                if (set.contains(sb.toString())) {
                    found = true;
                    break;
                }
            }
            if (r.length() > 0) {
                r.append(" ");
            }
            r.append(found ? sb.toString() : ss[i]);
        }
        return r.toString();
    }
}
