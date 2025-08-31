import java.util.HashMap;
import java.util.Map;

public class TwoLetterCardGame {
    public int score(String[] s, char x) {
        int n = s.length;
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        int both = 0;
        for (int i = 0; i < n; ++i) {
            char[] si = s[i].toCharArray();
            if (si[0] == x && si[1] == x) {
                ++both;
            } else if (si[0] == x) {
                char sc2 = si[1];
                m1.put(sc2, m1.getOrDefault(sc2, 0) + 1);
            } else if (si[1] == x) {
                char sc1 = si[0];
                m2.put(sc1, m2.getOrDefault(sc1, 0) + 1);
            }
        }

        int all1 = 0;
        int max1 = 0;
        for (char k1 : m1.keySet()) {
            int v1 = m1.get(k1);
            all1 += v1;
            max1 = Math.max(max1, v1);
        }
        int oldall1 = all1;
        int all2 = 0;
        int max2 = 0;
        for (char k2 : m2.keySet()) {
            int v2 = m2.get(k2);
            all2 += v2;
            max2 = Math.max(max2, v2);
        }
        int oldall2 = all2;

        both = Math.min(both, all1 + all2);
        int oldboth = both;


        int pairs1 = 0;
        int rem1 = 0;
        if (all1 > 1 && max1 > all1 / 2) {
            int delta = (2 * max1 - all1);
            int reducedmax1 = Math.min(delta, both);
            max1 -= reducedmax1;
            both -= reducedmax1;
            pairs1 += reducedmax1;
            all1 -= reducedmax1;
            if (max1 <= all1 / 2) {
                pairs1 += all1 / 2;
            } else {
                int other1 = all1 - max1;
                pairs1 += other1;
                rem1 = all1 - 2 * other1;
            }
        } else {
            pairs1 = all1 / 2;
        }
        int pairs2 = 0;
        int rem2 = 0;
        if (all2 > 1 && max2 > all2 / 2) {
            int delta = (2 * max2 - all2);
            int reducedmax2 = Math.min(delta, both);
            max2 -= reducedmax2;
            both -= reducedmax2;
            pairs2 += reducedmax2;
            all2 -= reducedmax2;
            if (max2 <= all2 / 2) {
                pairs2 += all2 / 2;
            } else {
                int other2 = all2 - max2;
                pairs2 += other2;
                rem2 = all2 - 2 * other2;
            }
        } else {
            pairs2 = all2 / 2;
        }
        if (both > rem1 + rem2) {
            return (oldall1 + oldall2 + oldboth) / 2;
        }
        else {
            return pairs1 + pairs2 + both;
        }
    }
}
