import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#249
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] ss) {
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (String s : ss) {
            List<Integer> sig = sig(s);
            map.computeIfAbsent(sig, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    List<Integer> sig(String s) {
        List<Integer> r = new ArrayList<>();
        if (s.isEmpty()) {
            return r;
        }
        r.add(0);
        char pre = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            int d = (cur - pre + 26) % 26;
            r.add(d);
            pre = cur;
        }
        return r;
    }
}
