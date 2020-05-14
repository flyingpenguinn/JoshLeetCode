import java.util.*;

/*
LC#524
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordInDictThroughDeletion {
    public String findLongestWord(String s, List<String> d) {
        String max = "";
        for (String di : d) {
            if (sub(s, di)) {
                if (di.length() > max.length()) {
                    max = di;
                } else if (di.length() == max.length() && di.compareTo(max) < 0) {
                    max = di;
                }
            }
        }
        return max;
    }

    boolean sub(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == t.length();
    }

    public static void main(String[] args) {
        String[] dict = {"aaa", "aa", "a"};
        System.out.println(new LongestWordInDictThroughDeletion().findLongestWord("aaa", Arrays.asList(dict)));
    }
}
