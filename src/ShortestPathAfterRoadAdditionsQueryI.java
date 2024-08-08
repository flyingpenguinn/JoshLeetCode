import java.util.*;

public class ShortestPathAfterRoadAdditionsQueryI {
    public int[] shortestDistanceAfterQueries(int n, int[][] qs) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            graph.get(i).add(i + 1);
        }

        int[] answer = new int[qs.length];

        for (int k = 0; k < qs.length; k++) {
            int u = qs[k][0];
            int v = qs[k][1];

            if (!graph.get(u).contains(v)) {
                graph.get(u).add(v);
            }

            answer[k] = bfs(graph, n);
        }

        return answer;
    }

    private int bfs(List<List<Integer>> graph, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[current] + 1;
                    queue.offer(neighbor);
                    if (neighbor == n - 1) {
                        return dist[neighbor];
                    }
                }
            }
        }
        return dist[n - 1];
    }
}


