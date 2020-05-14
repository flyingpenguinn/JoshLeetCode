import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.*;

/*
LC#784
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */
public class LetterCasePermutation {

    class Solution {
        List<String> r = new ArrayList<>();

        public List<String> letterCasePermutation(String s) {
            dfs(s, 0, "");
            return r;
        }

        void dfs(String s, int i, String cur) {
            int n = s.length();
            if (i == n) {
                r.add(cur);
                return;
            }
            char c = s.charAt(i);
            if (isLetter(c)) {
                dfs(s, i + 1, cur + toLowerCase(c));
                dfs(s, i + 1, cur + toUpperCase(c));
            } else {
                dfs(s, i + 1, cur + c);
            }
        }

    }
}
