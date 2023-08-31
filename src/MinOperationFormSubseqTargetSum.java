import java.util.Collections;
import java.util.List;

public class MinOperationFormSubseqTargetSum {
    public int minOperations(List<Integer> a, int t) {
        int[] b = new int[33];
        for (int i = 0; i < 32; ++i) {
            if (((t >> i) & 1) == 1) {
                ++b[i];
            }
        }
        //    System.out.println(Arrays.toString(b));
        Collections.sort(a);
        int pow = 1;
        int pb = 0;
        int n = a.size();
        int[] pbs = new int[33];
        for (int i = 0; i < n; ++i) {
            while (pow < a.get(i)) {
                pow *= 2;
                ++pb;
            }
            ++pbs[pb];
        }

        //   System.out.println(Arrays.toString(pbs));
        int res = 0;
        for (int i = 0; i < 32; ++i) {

            if (pbs[i] >= b[i]) {
                int delta = pbs[i] - b[i];
                pbs[i + 1] += delta / 2;
                //    System.out.println("extra "+delta+" at "+i);
                pbs[i] -= delta;
            } else {
                //  System.out.println("short at "+i);
                int found = -1;
                for (int j = i + 1; j < 32; ++j) {
                    if (pbs[j] > 0) {
                        found = j;
                        break;
                    }
                }
                if (found == -1) {
                    return -1;
                }
                //   System.out.println("found at "+found);

                for (int k = found - 1; k >= i; --k) {
                    //   System.out.println("expanding at "+k);
                    ++res;
                    --pbs[k + 1];
                    pbs[k] += 2;
                }
                pbs[i] -= b[i];
            }
            //    System.out.println("after "+i+" "+Arrays.toString(pbs));
        }
        return res;
    }
}
