import java.util.*;

public class Construct2DgridMatchGraphLayout {
    // TODO do it myself
    public int[][] constructGridLayout(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        Map<Integer, Integer> degree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            degree.put(i, 0);
        }

        for (int[] edge : edges) {
            degree.put(edge[0], degree.get(edge[0]) + 1);
            degree.put(edge[1], degree.get(edge[1]) + 1);
        }

        int startNode = -1;
        int minDegree = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : degree.entrySet()) {
            if (entry.getValue() < minDegree) {
                minDegree = entry.getValue();
                startNode = entry.getKey();
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, int[]> visited = new HashMap<>();
        queue.add(startNode);
        visited.put(startNode, new int[]{0, 0});
        int rows = 1, cols = 1;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (currentNode == startNode) {
                int[][] directions = {{0, 1}, {1, 0}};
                int index = 0;
                for (int neighbor : graph.get(currentNode)) {
                    queue.add(neighbor);
                    visited.put(neighbor, new int[]{directions[index][0], directions[index][1]});
                    rows = Math.max(rows, directions[index][0] + 1);
                    cols = Math.max(cols, directions[index][1] + 1);
                    index++;
                }
            } else {
                for (int neighbor : graph.get(currentNode)) {
                    if (visited.containsKey(neighbor)) {
                        continue;
                    }
                    queue.add(neighbor);
                    int maxRow = -1, maxCol = -1;
                    int connectedCount = 0;
                    for (int adjacent : graph.get(neighbor)) {
                        if (visited.containsKey(adjacent)) {
                            int[] pos = visited.get(adjacent);
                            maxRow = Math.max(maxRow, pos[0]);
                            maxCol = Math.max(maxCol, pos[1]);
                            connectedCount++;
                        }
                    }
                    if (connectedCount == 1) {
                        if (maxRow == 0) {
                            visited.put(neighbor, new int[]{maxRow, maxCol + 1});
                            rows = Math.max(rows, maxRow + 1);
                            cols = Math.max(cols, maxCol + 2);
                        } else {
                            visited.put(neighbor, new int[]{maxRow + 1, maxCol});
                            rows = Math.max(rows, maxRow + 2);
                            cols = Math.max(cols, maxCol + 1);
                        }
                    } else {
                        visited.put(neighbor, new int[]{maxRow, maxCol});
                        rows = Math.max(rows, maxRow + 1);
                        cols = Math.max(cols, maxCol + 1);
                    }
                }
            }
        }

        int[][] result = new int[rows][cols];
        for (Map.Entry<Integer, int[]> entry : visited.entrySet()) {
            int[] pos = entry.getValue();
            result[pos[0]][pos[1]] = entry.getKey();
        }

        return result;
    }

    private Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;

    }
}
