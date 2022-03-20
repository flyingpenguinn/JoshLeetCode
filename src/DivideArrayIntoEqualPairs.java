import java.util.HashSet;
import java.util.Set;

public class DivideArrayIntoEqualPairs {
    public boolean divideArray(int[] a) {
        Set<Integer> m = new HashSet<>();
        for (int ai : a) {
            if (!m.contains(ai)) {
                m.add(ai);
            } else {
                m.remove(ai);
            }
        }
        return m.isEmpty();
    }
}
