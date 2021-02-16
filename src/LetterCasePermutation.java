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
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, "", res);
        return res;
    }

    private void dfs(String s, int i, String cur, List<String> res){
        int n = s.length();
        if(i==n){
            res.add(cur);
            return;
        }
        char c = s.charAt(i);
        dfs(s, i+1, cur+c, res);
        if(Character.isUpperCase(c)){
            dfs(s, i+1, cur+Character.toLowerCase(c), res);
        }else if(Character.isLowerCase(c)){
            dfs(s, i+1, cur+Character.toUpperCase(c), res);
        }
    }
}
