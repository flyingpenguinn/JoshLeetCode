import java.util.TreeMap;

public class HandOfStraights {
    // basically need a java bag...
    public boolean isNStraightHand(int[] hand, int w) {
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        for (int h : hand) {
            ts.put(h, ts.getOrDefault(h, 0) + 1);
        }
        while (!ts.isEmpty()) {
            int min = ts.firstKey();
            int mc = ts.get(min);
            if (mc == 1) {
                ts.remove(min);
            } else {
                ts.put(min, mc - 1);
            }
            for (int i = 1; i < w; i++) {

                int tg = i + min;
                // System.out.println("tg "+tg+ " " +min);

                Integer nt = ts.get(tg);
                if (nt != null) {
                    int nc = nt - 1;
                    if (nc == 0) {
                        ts.remove(tg);
                    } else {
                        ts.put(tg, nc);
                    }

                } else {
                    // System.out.println("nf "+tg);
                    return false;
                }
            }
        }
        return true;


    }
}
