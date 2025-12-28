import java.util.HashSet;
import java.util.Set;

public class SmllestAllOneMultiple {
    public int minAllOneMultiple(int k) {
        Set<Long> seen = new HashSet<>();
        long cur = 1;
        long curmod = 0;
        int digits = 1;
        while (true) {
            long mod = cur % k;

            curmod += mod;
            curmod %= k;
            if (curmod == 0) {
                break;
            }
            if (seen.contains(curmod)) {
                return -1;
            }
            seen.add(curmod);
            cur *= 10;
            ++digits;
            cur %= k;
        }
        return digits;
    }
}
