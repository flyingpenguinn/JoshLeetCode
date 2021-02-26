import java.util.*;

public class SortFeaturesByFrequency {
    public String[] sortFeatures(String[] fs, String[] rs) {
        Map<String, Integer> m = new HashMap<>();
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < fs.length; i++) {
            m.put(fs[i], 0);
            index.put(fs[i], i);
        }
        for (String ri : rs) {
            String[] ris = ri.split(" ");
            Set<String> seen = new HashSet<>();
            for (String rii : ris) {
                if (rii.length() > 10) {
                    continue;
                } else if (seen.contains(rii)) {
                    continue;
                } else if (m.containsKey(rii)) {
                    m.put(rii, m.getOrDefault(rii, 0) + 1);
                    seen.add(rii);
                }
            }
        }
        Arrays.sort(fs, (x, y) -> {
            int nx = m.getOrDefault(x, 0);
            int ny = m.getOrDefault(y, 0);
            if (nx != ny) {
                return Integer.compare(ny, nx);
            } else {
                return Integer.compare(index.get(x), index.get(y));
            }
        });
        return fs;
    }
}
