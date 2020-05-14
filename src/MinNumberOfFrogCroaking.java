import java.util.HashMap;
import java.util.Map;

/*
LC#1419
Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple “croak” are mixed. Return the minimum number of different frogs to finish all the croak in the given string.

A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of valid "croak" return -1.



Example 1:

Input: croakOfFrogs = "croakcroak"
Output: 1
Explanation: One frog yelling "croak" twice.
Example 2:

Input: croakOfFrogs = "crcoakroak"
Output: 2
Explanation: The minimum number of frogs is two.
The first frog could yell "crcoakroak".
The second frog could yell later "crcoakroak".
Example 3:

Input: croakOfFrogs = "croakcrook"
Output: -1
Explanation: The given string is an invalid combination of "croak" from different frogs.
Example 4:

Input: croakOfFrogs = "croakcroa"
Output: -1


Constraints:

1 <= croakOfFrogs.length <= 10^5
All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
 */
public class MinNumberOfFrogCroaking {

    // maintain a map of states for each frog. count he max waiting frog
    public int minNumberOfFrogs(String croakOfFrogs) {
        Map<Character, Integer> pending = new HashMap<>();
        int max = 0;
        int count = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            if (c == 'c') {
                update(pending, c, 1);
                count++;
            } else {
                char pre = pre(c);
                if (!pending.containsKey(pre)) {
                    return -1;
                }
                update(pending, pre, -1);
                if (c != 'k') {
                    update(pending, c, 1);
                } else {
                    count--;
                }
            }

            max = Math.max(max, count);
        }
        if (pending.isEmpty()) {
            return max;
        } else {
            return -1;
        }
    }

    private void update(Map<Character, Integer> pending, char c, int delta) {
        int nv = pending.getOrDefault(c, 0) + delta;
        if (nv <= 0) {
            pending.remove(c);
        } else {
            pending.put(c, nv);
        }
    }

    String croak = "croak";

    private char pre(char c) {
        int i1 = croak.indexOf(c);
        return croak.charAt(i1 - 1);
    }
}
