import java.util.ArrayList;
import java.util.List;

/*
LC#830
In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.



Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
 */
public class PositionsOfLargeGroups {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> r = new ArrayList<>();

        int n = s.length();
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || (s.charAt(i) != s.charAt(i - 1))) {
                if (i - start >= 3) {
                    List<Integer> v = new ArrayList<>();
                    v.add(start);
                    v.add(i - 1);
                    r.add(v);
                }
                start = i;
            }
        }
        return r;
    }
}
