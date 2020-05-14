import java.util.*;

public class AllPathSourceToTarget {
    Map<Integer, List<List<Integer>>> dp = new HashMap<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        return doFindAllPath(0, n - 1, graph);
    }

    private List<List<Integer>> doFindAllPath(int s, int t, int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        if (s == t) {
            List<Integer> end = new LinkedList<>();
            end.add(t);
            result.add(end);
            return result;
        }
        if (dp.containsKey(s)) {
            return dp.get(s);
        }
        int n = graph.length;
        int[] connected = graph[s];
        for (int other : connected) {
            List<List<Integer>> later = doFindAllPath(other, t, graph);
            for (List<Integer> p : later) {
                List<Integer> nl = new LinkedList<>(p);
                nl.add(0, s);
                result.add(nl);
            }
        }
        dp.put(s, result);
        return result;
    }
}
