import base.ArrayUtils;

import java.util.*;

/*
LC#710
Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.

Optimize it such that it minimizes the call to system’s Math.random().

Note:

1 <= N <= 1000000000
0 <= B.length < min(100000, N)
[0, N) does NOT include N. See interval notation.
Example 1:

Input:
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
Output: [null,0,0,0]
Example 2:

Input:
["Solution","pick","pick","pick"]
[[2,[]],[],[],[]]
Output: [null,1,1,1]
Example 3:

Input:
["Solution","pick","pick","pick"]
[[3,[1]],[],[],[]]
Output: [null,0,0,2]
Example 4:

Input:
["Solution","pick","pick","pick"]
[[4,[2]],[],[],[]]
Output: [null,1,3,1]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class RandomPickWithBlackList {
    // we know where whitelist should end. any number after it can substitute bad numbers before it
    static class Solution {

        Map<Integer, Integer> m = new HashMap<>();
        int wn;

        public Solution(int n, int[] blacklist) {
            Set<Integer> set = new HashSet<>();
            for (int b : blacklist) {
                set.add(b);
            }
            this.wn = n - blacklist.length;
            int j = n - 1;
            for (int b : blacklist) {
                // shouldnt map ones already bad
                if (b >= wn) {
                    continue;
                }
                // skip bad ones after wn they are naturally bad
                while (set.contains(j)) {
                    j--;
                }
                m.put(b, j--);
            }
        }

        Random rand = new Random();

        public int pick() {
            int r = rand.nextInt(wn);
            return m.getOrDefault(r, r);
        }
    }

    public static void main(String[] args) {
        Solution rand = new Solution(4, ArrayUtils.read1d("2,1"));
        for (int i = 0; i < 100; i++) {
            System.out.println(rand.pick());
        }
    }
}

