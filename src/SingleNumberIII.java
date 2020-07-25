import base.ArrayUtils;

import java.util.Arrays;

/*
LC#260
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class SingleNumberIII {
    // first find the different digit for the two number then split the numbers to two groups. for each group that number appears only once
    public int[] singleNumber(int[] a) {
        // check null error out if needed
        int n = a.length;
        int[] bits = new int[32];
        for(int i=0; i<n;i++){
            for(int j=0; j<32; j++){
                bits[j] ^= ((a[i]>>j) & 1);
            }
        }
        int diff = -1;
        for(int j=0; j<32; j++){
            if(bits[j]!=0){
                diff = j;
                break;
            }
        }
        // diff is the bit that is differing between the two numbers
        int num1 = 0;
        int num2 = 0;
        for(int i=0; i<n;i++){
            if( ((a[i]>>diff) & 1)==1){
                num1 ^= a[i];
            }else{
                num2 ^= a[i];
            }
        }
        return new int[]{num1, num2};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SingleNumberIII().singleNumber(ArrayUtils.read1d("1,1,2,2,3,5"))));
    }
}
