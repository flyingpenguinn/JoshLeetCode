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
        // check null error out if needed
        // only lower case letters. non empty strings
        Map<List<Integer>, List<String>> m = new HashMap<>();
        for(String s: ss){
            List<Integer> key = getKey(s);
            m.computeIfAbsent(key, k-> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(m.values());
    }

    private List<Integer> getKey(String s){
        List<Integer> r = new ArrayList<>();
        for(int i=1; i<s.length(); i++){
            int diff = s.charAt(i)-s.charAt(i-1);
            if(diff<0){
                diff += 26;
            }
            r.add(diff);
        }
        return r;
    }
}
