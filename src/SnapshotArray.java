import java.util.TreeMap;

public class SnapshotArray {
    TreeMap<Integer, Integer>[] map;
    int snap = 0;

    public SnapshotArray(int length) {
        map = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new TreeMap<>();
            map[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        map[index].put(snap, val);
    }

    public int snap() {
        return snap++;
    }

    public int get(int index, int snapid) {
        Integer key = map[index].floorKey(snapid);
        return map[index].get(key);
    }
}
