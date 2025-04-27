import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CountCoveredBuildings {
    public int countCoveredBuildings(int n, int[][] b) {
        Map<Integer, TreeSet<Integer>> mr = new HashMap<>();
        Map<Integer, TreeSet<Integer>> mc = new HashMap<>();
        for (int[] bi : b) {
            int r = bi[0];
            int c = bi[1];
            mr.computeIfAbsent(r, k -> new TreeSet<>()).add(c);
            mc.computeIfAbsent(c, k -> new TreeSet<>()).add(r);
        }
        int res = 0;
        for (int[] bi : b) {
            int r = bi[0];
            int c = bi[1];
            TreeSet<Integer> onr = mr.getOrDefault(r, new TreeSet<>());
            TreeSet<Integer> onc = mc.getOrDefault(c, new TreeSet<>());
            if (onr.lower(c) != null && onr.higher(c) != null && onc.lower(r) != null && onc.higher(r) != null) {
                System.out.println(r + " " + c);
                ++res;
            }
        }
        return res;
    }
}
