import static java.lang.Math.max;
import static java.lang.Math.min;

/*
LC#1131
Given two arrays of integers with equal lengths, return the maximum value of:

|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|

where the maximum is taken over all 0 <= i, j < arr1.length.



Example 1:

Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
Output: 13
Example 2:

Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
Output: 20


Constraints:

2 <= arr1.length == arr2.length <= 40000
-10^6 <= arr1[i], arr2[i] <= 10^6
 */
public class MaxAbsoluteExpression {
    // turn abs into max
    // we can assume i>j because otherwise it's asymmetric
    // |a-b| + |c-d| = max (a-b +c-d, b-a+c-d, a-b+d-c, b-a+d-c)
    // so we got 4 cases to go through
    int Min = -1000000000;

    public int maxAbsValExpr(int[] a, int[] b) {

        int max1 = Min;
        int max2 = Min;
        int max3 = Min;
        int max4 = Min;
        int r = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int v1 = -(a[i] + b[i] + i);
            int v2 = a[i] - b[i] - i;
            int v3 = -(a[i] - b[i] + i);
            int v4 = a[i] + b[i] - i;
            int m1 = -v1 + max1;
            int m2 = -v2 + max2;
            int m3 = -v3 + max3;
            int m4 = -v4 + max4;
            int cmax = Math.max(m1, Math.max(m2, Math.max(m3, m4)));
            r = Math.max(cmax, r);
            max1 = Math.max(max1, v1);
            max2 = Math.max(max2, v2);
            max3 = Math.max(max3, v3);
            max4 = Math.max(max4, v4);
        }
        return r;
    }
}
