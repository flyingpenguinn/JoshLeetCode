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
        int[] w;

        public Solution(int[] w) {
            this.w = w;
            for(int i=1; i<w.length;i++){
                w[i]  = w[i-1]+w[i];
            }
        }

        Random rand = new Random();
        public int pickIndex() {
            int sum = w[w.length-1];
            int num = rand.nextInt(sum)+1;
            int slot = Arrays.binarySearch(w,num);
            if(slot>=0){
                return slot;
            }else{
                int insertion = -(slot+1);
                return insertion;
            }
        }
    }
}
