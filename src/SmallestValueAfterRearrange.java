import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallestValueAfterRearrange {
    public long smallestNumber(long num) {
        String str = String.valueOf(num);
        List<Integer> digits = new ArrayList<>();
        int minNonzero = 10;
        for (int i = (num < 0 ? 1 : 0); i < str.length(); ++i) {
            int v = str.charAt(i) - '0';
            digits.add(v);
            if (v > 0) {
                minNonzero = Math.min(minNonzero, v);
            }
        }
        if (num < 0) {
            Collections.sort(digits, Collections.reverseOrder());
            long res = 0;
            for (int i = 0; i < digits.size(); ++i) {
                res = res * 10 - digits.get(i);
            }
            return res;
        } else {
            if (minNonzero == 10) {
                return 0;
            }
            digits.remove(Integer.valueOf(minNonzero));
            Collections.sort(digits);
            long res = minNonzero;
            for (int i = 0; i < digits.size(); ++i) {
                res = res * 10 + digits.get(i);
            }
            return res;
        }
    }
}
