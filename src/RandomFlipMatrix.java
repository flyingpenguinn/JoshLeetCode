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
    // unlike pick with black list, here black list is a moving target...
    static class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        int rows;
        int cols;
        int n;
        int wn;
        int bp;
        Random ran = new Random();

        public Solution(int n_rows, int n_cols) {
            rows = n_rows;
            cols = n_cols;
            n = rows * cols;
            wn = n;
            bp = n - 1;
        }

        public int[] flip() {
            int num = ran.nextInt(wn--);
            int x = map.getOrDefault(num, num);
            int row = x / cols;
            int col = x % cols;
            // bp is either mapped, or not mapped...if mapped we set it to the mapped value...
            map.put(num, map.getOrDefault(bp, bp));
            bp--;
            return new int[]{row, col};
        }

        public void reset() {
            map.clear();
            wn = n;
            bp = n - 1;
        }
    }

    public static void main(String[] args) {
        Solution matrix = new Solution(2, 2);
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(matrix.flip()));
        }
    }
}

class FlipMatrixSlower {
    // use a while loop to find the mapping. in previous solution we compressed this path
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();

        int rsize = 0;
        int csize = 0;

        public Solution(int rows, int cols) {
            rsize = rows;
            csize = cols;
            last = rsize * csize - 1;
        }

        int last = 0;
        Random ran = new Random();

        public int[] flip() {
            int rt = ran.nextInt(last + 1);
            while (true) {
                Integer mapped = map.getOrDefault(rt, rt);
                if (mapped == rt) {
                    break;
                } else {
                    rt = mapped;
                }
            }
            map.put(rt, last);
            last--;
            return new int[]{rt / csize, rt % csize};
        }


        public void reset() {
            map.clear();
            last = rsize * csize - 1;
        }
    }
}