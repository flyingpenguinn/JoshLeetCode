import base.ArrayUtils;

import java.util.*;

/*
LC#499
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"

Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2:

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)

Output: "impossible"

Explanation: The ball cannot reach the hole.



Note:

There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
 */
public class TheMazeIII {
    // treat stuck position and the hole as nodes to run  weighted shortest path
    // double keyed dijkstra algo toake both dist and path string into account.
    // note just make it d->l->r->u is not enough: strings can be shorter. so must take the whole string into account
    class Value implements Comparable<Value> {
        int cd;
        String path;

        public Value(int cd, String path) {
            this.cd = cd;
            this.path = path;
        }

        @Override
        public int compareTo(Value o) {
            if (cd != o.cd) {
                return Integer.compare(cd, o.cd);
            } else {
                return path.compareTo(o.path);
            }
        }
    }

    class Qitem {
        int i;
        int j;
        Value cv;

        public Qitem(int i, int j, Value cv) {
            this.i = i;
            this.j = j;
            this.cv = cv;
        }
    }

    int Max = 10000000;
    int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    String[] dirstr = {"d", "l", "r", "u"};
    Value min = new Value(Max, null);


    public String findShortestWay(int[][] a, int[] b, int[] h) {
        PriorityQueue<Qitem> q = new PriorityQueue<>(new Comparator<Qitem>() {
            @Override
            public int compare(Qitem o1, Qitem o2) {
                return o1.cv.compareTo(o2.cv);
            }
        });
        int m = a.length;
        int n = a[0].length;
        boolean[][] done = new boolean[m][n];
        // r,c,dist,incoming dir
        q.offer(new Qitem(b[0], b[1], new Value(0, "")));
        Value[][] dist = new Value[m][n];
        while (!q.isEmpty()) {
            Qitem top = q.poll();
            int i = top.i;
            int j = top.j;
            if (i == h[0] && j == h[1]) {
                return top.cv.path;
            }
            done[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int[] d = dirs[k];
                int ni = i;
                int nj = j;
                int cstep = 0;
                while (ni + d[0] >= 0 && ni + d[0] < m && nj + d[1] >= 0 && nj + d[1] < n && a[ni + d[0]][nj + d[1]] == 0) {
                    if (ni == h[0] && nj == h[1]) {
                        break;
                    }
                    ni += d[0];
                    nj += d[1];
                    cstep++;
                }
                Value nv = cstep == 0 ? top.cv : new Value(top.cv.cd + cstep, top.cv.path + dirstr[k]);
                // stuck at ni nj or it's the hole
                if (dist[ni][nj] == null || dist[ni][nj].compareTo(nv) > 0) {
                    dist[ni][nj] = nv;
                    q.offer(new Qitem(ni, nj, nv));
                }
            }
        }
        return "impossible";
    }

    public static void main(String[] args) {
        System.out.println(new TheMazeIII().findShortestWay(ArrayUtils.read("[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]"),
                ArrayUtils.read1d("[4,3]"), ArrayUtils.read1d("[0,1]")));
    }
}
