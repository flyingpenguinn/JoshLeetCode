import java.util.Arrays;

public class FindNthValueAfterKSeconds {
    public int valueAfterKSeconds(int n, int k) {
        long[] a = new long[n];
        Arrays.fill(a, 1L);
        long Mod = (long)(1e9+7);
        for(int t=1; t<=k; ++t){
            for(int i=1; i<n; ++i){
                a[i] = a[i-1] + a[i];
                a[i] %= Mod;
            }
        }
        return (int)a[n-1];
    }
}
