import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQL {
    private Map<String, Map<Integer, Map<Integer, String>>> m = new HashMap<>();
    private Map<String, Integer> rm = new HashMap<>();

    public SQL(List<String> names, List<Integer> columns) {
    }

    public void insertRow(String name, List<String> row) {
        rm.put(name, rm.getOrDefault(name, 0) + 1);
        int rowId = rm.get(name);
        for (int i = 0; i < row.size(); ++i) {
            add(name, rowId, i + 1, row.get(i));
        }
    }

    public void deleteRow(String name, int rowId) {
        remove(name, rowId);
    }

    public String selectCell(String name, int rowId, int columnId) {
        return m.getOrDefault(name, new HashMap<>()).getOrDefault(rowId, new HashMap<>()).getOrDefault(columnId, "");
    }

    private void add(String k1, int k2, int k3, String v) {
        m.computeIfAbsent(k1, q -> new HashMap<>()).computeIfAbsent(k2, r -> new HashMap<>()).put(k3, v);
    }

    private void remove(String k1, int k2) {
        if (!m.containsKey(k1)) {
            return;
        }
        m.get(k1).remove(k2);
    }
}
