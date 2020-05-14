import java.util.ArrayList;
import java.util.List;

/*
LC#1253
Given the following details of a matrix with n columns and 2 rows :

The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
The sum of elements of the 0-th(upper) row is given as upper.
The sum of elements of the 1-st(lower) row is given as lower.
The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
Your task is to reconstruct the matrix with upper, lower and colsum.

Return it as a 2-D integer array.

If there are more than one valid solution, any of them will be accepted.

If no valid solution exists, return an empty 2-D array.



Example 1:

Input: upper = 2, lower = 1, colsum = [1,1,1]
Output: [[1,1,0],[0,0,1]]
Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
Example 2:

Input: upper = 2, lower = 3, colsum = [2,2,1,1]
Output: []
Example 3:

Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]


Constraints:

1 <= colsum.length <= 10^5
0 <= upper, lower <= colsum.length
0 <= colsum[i] <= 2
 */
public class ReconstructTwoRowBinaryMatrix {

    // if ==1, use the one with more ones. dont forget to check we used all
    public List<List<Integer>> reconstructMatrix(int u, int l, int[] cs) {
        List<Integer> ur = new ArrayList<>();
        List<Integer> lr = new ArrayList<>();
        List<List<Integer>> r = new ArrayList<>();

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 2) {
                if (u == 0 || l == 0) {
                    return r;
                }
                ur.add(1);
                lr.add(1);
                u--;
                l--;
            } else if (cs[i] == 0) {
                ur.add(0);
                lr.add(0);
            } else {
                if (u >= l && u > 0) {
                    ur.add(1);
                    lr.add(0);
                    u--;
                } else if (l > u && l > 0) {
                    ur.add(0);
                    lr.add(1);
                    l--;
                } else {
                    return r;
                }
            }
        }
        if (u > 0 || l > 0) {
            return r;
        }
        // must use up all
        r.add(ur);
        r.add(lr);
        return r;
    }
}
