import java.util.ArrayList;
import java.util.List;

/*
LC#916
We are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a.

Return a list of all universal words in A.  You can return the words in any order.



Example 1:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]
Example 3:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]
Example 4:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]
Example 5:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]


Note:

1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
public class WordSubsets {
    // condense b to single word take max of the counts. take max of all the counts
    public List<String> wordSubsets(String[] a, String[] b) {
        int[] bs = new int[26];
        for (String s : b) {
            int[] cs = new int[26];
            for (int j = 0; j < s.length(); j++) {
                char sc = s.charAt(j);
                int index = sc - 'a';
                cs[index]++;
            }
            for (int i = 0; i < 26; i++) {
                bs[i] = Math.max(bs[i], cs[i]);
            }
        }
        List<String> r = new ArrayList<>();
        for (String s : a) {
            int[] cs = new int[26];
            for (int j = 0; j < s.length(); j++) {
                char sc = s.charAt(j);
                int index = sc - 'a';
                cs[index]++;
            }
            boolean gd = true;
            for (int i = 0; i < 26; i++) {
                if (cs[i] < bs[i]) {
                    gd = false;
                    break;
                }
            }
            if (gd) {
                r.add(s);
            }
        }
        return r;
    }
}
