import java.util.*;

public class HighFive {
    // can also put a heap in map
    public int[][] highFive(int[][] items) {
        int sn = items.length;
        Map<Integer, List<Integer>> ls = new HashMap<>();

        for (int[] it : items) {
            List<Integer> cm = ls.getOrDefault(it[0], new ArrayList<>());
            cm.add(it[1]);
            ls.put(it[0], cm);
        }
        Set<Integer> keys = ls.keySet();
        int[][] r = new int[keys.size()][2];
        int rp = 0;
        for (int key : keys) {
            List<Integer> vs = ls.get(key);
            Collections.sort(vs, Collections.reverseOrder());
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += vs.get(j);
            }
            r[rp][0] = key;
            r[rp][1] = sum / 5;
            rp++;

        }

        return r;
    }
}
