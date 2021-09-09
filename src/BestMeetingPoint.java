import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
LC#296
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input:

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance
             of 2+2+2=6 is minimal. So return 6.
 */
public class BestMeetingPoint {
    // median of row and cols. An extension of the 1D case
    public int minTotalDistance(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(a[i][j]==1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(rows);
        Collections.sort(cols);
        int pr = rows.get(rows.size()/2);
        int pc = cols.get(cols.size()/2);
        //    System.out.println(pr+" "+pc);
        return dists(pr, pc, a);
    }

    private int dists(int pr, int pc, int[][] a){
        int res = 0;
        for(int i=0; i<a.length; ++i){
            for(int j=0; j<a[0].length; ++j){
                if(a[i][j]==1){
                    res += Math.abs(i-pr)+Math.abs(j-pc);
                }
            }
        }
        return res;
    }
}
