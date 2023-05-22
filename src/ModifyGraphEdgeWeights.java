import javafx.util.Pair;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ModifyGraphEdgeWeights {
    // do shortst distance first, then pick one to assign good value. good means we can figure out the right weight by having weight = target - current shortest dist
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        Map<Integer, Integer>[] adjs = new Map[n];
        for (int i = 0; i < n; i++) {
            adjs[i] = new HashMap<>();
        }

        for (int[] edge : edges) {
            adjs[edge[0]].put(edge[1], edge[2]);
            adjs[edge[1]].put(edge[0], edge[2]);
        }

        int[] distTo = new int[n];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[source] = 0;

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        pq.add(new int[]{source, 0});

        dijkstra(adjs, distTo, pq);

        if (distTo[destination] == target) {
            return fill(edges);
        } else if (distTo[destination] < target) {
            return new int[0][0];
        } else {
            for (int[] edge : edges) {
                if (edge[2] == -1) {
                    edge[2] = 1;
                    adjs[edge[0]].put(edge[1], 1);
                    adjs[edge[1]].put(edge[0], 1);

                    pq.clear();
                    pq.add(new int[]{edge[0], distTo[0]});
                    pq.add(new int[]{edge[1], distTo[1]});

                    dijkstra(adjs, distTo, pq);

                    if (distTo[destination] == target) {
                        return fill(edges);
                    } else if (distTo[destination] < target) {
                        edge[2] += target - distTo[destination];
                        adjs[edge[0]].put(edge[1], edge[2]);
                        adjs[edge[1]].put(edge[0], edge[2]);
                        return fill(edges);
                    }
                } else {
                    // else we didnt find anything so we keep looking
                }
            }
        }

        return new int[0][0];
    }

    private int[][] fill(int[][] edges) {
        for (int[] edge : edges) {
            if (edge[2] == -1) {
                edge[2] = (int) (2 * 1e9);
            }
        }
        return edges;
    }

    private void dijkstra(Map<Integer, Integer>[] adjs, int[] distTo, Queue<int[]> pq) {
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            for (Map.Entry<Integer, Integer> entry : adjs[curr[0]].entrySet()) {
                if (entry.getValue() > 0) {
                    int next = entry.getKey();
                    if (distTo[next] - entry.getValue() > distTo[curr[0]]) {
                        distTo[next] = distTo[curr[0]] + entry.getValue();
                        pq.add(new int[]{next, distTo[next]});
                    }
                }
            }
        }
    }
}

