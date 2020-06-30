import base.ArrayUtils;

import java.util.*;
/*
LC#332
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
 */

public class ReconstructItinerary {
    // classical euler path finding: remove an edge after visiting the next node...once we exhaust a node, we add it at the last (actually first in this problem)
    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (x, y) -> x.get(1).compareTo(y.get(1)));
        Map<String, Deque<String>> g = new HashMap<>();
        for (List<String> tick : tickets) {
            String start = tick.get(0);
            String end = tick.get(1);
            g.computeIfAbsent(start, key -> new ArrayDeque<>()).add(end);
        }
        List<String> r = dfs("JFK", g);
        Collections.reverse(r);
        return r;
    }

    private List<String> dfs(String s, Map<String, Deque<String>> g) {
        Deque<String> q = g.getOrDefault(s, new ArrayDeque<>());
        List<String> r = new ArrayList<>();
        while (!q.isEmpty()) {
            String next = q.poll();
            // remove the edge after we visit it
            List<String> later = dfs(next, g);
            r.addAll(later);
        }
        r.add(s);
        return r;
    }

    public static void main(String[] args) {
        List<List<String>> l2 = List.of(List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"), List.of("ATL", "JFK"), List.of("ATL", "SFO"));
        System.out.println(new ReconstructItinerary().findItinerary(new ArrayList<>(l2)));

        List<List<String>> l1 = List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        System.out.println(new ReconstructItinerary().findItinerary(new ArrayList<>(l1)));


    }
}