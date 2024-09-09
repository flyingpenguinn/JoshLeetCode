import java.util.List;

public class ReachEndOfArrayWithMaxScore {
    // greedy bigger value always better can compare with going to a smaller value then bigger value
    public long findMaximumScore(List<Integer> a) {
        int n = a.size();
        int max = 0;
        long res = 0;
        for (int i = 0; i < n - 1; ++i) {
            max = Math.max(max, a.get(i));
            res += max;
        }
        return res;
    }
}
