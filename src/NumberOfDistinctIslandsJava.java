import java.util.ArrayList;
import java.util.HashSet;

public class NumberOfDistinctIslandsJava {


    boolean[][] visited = null;

    HashSet<ArrayList<Integer>> islands = new HashSet<>();

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int i, int j, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private ArrayList<Integer> dfs(int[][] grid, int i, int j, int incoming) {
        visited[i][j] = true;
        ArrayList components = new ArrayList<>();
        components.add(incoming);
        for (int d = 0; d < directions.length; d++) {
            int ni = i + directions[d][0];
            int nj = j + directions[d][1];
            if (inRange(ni, nj, grid) && grid[ni][nj] == 1 && !visited[ni][nj]) {
                ArrayList<Integer> comp = dfs(grid, ni, nj, d);
                components.addAll(comp);

            }
        }
        components.add(-2);  // record when we end!  otherwise some different paths may look like the same
        return components;
    }


    public int numDistinctIslands(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        islands.clear();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ArrayList<Integer> components = dfs(grid, i, j, -1);
                    if (!islands.contains(components)) {
                        islands.add(components);
                    }
                }
            }
        }
        return islands.size();
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 1, 0}};
        NumberOfDistinctIslandsJava ndij = new NumberOfDistinctIslandsJava();
        System.out.println(ndij.numDistinctIslands(grid));
    }
}
