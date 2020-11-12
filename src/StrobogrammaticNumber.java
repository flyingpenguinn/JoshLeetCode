import java.util.HashMap;
import java.util.Map;

/*
LC#246
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
 */
public class StrobogrammaticNumber {
    // just a fancier palindrome with a special mapping
    private Map<Integer, Integer> m = new HashMap<>();

    public boolean isStrobogrammatic(String s) {
        m.put(0, 0);
        m.put(1, 1);
        m.put(6, 9);
        m.put(8, 8);
        m.put(9, 6);
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            Integer mapped = m.get(s.charAt(i) - '0');
            if (mapped != null && mapped == (s.charAt(j) - '0')) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
