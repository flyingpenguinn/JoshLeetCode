/*
LC#400
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class NthDigit {
    // find len
    // find the number x
    // find the digit we need
    public int findNthDigit(int n) {
        // n>=1
        if(n<1){
            return -1;
        }
        int len =1;
        long base = 1;
        long walked = 0;
        // 9*1*1, 9*2*10, .....
        while(walked+ 9*base*len <n){
            walked += 9*base*len;
            base *=10;
            len++;
        }
        // the number is of length == len
        n -= walked;
        int nums = (n-1)/len;
        // skip how many numbers to get to n
        // 1,2,-> 0   3,4->1
        int numLen = nums*len;
        n -= numLen;
        long number = base + nums;
        String sn = String.valueOf(number);
        return sn.charAt(n-1)-'0';
    }
}