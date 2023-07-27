import java.util.Arrays;

public class MaxRunningTimeOfNComputers {
    // either use whole battery for piece the smaller ones together....
    public long maxRunTime(long n, int[] b) {
        long l = 1;
        long u = (long) 1e14;
        Arrays.sort(b);
        while (l <= u) {
            long mid = l + (u - l) / 2L;
            long sum = 0;
            for(int i=0; i<b.length; ++i){
                sum += Math.min(b[i], mid);
            }
            if (sum < n*mid) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return u;
    }
}
