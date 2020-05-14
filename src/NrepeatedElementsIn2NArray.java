/*
LC#961
In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.

Return the element repeated N times.



Example 1:

Input: [1,2,3,3]
Output: 3
Example 2:

Input: [2,1,2,5,3,2]
Output: 2
Example 3:

Input: [5,1,5,2,5,3,5,4]
Output: 5


Note:

4 <= A.length <= 10000
0 <= A[i] < 10000
A.length is even
 */
public class NrepeatedElementsIn2NArray {
    // k can't be  3: 9,5,6,9. n/3+(n%3)>0?1:0 will be ==n/2 when n ==4
    // k can't be 2: 1,2,3,2
    // can even randomly pick and verify
    public int repeatedNTimes(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= 3 && i + k < n; k++) {
                if (a[i] == a[k + i]) {
                    return a[i];
                }
            }
        }
        return -1;
    }
}
