import java.util.ArrayList;
import java.util.List;

public class MaxSplitOfPositiveEvenNumbers {
    // greedy first then subtract one number
    public List<Long> maximumEvenSplit(long n) {
        if (n % 2 == 1) {
            return new ArrayList<>();
        }
        List<Long> res = new ArrayList<>();
        long sum = 0;
        long cand = 2;
        while (sum < n) {
            res.add(cand);
            sum += cand;
            cand += 2;
        }
        if (sum > n) {
            long diff = sum - n;
            res.remove(Long.valueOf(diff));
        }
        return res;
    }
}
