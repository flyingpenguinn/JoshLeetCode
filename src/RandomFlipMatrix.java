import java.util.*;

/*
LC#519

You are given the number of rows n_rows and number of columns n_cols of a 2D binary matrix where all values are initially 0. Write a function flip which chooses a 0 value uniformly at random, changes it to 1, and then returns the position [row.id, col.id] of that value. Also, write a function reset which sets all values back to 0. Try to minimize the number of calls to system's Math.random() and optimize the time and space complexity.

Note:

1 <= n_rows, n_cols <= 10000
0 <= row.id < n_rows and 0 <= col.id < n_cols
flip will not be called when the matrix has no 0 values left.
the total number of calls to flip and reset will not exceed 1000.
Example 1:

Input:
["Solution","flip","flip","flip","flip"]
[[2,3],[],[],[],[]]
Output: [null,[0,1],[1,2],[1,0],[1,1]]
Example 2:

Input:
["Solution","flip","flip","reset","flip"]
[[1,2],[],[],[],[]]
Output: [null,[0,0],[0,1],null,[0,0]]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, n_rows and n_cols. flip and reset have no arguments. Arguments are always wrapped with a list, even if there aren't any.

https://leetcode.com/problems/random-flip-matrix/
 */
public class RandomFlipMatrix {
    static class Solution {
        private int rs;
        private int cs;
        private int cand = 0;
        private Random rand = new Random();
        private Map<Integer,Integer> m = new HashMap<>();

        public Solution(int rs, int cs) {
            this.rs = rs;
            this.cs = cs;
            cand = rs*cs;
        }

        public int[] flip() {

            int ran = rand.nextInt(cand--);

            int real = m.getOrDefault(ran, ran);
            int r = real/cs;
            int c = real%cs;
            m.put(ran, m.getOrDefault(cand, cand));

            return new int[]{r,c};
        }

        public void reset() {
            m.clear();
            cand = rs*cs;
        }
    }
}