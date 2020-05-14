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
    public int maximumNumberOfOnes(int w, int h, int m, int max) {
        h--;// normalize to 0...n-1
        w--;
        int cur=0;
        List<Integer> ml= new ArrayList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                int rs= (h-i)/m+1;
                int cs= (w-j)/m+1;
                // each row gives cs, there are rs rows
                int sc= (rs)*(cs);
                ml.add(sc);
            }
        }
        Collections.sort(ml,Collections.reverseOrder());
        int r=0;
        for(int i=0;i<max;i++){
            r += ml.get(i);
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new MatrixMaxNumberOfOnes().maximumNumberOfOnes(3, 3, 2, 1));
    }
}
