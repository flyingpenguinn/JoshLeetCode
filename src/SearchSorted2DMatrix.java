import java.util.Arrays;

/*
LC#74
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */
public class SearchSorted2DMatrix {

    // logm + logn, better than O(m+n) solutions...
    public boolean searchMatrix(int[][] a, int t) {
        int m = a.length;
        if(m==0){
            return false;
        }
        int n = a[0].length;

        int l = 0;
        int u = m*n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            int mi = mid/n;
            int mj = mid%n;
            if(a[mi][mj]==t){
                return true;
            }else if(a[mi][mj]<t){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return false;
    }
}
