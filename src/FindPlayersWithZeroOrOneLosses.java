import java.util.*;

public class FindPlayersWithZeroOrOneLosses {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> lm = new HashMap<>();
        Set<Integer> pt = new HashSet<>();
        for (int[] m : matches) {
            int winner = m[0];
            int loser = m[1];
            lm.put(loser, lm.getOrDefault(loser, 0) + 1);
            pt.add(winner);
            pt.add(loser);
        }
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int k : pt) {
            if (!lm.containsKey(k)) {
                l1.add(k);
            } else if (lm.get(k) == 1) {
                l2.add(k);
            }
        }
        Collections.sort(l1);
        Collections.sort(l2);
        return List.of(l1, l2);
    }
}
