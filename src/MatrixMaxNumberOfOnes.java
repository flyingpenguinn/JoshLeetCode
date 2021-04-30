import java.util.*;

/*
LC#1183
Consider a matrix M with dimensions width * height, such that every cell has value 0 or 1, and any square sub-matrix of M of size sideLength * sideLength has at most maxOnes ones.

Return the maximum possible number of ones that the matrix M can have.



Example 1:

Input: width = 3, height = 3, sideLength = 2, maxOnes = 1
Output: 4
Explanation:
In a 3*3 matrix, no 2*2 sub-matrix can have more than 1 one.
The best solution that has 4 ones is:
[1,0,1]
[0,0,0]
[1,0,1]
Example 2:

Input: width = 3, height = 3, sideLength = 2, maxOnes = 2
Output: 6
Explanation:
[1,0,1]
[1,0,1]
[1,0,1]


Constraints:

1 <= width, height <= 100
1 <= sideLength <= width, height
0 <= maxOnes <= sideLength * sideLength
 */
public class MatrixMaxNumberOfOnes {
    // analyze the positions in the first m*m matrix.
    // every 1 here can have other 1s "independent from" its position. we want max numbers of such 1s from the first square
    // use the first sl*sl as a template to "influence" other such squares
    public int maximumNumberOfOnes(int w, int h, int sl, int maxones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < sl; i++) {
            for (int j = 0; j < sl; j++) {
                int rows = (int) Math.ceil((h - i) * 1.0 / sl);
                // rows of allowed 1s
                int cols = (int) Math.ceil((w - j) * 1.0 / sl);
                // in each row there are cols of allowed 1s
                pq.offer(rows * cols);
            }
        }
        int res = 0;
        while (!pq.isEmpty() && maxones > 0) {
            res += pq.poll();
            maxones--;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MatrixMaxNumberOfOnes().maximumNumberOfOnes(3, 3, 2, 1));
    }
}
