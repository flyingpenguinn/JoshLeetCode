import java.util.Arrays;

public class SumOfFlooredPairs {
    private int mod = 1000000007;
    public int sumOfFlooredPairs(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int last = 0;
        long res = 0;
        for(int i = 0; i<n; i++){
            long cand = a[i];
            last = lower_bound(a,0, i, cand);
            while(last <n){
                long times = a[last]/cand;
                int next = lower_bound(a, last, n-1, cand*(times+1));
                int diff = next-last;
                res += diff*times;
                res %= mod;
                last = next;
            }
        }
        return (int) res;
    }

    // first num >=t
    private int lower_bound(int[] a, int l, int u, long t){
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid]>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
