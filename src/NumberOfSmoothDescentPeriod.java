public class NumberOfSmoothDescentPeriod {
    // each segment would form 1+2+...n = n(n+1)/2 periods
    public long getDescentPeriods(int[] a) {
        int n = a.length;
        long res = 0;
        int i = 0;
        while(i<n){
            int j = i+1;
            while(j<n && a[j]==a[j-1]-1){
                ++j;
            }
            // i.. j-1
            long cur = j-i;
            res += cur*(cur+1)/2;
            i = j;
        }
        return res;
    }
}
