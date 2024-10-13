import java.util.Arrays;
import java.util.List;

public class CountMinBitwiseArrayIandII {
    public int[] minBitwiseArray(List<Integer> a) {
        int n = a.size();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; ++i) {
            int v = a.get(i);
            int plus1 = v + 1;
            if (v == 2) {
                continue;
            } else if ((plus1 & (plus1 - 1)) == 0) {
                res[i] = plus1 / 2 - 1;
            } else {
                int c1 = 0;
                for (int k = 0; k < 32; ++k) {
                    int dig = ((v >> k) & 1);
                    //System.out.println("k="+k+" dig="+dig);
                    if (dig == 0) {
                        break;
                    } else {
                        ++c1;
                    }
                }

                res[i] = v - (1 << (c1 - 1));
            }
        }
        return res;
    }
}
