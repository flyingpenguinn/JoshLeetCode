import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusRoutes {
    // visit as a level order in a bfs way.
    // note we dont expand a bus again if we have done so
    public int numBusesToDestination(int[][] a, int s, int t) {
        int n = a.length;
        Map<Integer, Set<Integer>> stoptobus = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int stop = a[i][j];
                int bus = i;
                stoptobus.computeIfAbsent(stop, k -> new HashSet<>()).add(bus);
            }
        }
        // stop and buses taken at this point
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s, 0});
        Set<Integer> seenstop = new HashSet<>();
        seenstop.add(s);
        Set<Integer> seenbus = new HashSet<>();
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int stop = top[0];
            int dist = top[1];
            if (stop == t) {
                return dist;
            }
            Set<Integer> buses = stoptobus.getOrDefault(stop, new HashSet<>());
            for (int bus : buses) {
                if (seenbus.add(bus)) {// important: if we expanded this bus already, dont do it again
                    for (int nextstop : a[bus]) {
                        if (seenstop.add(nextstop)) {
                            q.offer(new int[]{nextstop, top[1] + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
