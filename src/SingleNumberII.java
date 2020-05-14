/*
LC#137
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 */
public class SingleNumberII {
    // for digits at certain position if ans has 1 count= 3x+1 otherwise 3x
    // works for neg too in this way
    public int singleNumber(int[] a) {
        int r=0;
        for(int j=0;j<32;j++){
            int bc=0;
            for(int i=0;i<a.length;i++){
                int bit= (a[i]>>j) & 1;
                if(bit==1){
                    bc++;
                }
            }
            if(bc%3==1){
                r += 1<<j;
            }
        }
        return r;
    }
}
