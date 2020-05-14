import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
LC#17
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationOfPhoneNumber {
    String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String d) {
        if (d.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> r = dol(d, 0);
        Collections.sort(r);
        return r;
    }

    List<String> dol(String d, int i) {
        List<String> r = new ArrayList<>();
        if (i == d.length()) {
            r.add("");
            return r;
        }
        List<String> later = dol(d, i + 1);
        String cs = keys[d.charAt(i) - '0'];
        if (cs.isEmpty()) {
            return later;
        }
        for (String s : later) {
            for (int j = 0; j < cs.length(); j++) {
                r.add(cs.charAt(j) + s);
            }
        }
        return r;
    }
}

