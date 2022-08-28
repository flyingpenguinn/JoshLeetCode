import java.util.*;

public class MinAmountTimeToCollectGarbage {
    private Set<Character> cars = new HashSet<>();

    {
        cars.add('M');
        cars.add('G');
        cars.add('P');
    }

    public int garbageCollection(String[] garbage, int[] travel) {
        Map<Character, List<Integer>> m = new HashMap<>();
        for (int j = 0; j < garbage.length; ++j) {
            String g = garbage[j];
            for (int i = 0; i < g.length(); ++i) {
                char c = g.charAt(i);
                m.computeIfAbsent(c, k -> new ArrayList<>()).add(j);
            }
        }
        int[] sum = new int[travel.length];
        for (int i = 0; i < travel.length; ++i) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + travel[i];
        }
        int res = 0;
        for (char c : cars) {
            List<Integer> list = m.getOrDefault(c, new ArrayList<>());
            int cur = 0;
            int i = 0;
            while (i < list.size()) {
                int next = list.get(i);
                int dist = dist(sum, cur, next);
                res += dist;
                ++res;
                cur = next;
                ++i;
            }
        }
        return res;
    }

    private int dist(int[] sum, int i, int j) {
        return (j <= 0 ? 0 : sum[j - 1]) - (i <= 0 ? 0 : sum[i - 1]);
    }
}
