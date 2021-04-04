import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindUserActiveMinutes {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int[] log : logs) {
            int id = log[0];
            int min = log[1];
            m.computeIfAbsent(id, p -> new HashSet<>()).add(min);
        }
        Map<Integer, Integer> rm = new HashMap<>();
        for (int id : m.keySet()) {
            int count = m.get(id).size();
            rm.put(count, rm.getOrDefault(count, 0) + 1);
        }
        int[] res = new int[k];
        for (int count : rm.keySet()) {
            res[count - 1] = rm.get(count);
        }
        return res;
    }
}
