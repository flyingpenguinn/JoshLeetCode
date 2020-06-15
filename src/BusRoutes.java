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

class BusRoutesVerboseDijkastra {
    // in essence, it's a graph with node and bus as state. nodes with the same stop but differnt bus has dist =1. otherwise dist =0. run dijkastra on it
    class State {
        int node;
        int bus;


        public State(int node, int bus) {
            this.node = node;
            this.bus = bus;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return node == state.node &&
                    bus == state.bus;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, bus);
        }
    }

    class Qitem {
        State state;
        int bustaken;

        public Qitem(State state, int bustaken) {
            this.state = state;
            this.bustaken = bustaken;
        }
    }

    public int numBusesToDestination(int[][] a, int s, int t) {
        if (s == t) {
            return 0;
        }
        int n = a.length;

        Set<State> seen = new HashSet<>();
        PriorityQueue<Qitem> q = new PriorityQueue<>((x, y) -> Integer.compare(x.bustaken, y.bustaken));
        // stop, on which bus, buses taken
        Map<State, State> g = new HashMap<>();
        Map<Integer, Set<Integer>> buses = new HashMap<>();
        Map<State, Integer> dist = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a[i].length; j++) {
                State start = new State(a[i][j], i);
                State end = new State(a[i][(j + 1) % a[i].length], i);
                g.put(start, end);
                buses.computeIfAbsent(a[i][j], k -> new HashSet<>()).add(i);
                if (a[i][j] == s) {
                    State initial = new State(a[i][j], i);
                    q.offer(new Qitem(initial, 1));
                    seen.add(initial);
                    dist.put(initial, 1);
                }
            }
        }
        while (!q.isEmpty()) {
            Qitem top = q.poll();
            if (top.state.node == t) {
                return top.bustaken;
            }
            State next = g.get(top.state);
            if (dist.getOrDefault(next, Max) > top.bustaken) {
                q.offer(new Qitem(next, top.bustaken));
            }
            int newdist = top.bustaken + 1;
            for (int bus : buses.getOrDefault(top.state.node, new HashSet<>())) {
                State newstate = new State(top.state.node, bus);
                if (dist.getOrDefault(newstate, Max) > newdist) {
                    dist.put(newstate, newdist);
                    q.offer(new Qitem(newstate, newdist));
                }
            }
        }
        return -1;
    }

    int Max = 10000000;
}