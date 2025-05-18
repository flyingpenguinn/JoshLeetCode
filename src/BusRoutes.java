import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusRoutes {
    // stop to route = 1, route to stop = 0  , 01 BFS

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        int R = routes.length;
        int maxStop = 0;
        for (int[] r : routes) for (int s : r) maxStop = Math.max(maxStop, s);
        int S = maxStop + 1;
        List<List<Integer>> stopToRoutes = new ArrayList<>();
        for (int i = 0; i < S; i++) stopToRoutes.add(new ArrayList<>());
        for (int i = 0; i < R; i++)
            for (int s : routes[i])
                stopToRoutes.get(s).add(i);
        int N = S + R;
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<Integer> dq = new ArrayDeque<>();
        dist[source] = 0;
        dq.add(source);
        boolean[] usedRoute = new boolean[R];
        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            if (u < S) {
                for (int r : stopToRoutes.get(u)) {
                    int vr = S + r;
                    if (dist[vr] > dist[u] + 1) {
                        dist[vr] = dist[u] + 1;
                        dq.addLast(vr);
                    }
                }
            } else {
                int r = u - S;
                if (usedRoute[r]) continue;
                usedRoute[r] = true;
                for (int s : routes[r]) {
                    if (dist[s] > dist[u]) {
                        dist[s] = dist[u];
                        dq.addFirst(s);
                    }
                }
            }
        }
        return dist[target] == Integer.MAX_VALUE ? -1 : dist[target];
    }
}
