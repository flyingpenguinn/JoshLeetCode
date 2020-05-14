/*
LC#414
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdLargestNumber {
    // could have used a fixed size heap for kth largest number
    public int thirdMax(int[] nums) {
        long n1 = Integer.MIN_VALUE - 1L;
        long n2 = Integer.MIN_VALUE - 1L;
        long n3 = Integer.MIN_VALUE - 1L;
        for (int a : nums) {
            if (a > n1) {
                n3 = n2;
                n2 = n1;
                n1 = a;
            } else if (a > n2 && a < n1) {
                // must verify this for dupes
                n3 = n2;
                n2 = a;
            } else if (a > n3 && a < n2) {
                n3 = a;
            }
        }
        if (n3 < Integer.MIN_VALUE) {
            return (int) n1;
        } else {
            return (int) n3;
        }
    }
}
