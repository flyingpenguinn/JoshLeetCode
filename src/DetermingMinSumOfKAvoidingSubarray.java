import java.util.HashSet;
import java.util.Set;

public class DetermingMinSumOfKAvoidingSubarray {
    public int minimumSum(int n, int k) {

        Set<Integer> dead = new HashSet<>();
        int cur = 1;
        int res = 0;
        while (n > 0) {
            if (dead.contains(cur)) {
                ++cur;
                continue;
            }
            if (cur < k) {
                dead.add(k - cur);
            }
            //  System.out.println(cur);
            res += cur;
            ++cur;
            --n;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DetermingMinSumOfKAvoidingSubarray().minimumSum(50, 50));
    }
}
