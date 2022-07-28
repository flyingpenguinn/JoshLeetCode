import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberContainers {

    private Map<Integer, TreeSet<Integer>> nm = new HashMap<>();
    private Map<Integer, Integer> idm = new HashMap<>();

    public NumberContainers() {

    }

    public void change(int index, int number) {
        if (idm.containsKey(index)) {
            int oldnum = idm.get(index);
            if (oldnum == number) {
                // no need to change
                return;
            }
            nm.get(oldnum).remove(index);
        }
        TreeSet<Integer> nv = nm.getOrDefault(number, new TreeSet<>());
        nv.add(index);
        nm.put(number, nv);
        idm.put(index, number);
    }

    public int find(int number) {
        if (nm.containsKey(number)) {
            if (nm.get(number).isEmpty()) {
                return -1;
            }
            return nm.get(number).first();
        } else {
            return -1;
        }
    }
}
