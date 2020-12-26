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
        private int wlen = 0;
        private Map<Integer, Integer> m = new HashMap<>();

        public Solution(int n, int[] b) {
            Set<Integer> bset = new HashSet<>();
            for (int bi : b) {
                bset.add(bi);
            }
            int swapt = n - 1;
            this.wlen = n - b.length;
            // can't go from 0 to wlen otherwise we got tle
            for (int bi : bset) {
                if (bi >= wlen) {
                    continue;
                }
                while (bset.contains(swapt)) {
                    swapt--;
                }
                m.put(bi, swapt--);
                // must --swap to give others a chance
            }

        }

        private Random rand = new Random();

        public int pick() {
            int ran = rand.nextInt(wlen);
            return m.getOrDefault(ran, ran);
        }

        public static void main(String[] args) {
            Solution rand = new Solution(4, ArrayUtils.read1d("2,1"));
            for (int i = 0; i < 100; i++) {
                System.out.println(rand.pick());
            }
        }
    }
}

