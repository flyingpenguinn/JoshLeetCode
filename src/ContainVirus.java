import base.ArrayUtils;

import java.util.*;

public class ContainVirus {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int[][] matrix, int ni, int nj) {
        return ni >= 0 && ni < matrix.length && nj >= 0 && nj < matrix[0].length;
    }

    int day = 2;
    Set<Point> dayseen = new HashSet<>();
    Set<Point> curseen = new HashSet<>();
    int curwalls = 0;
    Set<Point> curthreatened = new HashSet<>();

    class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row &&
                    col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    class DfsResult {
        int threatened = 0;
        int walls = 0;
        Set<Point> virus = new HashSet<>();

        public DfsResult(int threatened, int walls, Set<Point> virus) {
            this.threatened = threatened;
            this.walls = walls;
            this.virus = virus;
        }
    }

    public int containVirus(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        DfsResult most = null;

        int walls = 0;
        while (true) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Point start = new Point(i, j);
                    if (grid[i][j] == 1 && !dayseen.contains(start)) {
                        curseen.clear();
                        curthreatened.clear();
                        curwalls = 0;
                        dfs(grid, start);
                        dayseen.addAll(curseen);
                        if (most == null || most.threatened < curthreatened.size()) {
                            DfsResult dr = new DfsResult(curthreatened.size(), curwalls, new HashSet<>(curseen));
                            most = dr;
                        }
                    }
                }
            }
            if (most == null) {
                return walls;
            }
            if (most.virus.size() == rows * cols) {
                return walls;
            }
            for (Point p : most.virus) {
                grid[p.row][p.col] = day;
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Point p = new Point(i, j);
                    if (grid[i][j] == 1 && dayseen.contains(p)) {
                        grow(grid, p);
                    }
                }
            }
            walls += most.walls;
            day++;
            most = null;
            dayseen.clear();
        }
    }

    private void grow(int[][] grid, Point v) {
        int i = v.row;
        int j = v.col;
        for (int[] d : directions) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (!inRange(grid, ni, nj)) {
                continue;
            }
            if (grid[ni][nj] == 0) {
                grid[ni][nj] = 1;
            }
        }
    }

    private void dfs(int[][] grid, Point s) {
        curseen.add(s);
        for (int[] d : directions) {
            int ni = s.row + d[0];
            int nj = s.col + d[1];
            Point np = new Point(ni, nj);
            if (!inRange(grid, ni, nj)) {
                continue;
            }
            if (curseen.contains(np)) {
                continue;
            }
            if (grid[ni][nj] == 1) {
                dfs(grid, np);
            } else if (grid[ni][nj] == 0) {
                curwalls++;
                curthreatened.add(np);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ContainVirus().containVirus(ArrayUtils.read("[[0,1,0,0,0,0,0,1],[0,1,0,1,0,0,0,1],[0,0,0,0,0,0,0,1]]")));
    }
}
