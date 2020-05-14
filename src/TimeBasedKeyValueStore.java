import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {
}


class TimeMap {
    private HashMap<String, TreeMap<Integer, String>> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> tm = map.getOrDefault(key, new TreeMap<>());
        tm.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> tm = map.getOrDefault(key, new TreeMap<>());
        Map.Entry<Integer, String> entry = tm.floorEntry(timestamp);
        if (entry != null) {
            return entry.getValue();
        } else {
            return "";
        }
    }
}