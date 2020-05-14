/*
LC#370
Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Explanation:

Initial state:
[0,0,0,0,0]

After applying operation [1,3,2]:
[0,2,2,2,0]

After applying operation [2,4,3]:
[0,2,5,5,3]

After applying operation [0,2,-2]:
[-2,0,3,5,3]
 */
public class RangeAddition {
    // similar to meeting room 2, only work on the two ends
    public int[] getModifiedArray(int n, int[][] us) {
        int[] m = new int[n];
        for (int[] u : us) {
            m[u[0]] += u[2];
            if (u[1] + 1 < n) {
                m[u[1] + 1] -= u[2];
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += m[i];
            m[i] = sum;
        }
        return m;
    }
}

