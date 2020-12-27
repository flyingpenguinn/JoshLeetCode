import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusRoutes {
    // visit as a level order in a bfs way.
    // note we dont expand a bus again if we have done so, similar to jump game iv
    // note the constraints are a bit misleading...
    public int numBusesToDestination(int[][] a, int s, int t) {

        int n = a.length;
        // stop-> bus
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i].length; j++) {
                m.computeIfAbsent(a[i][j], k -> new HashSet<>()).add(i);
            }
        }
        //     System.out.println(m);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s, 0});
        Set<Integer> seenstop = new HashSet<>();
        Set<Integer> seenbus = new HashSet<>();
        seenstop.add(s);
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int stop = top[0];
            int dist = top[1];
            //  System.out.println(stop+" "+dist);
            if (stop == t) {
                return dist;
            }
            Set<Integer> buses = m.getOrDefault(stop, new HashSet<>());
            for (int b : buses) {
                if (!seenbus.contains(b)) {
                    // key: if a bus was expanded before we dont need to redo it
                    seenbus.add(b);
                    for (int st : a[b]) {
                        //   System.out.println("adding..."+st);
                        if (!seenstop.contains(st)) {
                            seenstop.add(st);
                            q.offer(new int[]{st, dist + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
