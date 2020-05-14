import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*'
LC#294
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:

Input: s = "++++"
Output: true
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
Follow up:
Derive your algorithm's runtime complexity.
 */
public class FlipGameII {
    Map<String, Boolean> map = new HashMap<>();

    public boolean canWin(String s) {
        return doc(s);
    }

    boolean doc(String s) {
        List<String> next = next(s);
        if (next.isEmpty()) {
            map.put(s, false);
            return false;
        }
        Boolean ch = map.get(s);
        if (ch != null) {
            return ch;
        }
        for (String n : next) {
            if (!doc(n)) {
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    // reusing solution in #1. can just generate on the fly withotu putting into a list
    public List<String> next(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        List<String> r = new ArrayList<>();
        for (int i = 0; i + 1 < n; i++) {
            if (cs[i] == '+' && cs[i + 1] == '+') {
                cs[i] = '-';
                cs[i + 1] = '-';
                r.add(new String(cs));
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }
        return r;
    }
}
