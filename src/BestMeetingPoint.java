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
    public int minTotalDistance(int[][] g) {
        if (g == null || g.length == 0 || g[0].length == 0) {
            return 0;
        }
        int m = g.length;
        int n = g[0].length;
        int[] cr = new int[m];
        int[] cc = new int[n];
        int dist = 0;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    dist += (i + j);
                    cr[i]++;
                    cc[j]++;
                    sum++;
                }
            }
        }
        int i = 0;
        int j = 0;
        int minDist = dist;
        int curSumc = cc[0];
        // actually finding the median
        int curSumr = cr[0];
        while (i < m && j < n) {
            minDist = Math.min(dist, minDist);
            int goRight = dist + curSumc - (sum - curSumc);
            int goDown = dist + curSumr - (sum - curSumr);
            if (goRight <= goDown && goRight < dist) {
                j++;
                curSumc += j == n ? 0 : cc[j];
                dist = goRight;
            } else if (goDown <= goRight && goDown < dist) {
                i++;
                curSumr += i == m ? 0 : cr[i];
                dist = goDown;
            } else {
                break;
            }
        }
        return minDist;
    }
}
