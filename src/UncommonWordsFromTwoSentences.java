import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#884
We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words.

You may return the list in any order.



Example 1:

Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
Example 2:

Input: A = "apple apple", B = "banana"
Output: ["banana"]


Note:

0 <= A.length <= 200
0 <= B.length <= 200
A and B both contain only spaces and lowercase letters.
 */
public class UncommonWordsFromTwoSentences {
    public String[] uncommonFromSentences(String a, String b) {
        Map<String, Integer> m1 = new HashMap<>();
        for (String s : a.split(" ")) {
            m1.put(s, m1.getOrDefault(s, 0) + 1);
        }
        Map<String, Integer> m2 = new HashMap<>();

        for (String s : b.split(" ")) {
            m2.put(s, m2.getOrDefault(s, 0) + 1);

        }
        List<String> rl = new ArrayList<>();
        for (String s : m1.keySet()) {
            if (m1.get(s) == 1 && !m2.containsKey(s)) {
                rl.add(s);
            }
        }
        for (String s : m2.keySet()) {
            if (m2.get(s) == 1 && !m1.containsKey(s)) {
                rl.add(s);
            }
        }
        String[] r = new String[rl.size()];
        for (int i = 0; i < r.length; i++) {
            r[i] = rl.get(i);
        }
        return r;
    }
}
