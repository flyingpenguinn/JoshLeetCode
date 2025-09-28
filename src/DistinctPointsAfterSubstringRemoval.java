import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DistinctPointsAfterSubstringRemoval {
    private final Map<Character, int[]> dm = new HashMap<>();

    {
        dm.put('U', new int[]{0, 1});
        dm.put('D', new int[]{0, -1});
        dm.put('L', new int[]{-1, 0});
        dm.put('R', new int[]{1, 0});
    }

    public int distinctPoints(String s, int k) {
        int n = s.length();
        int curx = 0;
        int cury = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int dx = dm.get(c)[0];
            int dy = dm.get(c)[1];
            curx += dx;
            cury += dy;
        }
        int cutx = 0;
        int cuty = 0;
        for (int i = 0; i < k - 1; ++i) {
            char c = s.charAt(i);
            int dx = dm.get(c)[0];
            int dy = dm.get(c)[1];
            cutx += dx;
            cuty += dy;
        }
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (int i = k - 1; i < n; ++i) {
            char c = s.charAt(i);
            int dx = dm.get(c)[0];
            int dy = dm.get(c)[1];
            cutx += dx;
            cuty += dy;
            int remx = curx - cutx;
            int remy = cury - cuty;
            set.add(new Pair<>(remx, remy));
            int head = i-k+1;
            char hc = s.charAt(head);
            int hx = dm.get(hc)[0];
            int hy = dm.get(hc)[1];
            cutx -= hx;
            cuty -= hy;
        }
        return set.size();
    }
}
