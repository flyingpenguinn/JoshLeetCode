import java.util.HashMap;
import java.util.Map;

public class FrequencyTracker {
    private Map<Integer, Integer> fm = new HashMap<>();
    private Map<Integer, Integer> rm = new HashMap<>();

    public FrequencyTracker() {

    }

    private int update(Map<Integer, Integer> m, int k, int d) {
        int oldv = m.getOrDefault(k, 0);
        int nv = oldv + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
        return oldv;
    }

    public void add(int number) {
        int oldv = update(fm, number, 1);
        if (oldv != 0) {
            update(rm, oldv, -1);
        }
        update(rm, oldv + 1, 1);
    }

    public void deleteOne(int number) {
        int oldv = update(fm, number, -1);
        if (oldv != 0) {
            update(rm, oldv, -1);
        }
        if (oldv - 1 > 0) {
            update(rm, oldv - 1, 1);
        }
    }

    public boolean hasFrequency(int frequency) {
        return rm.containsKey(frequency);
    }
}