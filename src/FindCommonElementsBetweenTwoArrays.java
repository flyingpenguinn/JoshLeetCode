import java.util.HashSet;
import java.util.Set;

public class FindCommonElementsBetweenTwoArrays {
    public int[] findIntersectionValues(int[] a, int[] b) {
        Set<Integer> sa = new HashSet<>();
        Set<Integer> sb = new HashSet<>();
        for (int ai : a) {
            sa.add(ai);
        }
        for (int bi : b) {
            sb.add(bi);
        }
        int na = 0;
        int nb = 0;
        for (int ai : a) {
            if (sb.contains(ai)) {
                ++na;
            }
        }
        for (int bi : b) {
            if (sa.contains(bi)) {
                ++nb;
            }
        }
        return new int[]{na, nb};
    }
}
