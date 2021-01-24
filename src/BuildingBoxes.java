import com.sun.jdi.PathSearchingVirtualMachine;

import java.util.Arrays;

public class BuildingBoxes {
    // 1, 1+2, 1+2+3....
    // for the remnant, we find 1+2+3...+j that could make it ==n, and the ones on the base is j
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

class BuildingBoxesBinarySearch {
    // binary search an answer if the base can support up to n. we lay the base as 1,2,3.... to get max coverage

    private int maxpossible = 1700000;

    public int minimumBoxes(long n) {
        long l = 1;
        long u = maxpossible;

        while (l <= u) {
            long mid = l + (u - l) / 2;
            long calced = calc(mid);
            if (calced >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;

    }

    private long calc(long n) {

        if (n <= 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }
        // binary find the last i*(i+1)/2 <=n
        int i = binary(n);
        long sum = i * (i + 1) / 2;
        long rem = n - sum;
        long gained = sum - i; // 1,2,3...i each -1
        gained += (rem == 0) ? 0 : rem - 1; // remaining ones like 1,2,2-> 1+1 = 2 got covered
        long res = n + calc(gained);
        return res;
    }

    private int binary(long n) {
        long l = 1;
        long u = maxpossible;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (mid * (mid + 1) / 2 <= n) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return (int) u;
    }


    public static void main(String[] args) {
        System.out.println(new BuildingBoxes().minimumBoxes(3));
    }
}
