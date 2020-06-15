import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusRoutes {
    // the gist is to enumerate on bus not stop...same complexity as leetcode solution, but looks suspicious as the intersect part makes the solution n^2*len of bus route in theory
    public int numBusesToDestination(int[][] a, int s, int t) {
        if (s == t) {
            return 0;
        }
        int n = a.length;

        Map<Integer, Set<Integer>> bustostop = new HashMap<>();
        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int stop = a[i][j];
                int bus = i;
                bustostop.computeIfAbsent(bus, k -> new HashSet<>()).add(stop);
                if (a[i][j] == s) {
                    q.offerLast(new int[]{i, 1});
                    seen.add(i);
                }
            }
        }
        Map<Integer, Set<Integer>> nextbus = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intersect(bustostop.get(i), bustostop.get(j))) {
                    nextbus.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                    nextbus.computeIfAbsent(j, k -> new HashSet<>()).add(i);
                }
            }
        }

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int curbus = top[0];
            int dist = top[1];
            if (bustostop.getOrDefault(curbus, new HashSet<>()).contains(t)) {
                return dist;
            }
            for (Integer next : nextbus.getOrDefault(curbus, new HashSet<>())) {
                if (seen.add(next)) {
                    q.offerLast(new int[]{next, dist + 1});
                }
            }
        }
        return -1;
    }

    private boolean intersect(Set<Integer> s1, Set<Integer> s2) {
        for (int se : s1) {
            if (s2.contains(se)) {
                return true;
            }
        }
        return false;
    }
}

class BusRouteSimplerBfs {
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