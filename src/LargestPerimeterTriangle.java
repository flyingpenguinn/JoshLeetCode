import java.util.Arrays;

/*
LC#976
Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return 0.



Example 1:

Input: [2,1,2]
Output: 5
Example 2:

Input: [1,2,1]
Output: 0
Example 3:

Input: [3,2,3,4]
Output: 10
Example 4:

Input: [3,6,2,3]
Output: 8


Note:

3 <= A.length <= 10000
1 <= A[i] <= 10^6
 */
public class LargestPerimeterTriangle {
    // find the biggest 3 tuples that can form a triangle. if n-1 can't make it, try out n-2
    public int largestPerimeter(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        for (int i = n - 1; i - 2 >= 0; i--) {
            int l1 = a[i];
            int l2 = a[i - 1];
            int l3 = a[i - 2];
            if (l2 + l3 > l1) {
                return l1 + l2 + l3;
            }
        }
        return 0;
    }
}
