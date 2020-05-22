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
    List<String> r = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Deque<String>> map = new HashMap<>();
        // sort small to big : note even if we reverse in the end, we want to go to smaller ones first because that's next to our starting point
        Collections.sort(tickets, (a, b) -> a.get(0).equals(b.get(0)) ? a.get(1).compareTo(b.get(1)) : a.get(0).compareTo(b.get(0)));
        for (List<String> t : tickets) {
            map.computeIfAbsent(t.get(0), k -> new ArrayDeque<>()).offer(t.get(1));
        }
        dfs("JFK", map);
        Collections.reverse(r);
        return r;
    }

    // note in euler path finding a node can be visited multiple times, so we dont use seen set. instead, we poll edges so each edge is only used once
    private void dfs(String s, Map<String, Deque<String>> map) {
        Deque<String> dk = map.getOrDefault(s, new ArrayDeque<>());
        while (!dk.isEmpty()) {
            String next = dk.poll();
            dfs(next, map);
        }
        r.add(s);
    }

    public static void main(String[] args) {
        List<List<String>> l2 = List.of(List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"), List.of("ATL", "JFK"), List.of("ATL", "SFO"));
        System.out.println(new ReconstructItinerary().findItinerary(new ArrayList<>(l2)));

        List<List<String>> l1 = List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        System.out.println(new ReconstructItinerary().findItinerary(new ArrayList<>(l1)));


    }
}