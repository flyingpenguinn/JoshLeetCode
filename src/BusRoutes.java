import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusRoutes {
    // can do dijkastra or queue doesnt matter. gist is know what bus we boarded and don't board it again. because we generate dist greedily this is still optimal
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, Set<Integer>> sm = new HashMap<>();
        int n = routes.length;
        for (int i = 0; i < n; ++i) {
            for (int st : routes[i]) {
                sm.computeIfAbsent(st, p -> new HashSet<>()).add(i);
            }
        }
        Map<Integer, Integer> dist = new HashMap<>();
        int Max = (int) 1e9;
        dist.put(source, 0);
        Deque<int[]> pq = new ArrayDeque<>();
        Set<Integer> seenbus = new HashSet<>();
        pq.offer(new int[]{source, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int stop = top[0];
            int cd = top[1];

            if (stop == target) {
                return cd;
            }
            int nd = cd + 1;
            for (int bus : sm.getOrDefault(stop, new HashSet<>())) {
                if (seenbus.contains(bus)) {
                    continue;
                }
                //   System.out.println("stop "+stop+" onto bus "+bus);
                seenbus.add(bus);
                for (int nextstop : routes[bus]) {

                    if (dist.getOrDefault(nextstop, Max) > nd) {
                        dist.put(nextstop, nd);
                        pq.offer(new int[]{nextstop, nd});
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new BusRoutes().numBusesToDestination(ArrayUtils.read("[[1,2,7],[3,6,7]]"), 1, 6));
    }
}
