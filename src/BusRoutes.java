import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BusRoutes {

    class Node {
        int value;
        int ith;
        int dist;

        public Node(int value, int ith, int dist) {
            this.value = value;
            this.ith = ith;
            this.dist = dist;
        }
    }

    // filter useless nodes that are already populated.
    // in bfs we can skip checking some edges when they don't make difference. this will reduce the complexity from O(v+e) when e is huge
    boolean[] ithset;
    Set<Integer> stopset = new HashSet<>();

    Map<Integer, Set<Integer>> con = new HashMap<>();

    // m = rows, n = max row lenth
    // there are at most m*n nodes in queue
    // we at most check m*n nodes when we traverse the queue. so overall time is Omn
    public int numBusesToDestination(int[][] routes, int s, int t) {
        int n = routes.length;
        ithset = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                Set<Integer> curcon = con.getOrDefault(routes[i][j], new HashSet<>());
                curcon.add(i);
                con.put(routes[i][j], curcon);
            }
        }
        Set<Integer> starter = con.getOrDefault(s, new HashSet<>());
        if (starter.isEmpty()) {
            return -1;
        }
        Set<Integer> ender = con.getOrDefault(t, new HashSet<>());
        if (ender.isEmpty()) {
            return -1;
        }
        if (s == t) {
            return 0;
        }
        int targetNode = t;
        int startNode = s;
        Deque<Node> q = new ArrayDeque<>();
        for (int si : con.get(startNode)) {
            Node node = new Node(startNode, si, 1);
            q.offer(node);
            stopset.add(startNode);
        }

        while (!q.isEmpty()) {
            Node top = q.poll();
            //  System.out.println(top.value);
            int ith = top.ith;
            int topdist = top.dist;
            // zero first 1 next we don't need a priority queue
            if (!ithset[ith]) {
                ithset[ith] = true;
                int[] group = routes[ith];
                for (int val : group) {
                    if (val == top.value) {
                        continue;
                    }
                    Node groupNode = new Node(val, ith, topdist);
                    if (groupNode.value == targetNode) {
                        return topdist;
                    }
                    q.offer(groupNode);
                }
            }
            if (!stopset.contains(top.value)) {
                stopset.add(top.value);
                Set<Integer> others = con.getOrDefault(top.value, new HashSet<>());
                for (int other : others) {
                    if (other == ith) {
                        continue;
                    }
                    Node othergroupNode = new Node(top.value, other, topdist + 1);
                    if (othergroupNode.value == targetNode) {
                        return othergroupNode.dist;
                    }
                    q.offer(othergroupNode);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\busroutes.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        int[][] routes = ArrayUtils.readUnEven(s);
        System.out.println(new BusRoutes().numBusesToDestination(routes, 0, 90000));
    }
}
