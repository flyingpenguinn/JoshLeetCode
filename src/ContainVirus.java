import base.ArrayUtils;


import java.util.*;

/*
LC#749
A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.

The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.

Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.

Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.

Example 1:
Input: grid =
[[0,1,0,0,0,0,0,1],
 [0,1,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,0]]
Output: 10
Explanation:
There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:

[[0,1,0,0,0,0,1,1],
 [0,1,0,0,0,0,1,1],
 [0,0,0,0,0,0,1,1],
 [0,0,0,0,0,0,0,1]]

On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
Example 2:
Input: grid =
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output: 4
Explanation: Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.
Example 3:
Input: grid =
[[1,1,1,0,0,0,0,0,0],
 [1,0,1,0,1,1,1,1,1],
 [1,1,1,0,0,0,0,0,0]]
Output: 13
Explanation: The region on the left only builds two new walls.
Note:
The number of rows and columns of grid will each be in the range [1, 50].
Each grid[i][j] will be either 0 or 1.
Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares in the next round.
 */
public class ContainVirus {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int containVirus(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int r = 0;
        while (true) {
            // mark all virus into components
            boolean[][] v = new boolean[m][n];
            int comp = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!v[i][j] && a[i][j] > 0) {
                        comp++;
                        dfs(i, j, a, v, comp);
                    }
                }
            }
            // no normal ones or no virus
            int[] walls = new int[comp + 1];
            int[] infected = new int[comp + 1];
            // track infected and build walls for each component
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == 0) {
                        // use a set here so that if we are next to two ones we dont count one twice
                        Set<Integer> vs = new HashSet<>();
                        for (int[] d : dirs) {
                            int ni = i + d[0];
                            int nj = j + d[1];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] > 0) {
                                int virus = a[ni][nj];
                                // we will build a wall between this virus and this cell
                                walls[virus]++;
                                vs.add(virus);
                            }
                        }
                        for (int vk : vs) {
                            // these virus can infect this cell
                            infected[vk]++;
                        }
                    }
                }
            }
            // find the max virus, count the wall
            int maxinfected = 0;
            int maxvirus = 0;
            for (int i = 1; i <= comp; i++) {
                if (infected[i] > maxinfected) {
                    maxinfected = infected[i];
                    maxvirus = i;
                }
            }
            if (maxinfected == 0) {
                break;
            }
            r += walls[maxvirus];
            // grow non maxvirus, and muffle max virus
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] > 0 && a[i][j] != maxvirus) {
                        for (int[] d : dirs) {
                            int ni = i + d[0];
                            int nj = j + d[1];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 0) {
                                a[ni][nj] = -a[i][j];  // mark first, dont convert, otherwise next time we will step on it...
                            }
                        }
                    } else if (a[i][j] == maxvirus) {
                        a[i][j] = DEAD;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] < 0 && a[i][j] != DEAD) {
                        a[i][j] = -a[i][j];
                    }
                }
            }
        }
        return r;
    }

    int DEAD = -1000000;

    private void dfs(int i, int j, int[][] a, boolean[][] v, int comp) {
        v[i][j] = true;
        a[i][j] = comp;
        int m = a.length;
        int n = a[0].length;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] > 0 && !v[ni][nj]) {
                dfs(ni, nj, a, v, comp);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ContainVirus().containVirus(ArrayUtils.read("[[1,1,0,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,0,0],[1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1,0,0],[1,1,1,1,1,1,1,1,1,0,0,1,0,0,0,1,1,1,1,1],[1,1,0,1,1,0,1,0,1,1,0,0,0,0,0,1,1,1,0,1],[1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,0,1],[0,1,0,1,0,1,0,1,0,0,0,0,1,1,1,0,1,0,1,0],[1,0,1,1,1,0,0,0,1,1,0,1,1,0,1,1,1,0,1,1],[1,0,0,1,1,1,0,0,1,1,1,1,0,1,1,1,0,1,0,0],[1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1],[1,0,0,1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,1,1],[1,0,1,0,0,1,1,1,0,1,1,1,1,0,0,1,1,1,0,1],[1,0,1,1,1,0,1,1,1,1,0,1,0,0,1,1,0,1,1,1],[1,0,1,0,1,0,0,1,0,1,1,1,0,1,0,0,1,1,0,1],[1,1,0,0,0,1,0,0,1,1,1,0,0,0,0,1,0,1,0,1],[0,1,1,0,0,1,1,0,0,0,1,1,1,1,0,0,0,1,0,0],[1,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,0,0,0,0],[0,1,0,0,0,1,1,0,0,1,1,1,1,1,1,0,1,0,0,1],[1,1,1,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,0,1],[0,0,1,1,1,1,1,1,0,1,0,0,1,0,0,0,0,1,1,1],[0,1,1,1,1,0,1,0,1,1,1,1,0,0,0,1,0,0,0,0]]")));
    }
}
