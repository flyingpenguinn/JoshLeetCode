import java.util.*;

/*
LC#49
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
 */
public class GroupAnagrams {
    // sort to get signature then use map to group
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            String sig = getsig(s);
            List<String> cur = m.get(sig);
            if (cur == null) {
                cur = new ArrayList<>();
                m.put(sig, cur);
            }
            cur.add(s);
        }

        return new ArrayList<>(m.values());
    }

    String getsig(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        return new String(cs);
    }
}
