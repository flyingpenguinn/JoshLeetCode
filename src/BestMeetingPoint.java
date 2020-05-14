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
        int m = g.length;
        int n = g[0].length;
        int[] rc = new int[m];
        int[] cc = new int[n];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    rc[i]++;
                    cc[j]++;
                    sum++;
                }
            }
        }
        // can use a raw dist[m][n] code will be easier. here it's 2 because we use at most 2 rows
        int[][] dist = new int[2][n];
        // calc [0][0] result first
        for (int i = 1; i < m; i++) {
            dist[0][0] += rc[i] * i;
        }
        for (int j = 1; j < n; j++) {
            dist[0][0] += cc[j] * j;
        }
        int min = dist[0][0];
        int pr = 0;// row counts before row i
        for (int i = 0; i < m; i++) {
            int pc = 0; // column counts before column j
            int mi = i % 2;
            int pmi = (i + 1) % 2;
            for (int j = 0; j < n; j++) {
                if (j - 1 >= 0) {
                    int apc = sum - pc;
                    dist[mi][j] = dist[mi][j - 1] + pc - apc;
                } else if (i - 1 >= 0) {
                    int apr = sum - pr;
                    // if it was 1*2+3*4+5*6 before, now it's 2*2+4*4+6*6. so all rows before i got +1. hence +pr. same reason we need to -apr
                    dist[mi][j] = dist[pmi][j] + pr - apr;
                }
                min = Math.min(min, dist[mi][j]);
                pc += cc[j];
            }
            pr += rc[i];
        }
        return min;
    }
}
