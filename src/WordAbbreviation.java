
import java.util.*;

/*
LC#527
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
 */
public class WordAbbreviation {
    public static void main(String[] args) {
        String[] dictarray = {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion", "intrution"};
        List<String> dict = Arrays.asList(dictarray);
        System.out.println(new WordAbbreviation().wordsAbbreviation(dict));
    }

    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, TreeSet<String>> map = new HashMap<>();
        for (String s : dict) {
            String key = getkey(s);
            TreeSet<String> cur = map.getOrDefault(key, new TreeSet<>());
            cur.add(s);
            map.put(key, cur);
        }
        List<String> r = new ArrayList<>();
        for (String s : dict) {
            int sn = s.length();
            if (sn <= 3) {
                r.add(s);
                continue;
            }
            String sign = getkey(s);
            TreeSet<String> cur = map.getOrDefault(sign, new TreeSet<>());
            if (cur.size() == 1) {
                r.add("" + s.charAt(0) + (sn - 2) + s.charAt(sn - 1));
            } else {
                int rt = compare(cur, s);
                if (sn - 2 - rt <= 1) {
                    r.add(s);
                } else {
                    r.add(s.substring(0, rt + 1) + (sn - 2 - rt) + s.charAt(sn - 1));
                }
            }
        }
        return r;
    }

    // just need to compare with lower and higher
    private int compare(TreeSet<String> ext, String s) {
        String lower = ext.lower(s);
        String higher = ext.higher(s);
        // all 3 have same length. no point if the first unique one is >=len-3
        int limit = s.length() - 3;
        int i = 0;
        for (; i < limit; i++) {
            char lc = lower == null ? '#' : lower.charAt(i);
            char hc = higher == null ? '#' : higher.charAt(i);
            if (s.charAt(i) != lc && s.charAt(i) != hc) {
                break;
            } else if (s.charAt(i) != lc) {
                // clear up when we have a mismatch already
                lower = null;
            } else if (s.charAt(i) != hc) {
                higher = null;
            }
        }
        return i;
    }


    private String getkey(String s) {
        return "" + s.charAt(0) + s.charAt(s.length() - 1) + s.length();
    }
}

class WordAbbreviationTrie {
    // one trie per group
    class Trie {
        char c;
        Trie[] ch = new Trie[26];
        int count = 0;

        public Trie(char c) {
            this.c = c;
        }

        void insert(String s, int i) {
            if (i == s.length()) {
                return;
            }
            char c = s.charAt(i);
            Trie child = this.ch[c - 'a'];
            if (child == null) {
                this.ch[c - 'a'] = child = new Trie(c);
            }
            child.count++;
            child.insert(s, i + 1);
        }

        int search(String s, int i) {
            if (i == s.length()) {
                return 0;
            }
            char c = s.charAt(i);
            Trie child = this.ch[c - 'a'];
            if (child.count == 1) {
                return 0;
            }
            return 1 + child.search(s, i + 1);
        }
    }


    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> r = new ArrayList<>();
        Map<String, Trie> maps = new HashMap<>();
        for (String s : dict) {
            if (s.length() >= 3) {
                String key = getkey(s);
                Trie cur = maps.getOrDefault(key, new Trie('-'));
                cur.insert(s, 0);
                maps.put(key, cur);
            }
        }
        for (String s : dict) {
            int sn = s.length();
            if (sn <= 3) {
                r.add(s);
                continue;
            }
            Trie t = maps.get(getkey(s));
            int len = t.search(s, 0);
            if (sn - 2 - len <= 1) {
                r.add(s);
            } else {
                r.add("" + s.substring(0, len + 1) + (sn - 2 - len) + s.charAt(sn - 1));
            }
        }
        return r;
    }

    private String getkey(String s) {
        return "" + s.charAt(0) + s.charAt(s.length() - 1) + s.length();
    }
}

