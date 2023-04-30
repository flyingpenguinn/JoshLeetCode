import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MakeArrayEmpty {
    // can also use BIT
    public long countOperationsToEmptyArray(int[] A) {
        Map<Integer, Integer> pos = new HashMap<>();
        long n = A.length, res = n;
        for (int i = 0; i < n; ++i)
            pos.put(A[i], i);
        Arrays.sort(A);
        for (int i = 1; i < n; ++i)
            if (pos.get(A[i]) < pos.get(A[i - 1]))
                res += n - i;
        return res;
    }
}
