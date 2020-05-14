import java.util.*;

/*
LC#399
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> es, double[] vs, List<List<String>> qs) {
        Map<String, Map<String, Double>> g = new HashMap<>();
        for (int i = 0; i < es.size(); i++) {
            List<String> e = es.get(i);
            put(vs[i], g, e.get(0), e.get(1));
            put(1.0 / vs[i], g, e.get(1), e.get(0)); // !
        }
        double[] r = new double[qs.size()];
        int ri = 0;
        Set<String> seen = new HashSet<>();
        for (List<String> q : qs) {
            seen.clear();
            double res = dfs(g, q.get(0), q.get(1), seen);
            r[ri++] = Double.isNaN(res) ? -1 : res;
        }
        return r;
    }

    private void put(double v, Map<String, Map<String, Double>> g, String s, String e) {
        Map<String, Double> cm = g.getOrDefault(s, new HashMap<>());
        cm.put(e, v);
        g.put(s, cm);
    }

    private double dfs(Map<String, Map<String, Double>> g, String i, String end, Set<String> seen) {
        if (!g.containsKey(i)) {
            return Double.NaN;
        }
        if (i.equals(end)) {
            return 1.0;
        }
        seen.add(i);
        Map<String, Double> cm = g.getOrDefault(i, new HashMap<>());
        for (String next : cm.keySet()) {
            if (seen.contains(next)) {
                continue;
            }
            double cur = cm.get(next) * dfs(g, next, end, seen);
            if (!Double.isNaN(cur)) {
                return cur;
            }
        }
        return Double.NaN;
    }
}
