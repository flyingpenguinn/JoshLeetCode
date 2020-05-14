package base;

import java.util.*;

public class GraphAlgo {


    Map<Integer, Set<Integer>> graph = new HashMap<>();
    Map<Integer, Set<Integer>> reverseGraph = new HashMap<>();
    TreeMap<Integer, Integer> completed = new TreeMap<>();
    int completedCount = 0;
    Set<Integer> visited = new HashSet<>();
    int scc = 0;

    public int scc(int[][] transactions) {
        buildGraphs(transactions);
        completed = new TreeMap<>();
        dfs();
        visited.clear();
        rdfs();
        return scc;
    }

    private void rdfs() {
        for (int count : completed.descendingKeySet()) {
            Integer node = completed.get(count);
            if (!visited.contains(node)) {
                scc++;
                doRdfs(node);
            }
        }
    }

    private void doRdfs(Integer s) {
        visited.add(s);
        for (Integer c : reverseGraph.getOrDefault(s, new HashSet<>())) {
            if (!visited.contains(c)) {
                doRdfs(c);
            }
        }
    }

    private void dfs() {
        for (int s : graph.keySet()) {
            if (!visited.contains(s)) {
                doDfs(s);
            }
        }
    }

    private void doDfs(int s) {
        visited.add(s);
        for (Integer c : graph.getOrDefault(s, new HashSet<>())) {
            if (!visited.contains(c)) {
                doDfs(c);
            }
        }
        completed.put(completedCount++, s);
    }

    private void buildGraphs(int[][] transactions) {
        for (int i = 0; i < transactions.length; i++) {
            int[] t = transactions[i];
            Set<Integer> connected = graph.getOrDefault(t[0], new HashSet<>());
            connected.add(t[1]);
            graph.put(t[0], connected);
            Set<Integer> rc = reverseGraph.getOrDefault(t[1], new HashSet<>());
            rc.add(t[0]);
            reverseGraph.put(t[1], rc);
        }

    }

}
