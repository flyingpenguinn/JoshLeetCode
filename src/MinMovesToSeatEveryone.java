import java.util.Arrays;

public class MinMovesToSeatEveryone {
    public int minMovesToSeat(int[] a, int[]b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int res = 0;
        int n = a.length;
        for(int i=0; i<n; ++i){
            res += Math.abs(a[i]-b[i]);
        }
        return res;
    }
}
