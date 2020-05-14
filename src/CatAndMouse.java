import base.ArrayUtils;

import java.util.*;

//TODO Write it myself
public class CatAndMouse {
    Map<int[][][], Map<int[], Integer>> dp = new HashMap<>();

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] visited = new int[n][n][3];


        visited[1][2][1] = 1;
        int w = winner(graph, 1, 2, 1, visited);
        return w;
    }

    private int winner(int[][] graph, int mp, int cp, int mover, int[][][] visited) {
        if (mp == 0) {
            return 1;
        }
        if (mp == cp) {
            return 2;
        }
        Map<int[], Integer> cm = dp.getOrDefault(visited, new HashMap<>());
        int[] cur = new int[3];
        cur[0] = mp;
        cur[1] = cp;
        cur[2] = mover;
        Integer cached = cm.get(cur);
        if (cached != null) {
            return cached;
        }
        if (mover == 1) {
            int flipped = flip(mover);
            boolean candraw = false;
            for (int nmouse : graph[mp]) {
                if (visited[nmouse][cp][flipped] != 1) {
                    visited[nmouse][cp][flipped] = 1;
                    int laterwinner = winner(graph, nmouse, cp, flipped, visited);
                    visited[nmouse][cp][flipped] = 0;
                    if (laterwinner == 2) {
                        continue;
                    } else if (laterwinner == 1) {
                        cm.put(cur, 1);
                        dp.put(visited, cm);
                        return 1;
                    } else {
                        candraw = true;
                    }
                } else {
                    candraw = true;
                }
            }
            if (candraw) {
                cm.put(cur, 0);
                dp.put(visited, cm);
                return 0;
            } else {
                cm.put(cur, 2);
                dp.put(visited, cm);
                return 2;
            }
        } else {
            int flipped = flip(mover);
            boolean candraw = false;
            for (int newcat : graph[cp]) {
                if (newcat == 0) {
                    continue;
                }
                if (visited[mp][newcat][flipped] != 1) {
                    visited[mp][newcat][flipped] = 1;
                    int laterwinner = winner(graph, mp, newcat, flipped, visited);
                    visited[mp][newcat][flipped] = 0;
                    if (laterwinner == 1) {
                        continue;
                    } else if (laterwinner == 2) {
                        cm.put(cur, 2);
                        dp.put(visited, cm);
                        return 2;
                    } else {
                        candraw = true;
                    }
                } else {
                    candraw = true;
                }
            }
            if (candraw) {
                cm.put(cur, 0);
                dp.put(visited, cm);
                return 0;
            } else {
                cm.put(cur, 1);
                dp.put(visited, cm);
                return 1;
            }
        }
    }

    private int flip(int mover) {
        return mover == 1 ? 2 : 1;
    }

    public static void main(String[] args) {
        int[][] graph = ArrayUtils.readUnEven("[[6],[4,11],[9,12],[5],[1,5,11],[3,4,6],[0,5,10],[8,9,10],[7],[2,7,12],[6,7],[1,4],[2,9]]");
        //    int[][] graph = ArrayUtils.readUnEven("[[1,3],[0],[3],[0,2]]");
        System.out.println(new CatAndMouse().catMouseGame(graph));
    }
}
