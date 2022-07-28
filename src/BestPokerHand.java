import java.util.HashMap;
import java.util.Map;

public class BestPokerHand {
    public String bestHand(int[] ranks, char[] suits) {

        Map<Character, Integer> sm = new HashMap<>();
        Map<Integer, Integer> rm = new HashMap<>();
        for (char s : suits) {
            sm.put(s, sm.getOrDefault(s, 0) + 1);
        }
        int maxv = 0;
        for (int r : ranks) {
            rm.put(r, rm.getOrDefault(r, 0) + 1);
            maxv = Math.max(maxv, rm.get(r));
        }
        if (sm.size() == 1) {
            return "Flush";
        } else if (maxv >= 3) {
            return "Three of a Kind";
        } else if (maxv == 2) {
            return "Pair";
        } else {
            return "High Card";
        }

    }
}
