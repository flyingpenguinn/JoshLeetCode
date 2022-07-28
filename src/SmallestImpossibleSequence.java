import java.util.HashSet;
import java.util.Set;

public class SmallestImpossibleSequence {
    public int shortestSequence(int[] a, int k) {
        int res = 1;
        Set<Integer> s = new HashSet<>();
        for (int ai : a) {
            s.add(ai);
            if (s.size() == k) {
                res++;
                s.clear();
            }
        }
        return res;
    }
}
