import java.util.*;

/*
LC#756
We are stacking blocks to form a pyramid. Each block has a color which is a one letter string.

We are allowed to place any color block C on top of two adjacent blocks of colors A and B, if and only if ABC is an allowed triple.

We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:

Input: bottom = "BCD", allowed = ["BCG", "CDE", "GEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  G   E
 / \ / \
B   C   D

We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.


Example 2:

Input: bottom = "AABA", allowed = ["AAA", "AAB", "ABA", "ABB", "BAC"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.


Note:

bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

 */
public class PyramidTransitionMatrix {

    // try out each possibility. could have cached cur and next using long by clever hashing
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();
        for (String a : allowed) {
            String key = a.substring(0, 2);
            Set<Character> set = map.getOrDefault(key, new HashSet<>());
            set.add(a.charAt(2));
            map.put(key, set);
        }
        return dop(bottom.length() - 1, "", bottom, map);
    }

    boolean dop(int i, String cur, String next, Map<String, Set<Character>> map) {
        //   System.out.println(i+" "+cur);
        if (i == 0) {
            return true;
        }
        int cn = cur.length();
        if (cn == i) {
            return dop(i - 1, "", cur, map);
        }
        String key = "" + next.charAt(cn) + next.charAt(cn + 1);
        Set<Character> vals = map.getOrDefault(key, new HashSet<>());
        for (Character v : vals) {
            String ns = cur + v;
            boolean gd = dop(i, ns, next, map);
            if (gd) {
                return true;
            }
        }
        return false;
    }
}
