import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class DesignEventManager {
    class EventManager {
        private Map<Integer, Integer> em = new HashMap<>();
        private TreeMap<Integer, TreeSet<Integer>> pm = new TreeMap<>();

        public EventManager(int[][] events) {
            for (int[] e : events) {
                int en = e[0];
                int ep = e[1];
                em.put(en, ep);
                pm.computeIfAbsent(ep, k -> new TreeSet<>()).add(en);
            }
        }

        public void updatePriority(int eventId, int newPriority) {
            if (!em.containsKey(eventId)) {
                return;
            }
            int oldp = em.get(eventId);
            TreeSet<Integer> oldcands = pm.get(oldp);
            oldcands.remove(eventId);
            if (oldcands.isEmpty()) {
                pm.remove(oldp);
            }
            em.put(eventId, newPriority);
            pm.computeIfAbsent(newPriority, k -> new TreeSet<>()).add(eventId);
        }

        public int pollHighest() {
            if (pm.isEmpty()) {
                return -1;
            }
            int lastKey = pm.lastKey();
            TreeSet<Integer> cand = pm.get(lastKey);
            int rt = cand.pollFirst();
            if (cand.isEmpty()) {
                pm.remove(lastKey);
            }
            return rt;
        }
    }
}
