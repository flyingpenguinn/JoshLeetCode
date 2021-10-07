import java.util.Arrays;

public class CountSubarraysWithMoreOnesThanZeros {
    // sumj-sumi > j-i/2, so 2*sumj-j > 2*sumi-i
    private long mod = (long)(1e9+7);
    private int[] bit;
    public int subarraysWithMoreZerosThanOnes(int[] a) {
        int n = a.length;
        int sum = Arrays.stream(a).sum();
        bit= new int[2*(sum+1)+n+1];
        int offset = n+1;
        int csum = 0;
        long res = 0;
        u(1+offset);
        for(int j=0; j<n; ++j){
            csum += a[j];
            int vj = 2*csum-j;
            long cur = q(vj+offset-1);
            res += cur;
            res %= mod;
            u(vj+offset);
        }
        return (int) res;
    }

    private void u(int i){
        while(i<bit.length){
            ++bit[i];
            i += i&(-i);
        }
    }

    private long q(int i){
        long res = 0;
        while(i>0){
            res += bit[i];
            i -= i&(-i);
        }
        return res;
    }
}
