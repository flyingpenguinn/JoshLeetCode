import java.util.HashMap;
import java.util.Map;

public class NumberOfWinningPlayers {
    public int winningPlayerCount(int n, int[][] a) {
        // HashMap to store player and their count of occurrences
        Map<Integer, Map<Integer, Integer>> m = new HashMap<>();

        // Filling the HashMap with the input data
        for (int[] ai : a) {
            int p = ai[0];
            int c = ai[1];
            m.putIfAbsent(p, new HashMap<>());
            m.get(p).put(c, m.get(p).getOrDefault(c, 0) + 1);
        }

        int res = 0;

        // Checking each player's picked numbers and counts
        for (Map.Entry<Integer, Map<Integer, Integer>> mp : m.entrySet()) {
            int p = mp.getKey();
            Map<Integer, Integer> picked = mp.getValue();
            for (Map.Entry<Integer, Integer> pp : picked.entrySet()) {
                if (pp.getValue() > p) {
                    res++;
                    break;
                }
            }
        }

        return res;
    }
}
