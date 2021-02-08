import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.Arrays;

public class BuildingBoxes {
    // 1, 1+2, 1+2+3....
    // for the remnant, we find 1+2+3...+j that could make it >=n, and the ones on the base is j
    public int minimumBoxes(long n) {
        long sum = 0;
        long i = 1;
        long cur = 0;
        while (n > 0) {
            cur = i * (i + 1) / 2;
            //     System.out.println(n+" "+cur);
            if (n - cur == 0) {
                return (int) cur;
            } else if (n - cur < 0) {
                break;
            }
            n -= cur;
            i++;
        }
        i--; // i-1 is the last good i
        cur = i * (i + 1) / 2;
        // find 1+2+3...j >= n
        int j = 1;
        while (j * (j + 1) / 2 < n) {
            j++;
        }
        return (int) (cur + j);
    }

}
