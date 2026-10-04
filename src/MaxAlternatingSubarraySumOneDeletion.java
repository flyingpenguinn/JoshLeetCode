public class MaxAlternatingSubarraySumOneDeletion {
    private long Min = (long) -1e18;
    public long maxAlternatingSum(int[] a) {
        int n = a.length;
        long p0 = a[0];
        long p1 = Min;
        long m0 = Min;
        long m1 = Min;

        long p02ago = Min;
        long m02ago = Min;
        long res = a[0];

        for(int i=1; i<n; ++i){
            long x = a[i];
            long nm0 = p0-x;
            long np0 = Math.max(x, m0+x);
            
            long nm1 = p1-x;
            long np1 = m1+x;

            if(i>=2){
              np1 = Math.max(np1, m02ago+x);
              nm1 = Math.max(nm1, p02ago-x);
            }
            p02ago = p0;
            m02ago = m0;
            p0 = np0;
            m0 = nm0;
            p1 = np1;
            m1 = nm1;
            res = Math.max(res, Math.max(np0, nm0));
            res = Math.max(res, Math.max(np1, nm1));
        }
        return res;
    }
}
