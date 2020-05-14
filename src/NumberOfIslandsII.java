import java.util.*;

/*
LC#305
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberOfIslandsII {
    // typical union find
    int rs = 0;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[] p;

    public List<Integer> numIslands2(int m, int n, int[][] ps) {
        List<Integer> r = new ArrayList<>();
        p = new int[m * n];
        Arrays.fill(p, -1);
        for (int[] a : ps) {
            int ai = a[0] * n + a[1];
            if (p[ai] != -1) {
                r.add(rs);
                continue;
            }
            p[ai] = ai;
            rs++;
            for (int[] d : dirs) {
                int n0 = a[0] + d[0];
                int n1 = a[1] + d[1];
                if (n0 >= 0 && n0 < m && n1 >= 0 && n1 < n) {
                    int ni = n0 * n + n1;
                    union(ai, ni);
                }
            }
            r.add(rs);
        }
        return r;
    }

    void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi == -1 || pj == -1 || pi == pj) {
            return;
        }
        p[pi] = pj;
        rs--;
    }

    int find(int i) {
        if (p[i] == i || p[i] == -1) {
            return p[i];
        }
        int rt = find(p[i]);
        p[i] = rt;
        return rt;
    }
}
