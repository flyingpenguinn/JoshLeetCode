import java.util.*;

/*
LC#676
Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class ImplementMagicDictionary {
}


class MagicDictionary {
    // way1: loop through 26 chars

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {

    }

    Set<String> set = new HashSet<>();

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String d : dict) {
            set.add(d);
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == c) {
                    continue;
                }
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, k);
                if (set.contains(sb.toString())) {
                    return true;
                }

            }
        }
        return false;
    }
}

class MagicDictionaryAlternative {
// way2: use _ to indicate a possible mismathc. note we have to use a set to tell if the word is in dict and if so, it must match >=2

    /**
     * Initialize your data structure here.
     */
    public MagicDictionaryAlternative() {

    }

    Set<String> set = new HashSet<>();
    Map<String, Integer> m = new HashMap<>();

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String s : dict) {
            for (int i = 0; i < s.length(); i++) {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(i, '_');
                String str = sb.toString();
                m.put(str, m.getOrDefault(str, 0) + 1);
            }
            set.add(s);
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        boolean inset = set.contains(word);
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.setCharAt(i, '_');
            String str = sb.toString();
            if (!inset) {
                boolean rt = m.getOrDefault(str, 0) >= 1;
                if (rt) {
                    return true;
                }
            } else {
                boolean rt = m.getOrDefault(str, 0) >= 2;
                if (rt) {
                    return true;
                }
            }
        }
        return false;
    }
}
