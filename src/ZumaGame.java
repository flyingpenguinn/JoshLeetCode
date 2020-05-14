import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#488
Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.



Example 1:

Input: board = "WRRBBW", hand = "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
Example 2:

Input: board = "WWRRBBWW", hand = "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
Example 3:

Input: board = "G", hand = "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty
Example 4:

Input: board = "RBYYBBRRB", hand = "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty


Constraints:

You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
The number of balls on the table won't exceed 16, and the string represents these balls is called "board" in the input.
The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 */
public class ZumaGame {
    // dfs. we only increase, and collpase in one shot
    // we can't collapse eagerly because we may have the situation like RRRBBBRR.
    // if we collapse the first 3rs we lose the chance of merging with the last two
    public int findMinStep(String b, String h) {
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < h.length(); i++) {
            char key = h.charAt(i);
            m.put(key, m.getOrDefault(key, 0) + 1);
        }
        int r = dof(b, m);
        return r >= Max ? -1 : r;
    }

    int Max = 1000000;

    private int dof(String b, Map<Character, Integer> m) {
        String nb = collapse(b);
        if (nb.isEmpty()) {
            return 0;
        }
        if (m.isEmpty()) {
            return Max;
        }
        int min = Max;
        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            if (i > 0 && c == b.charAt(i - 1)) {
                continue;
            }
            if (m.containsKey(c)) {
                update(m, c, -1);
                StringBuilder sb = new StringBuilder(b);
                sb.insert(i, c);
                String next = sb.toString();
                int cur = 1 + dof(next, m);
                update(m, c, 1);
                min = Math.min(min, cur);
            }
        }
        return min;
    }

    private Map<Character, Integer> update(Map<Character, Integer> m, char c, int delta) {
        int nv = m.getOrDefault(c, 0) + delta;
        if (nv <= 0) {
            m.remove(c);
        } else {
            m.put(c, nv);
        }
        return m;
    }

    // use recursion
    private String collapse(String b) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < b.length()) {
            int j = i;
            while (j < b.length() && b.charAt(j) == b.charAt(i)) {
                j++;
            }
            if (j - i < 3) {
                while (i < j) {
                    sb.append(b.charAt(i++));
                }
            } else {
                i = j;
            }
        }
        if (sb.length() < b.length()) {
            return collapse(sb.toString());
        } else {
            return b;
        }
    }


    public static void main(String[] args) {
        System.out.println(new ZumaGame().findMinStep("WRRBBW", "RB"));
        System.out.println(new ZumaGame().findMinStep("RWYWRRWRR", "YRY"));

        System.out.println(new ZumaGame().findMinStep("WWRRBBWW", "WRBRW"));
        System.out.println(new ZumaGame().findMinStep("G", "GGGG"));
        System.out.println(new ZumaGame().findMinStep("RBYYBBRRB", "YRBGB"));

    }
}
