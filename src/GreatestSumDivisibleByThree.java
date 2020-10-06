import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
LC#1262
Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.

In one shift operation:

Element at grid[i][j] becomes at grid[i][j + 1].
Element at grid[i][m - 1] becomes at grid[i + 1][0].
Element at grid[n - 1][m - 1] becomes at grid[0][0].
Return the 2D grid after applying shift operation k times.



Example 1:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[9,1,2],[3,4,5],[6,7,8]]
Example 2:


Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
Example 3:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
Output: [[1,2,3],[4,5,6],[7,8,9]]


Constraints:

1 <= grid.length <= 50
1 <= grid[i].length <= 50
-1000 <= grid[i][j] <= 1000
0 <= k <= 100
 */
public class GreatestSumDivisibleByThree {

    // think from the point of minusing from sum, dont try to create from the lists!
    // if mod ==1, -mod 1, or - 2* mod2
    // if mod ==2, -mod2, or - 2*mod1
    public int maxSumDivThree(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        if (sum % 3 == 0) {
            return sum;
        }
        int max = 0;
        List<Integer> minl1 = null;
        List<Integer> minl2 = null;
        if (sum % 3 == 1) {
            minl1 = find(a, 1, 1);
            minl2 = find(a, 2, 2);
        } else {
            minl2 = find(a, 2, 1);
            minl1 = find(a, 1, 2);
        }
        if (minl1 != null) {
            max = Math.max(max, num(sum, minl1));
        }
        if (minl2 != null) {
            max = Math.max(max, num(sum, minl2));
        }
        return max;
    }

    private List<Integer> find(int[] a, int t, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 3 == t) {
                pq.offer(a[i]);
                if (pq.size() > n) {
                    pq.poll();
                }
            }
        }
        if (pq.size() < n) {
            return null;
        } else {
            List<Integer> list = new ArrayList<>(pq);
            return list;
        }
    }

    // n- the list of numbers
    private int num(int n, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            n -= list.get(i);
        }
        return n;
    }
}
