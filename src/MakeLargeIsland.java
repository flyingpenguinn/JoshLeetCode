import base.ArrayUtils;

import java.util.*;

public class MakeLargeIsland {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int[][] board, int ni, int nj) {
        return ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length;
    }

    DisjointSet<Integer> djs = new DisjointSet<>();

    int getKey(int row, int col) {
        return row * 50 + col;
    }

    int maxisland = 0;

    public int largestIsland(int[][] grid) {
        processOnes(grid);
        maxisland = Math.max(maxisland, djs.maxSetSize);
        processZeros(grid);
        return maxisland;
    }

    private void processZeros(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    int cursize = 1;
                    Set<Integer> found = new HashSet<>();
                    for (int[] d : directions) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        // avoid double counting of overlapping areas
                        if (inRange(grid, ni, nj) && grid[ni][nj] == 1) {
                            int newkey = getKey(ni, nj);
                            int setn = djs.find(newkey);
                            if (!found.contains(setn)) {
                                cursize += djs.findSize(setn);
                                found.add(setn);
                            }
                        }
                    }
                    maxisland = Math.max(maxisland, cursize);
                }
            }
        }
    }


    private void processOnes(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int key = getKey(i, j);
                    int set = djs.find(key);
                    for (int[] d : directions) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (inRange(grid, ni, nj) && grid[ni][nj] == 1) {
                            int newkey = getKey(ni, nj);
                            int setn = djs.find(newkey);
                            djs.union(set, setn);
                        }
                    }
                }
            }
        }
    }

    class DisjointSet<T> {
        // from string to its representative
        Map<T, T> parent = new HashMap<>();
        // each representative's size
        Map<T, Integer> size = new HashMap<>();
        int maxSetSize = 0;
        int numOfSets = 0;

        public T makeSet(T s) {
            parent.put(s, s);
            size.put(s, 1);
            numOfSets++;
            maxSetSize = Math.max(maxSetSize, 1);
            return s;
        }

        // path compression
        public T find(T s) {
            T p = parent.get(s);
            if (p == null) {
                return makeSet(s);
            } else if (p.equals(s)) {
                return p;
            } else {
                T pt = find(p);
                parent.put(s, pt);
                return pt;
            }
        }

        public int findSize(T s) {
            return size.get(find(s));
        }

        // union by size
        public T union(T x, T y) {
            T px = find(x);
            T py = find(y);
            if (px.equals(py)) {
                // no merge! all equal
                return px;
            }
            int sizex = size.get(px);
            int sizey = size.get(py);
            T toReturn = null;
            numOfSets--;
            if (sizex < sizey) {
                parent.put(px, py);
                toReturn = py;
            } else if (sizex > sizey) {
                parent.put(py, px);
                toReturn = px;
            } else {
                parent.put(px, py);
                toReturn = py;
            }
            size.put(toReturn, sizex + sizey);
            maxSetSize = Math.max(maxSetSize, sizex + sizey);
            return toReturn;

        }

        public int numOfSets() {
            return numOfSets;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MakeLargeIslandDfs().largestIsland(ArrayUtils.read("[[1, 1], [1, 0]]")));
    }
}


class MakeLargeIslandDfs {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int[][] board, int ni, int nj) {
        return ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length;
    }

    int[][] dfsSeqMap;
    int seq = 0;
    Map<Integer, Integer> dfssize = new HashMap<>();
    int maxisland = 0;

    public int largestIsland(int[][] grid) {
        dfsSeqMap = new int[grid.length][grid[0].length];
        doDfs(grid);
        processZeros(grid);
        return maxisland;
    }


    private void processZeros(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    int cursize = 1;
                    Set<Integer> found = new HashSet<>();
                    for (int[] d : directions) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        // avoid double counting of overlapping areas
                        if (inRange(grid, ni, nj) && grid[ni][nj] == 2) {
                            int dfsseq = dfsSeqMap[ni][nj];
                            if (!found.contains(dfsseq)) {
                                cursize += dfssize.get(dfsseq);
                                found.add(dfsseq);
                            }
                        }
                    }
                    maxisland = Math.max(maxisland, cursize);
                }
            }
        }
    }

    private void doDfs(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, seq);
                    dfssize.put(seq, size);
                    maxisland = Math.max(maxisland, size);
                    seq++;
                }
            }
        }
    }

    private int dfs(int[][] grid, int i, int j, int dfsseq) {
        dfsSeqMap[i][j] = dfsseq;
        grid[i][j] = 2;
        int count = 1;
        for (int[] d : directions) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (inRange(grid, ni, nj) && grid[ni][nj] == 1) {
                count += dfs(grid, ni, nj, dfsseq);
            }
        }
        return count;
    }
}