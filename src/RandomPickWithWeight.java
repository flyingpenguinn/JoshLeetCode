import base.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

/*
LC#528
Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class RandomPickWithWeight {
    // each one bears their own weight
    class Solution {

        private int[] sum;
        private int all = 0;

        public Solution(int[] w) {
            // w is not null, otherwise throw....
            int n = w.length;
            sum = new int[n];
            sum[0] = 0;
            all += w[0];
            for (int i = 1; i < n; i++) {
                // 1,3,5 => 0,1,4 pick from 9
                sum[i] = sum[i - 1] + w[i - 1];
                all += w[i];
            }
        }

        private Random rand = new Random();

        public int pickIndex() {
            int picked = rand.nextInt(all);
            int pos = binarySearch(sum, picked);
            return pos;
        }

        // either return the found index, or return the biggest that is smaller, return the index
        private int binarySearch(int[] sum, int t) {
            int l = 0;
            int u = sum.length - 1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (sum[mid] == t) {
                    return mid;
                } else if (sum[mid] < t) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            return u;
        }
    }

    public static void main(String[] args) {
        Solution sol = new RandomPickWithWeight().new Solution(ArrayUtils.read1d("1,3,5"));
        for (int i = 0; i < 100; i++) {
            System.out.println(sol.pickIndex());
        }
    }
}
