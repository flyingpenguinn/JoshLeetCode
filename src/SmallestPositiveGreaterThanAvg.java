import java.util.HashSet;
import java.util.Set;

public class SmallestPositiveGreaterThanAvg {
    public int smallestAbsent(int[] a) {
        int n = a.length;
        int sum = 0;
        Set<Integer> seen = new HashSet<>();
        for (int ai : a) {
            sum += ai;
            seen.add(ai);
        }
        double avg = sum * 1.0 / n;
        int start = (int) (avg) + 1;
        int i = Math.max(start, 1);
        while (true) {
            if (!seen.contains(i)) {
                return i;
            }
            ++i;
        }

    }
}
