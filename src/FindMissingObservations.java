public class FindMissingObservations {
    public int[] missingRolls(int[] a, int mean, int n) {
        int m = a.length;
        int csum = 0;
        for(int i=0; i<m; ++i){
            csum += a[i];
        }
        int rem = mean*(n+m)-csum;
        if(rem > 6*n || rem<n){
            return new int[0];
        }
        int[] res = new int[n];
        int ri = 0;
        while(n>0){
            int taken = (int)Math.ceil(rem*1.0/n);
            res[ri++] = taken;
            rem -= taken;
            n--;
        }
        return res;
    }
}
