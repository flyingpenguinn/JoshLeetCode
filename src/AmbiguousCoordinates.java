import java.util.ArrayList;
import java.util.List;

/*LC#816
We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string S.  Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

Example 1:
Input: "(123)"
Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
Example 2:
Input: "(00011)"
Output:  ["(0.001, 1)", "(0, 0.011)"]
Explanation:
0.0, 00, 0001 or 00.01 are not allowed.
Example 3:
Input: "(0123)"
Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
Example 4:
Input: "(100)"
Output: [(10, 0)]
Explanation:
1.0 is not allowed.


Note:

4 <= S.length <= 12.
S[0] = "(", S[S.length - 1] = ")", and the other elements in S are digits.

*/
public class AmbiguousCoordinates {
    // On3, just two nums so cut and loop
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> r = new ArrayList<>();
        for (int i = 1; i < n - 2; i++) {
            List<String> n1 = don(s, 1, i);
            List<String> n2 = don(s, i + 1, n - 2);
            for (String s1 : n1) {
                for (String s2 : n2) {
                    r.add("(" + s1 + ", " + s2 + ")");
                }
            }
        }
        return r;
    }

    List<String> don(String s, int l, int u) {
        List<String> r = new ArrayList<>();
        if (s.charAt(l) == '0') {
            if (l == u) {
                r.add("0");
            } else if (s.charAt(u) != '0') {
                // no trailing zero
                r.add("0." + s.substring(l + 1, u + 1));
            }

        } else {
            if (s.charAt(u) != '0') {
                // no trailing zero after .
                for (int i = l; i < u; i++) {
                    r.add(s.substring(l, i + 1) + "." + s.substring(i + 1, u + 1));
                }
            }
            // but ints can end with 0
            r.add(s.substring(l, u + 1));
        }
        return r;
    }
}
