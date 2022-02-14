import java.util.Arrays;

class RemovingMinMagicBeans {
    public long minimumRemoval(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        long sum = 0;
        for(int i=0; i<n; ++i){
            sum += a[i];
        }
        long removed = 0;
        long remsum = sum;
        long min = (long)(1e15);
        int i = 0;
        while(i<n){
            long later = (0L+n-i)*a[i];
            long latermove = remsum - later;
            long allmove = latermove + removed;
            min = Math.min(min, allmove);
            int j = i;
            while(j<n && a[j]==a[i]){
                ++j;
            }
            removed += (j-i)*a[i];
            remsum -= (j-i)*a[i];
            i=j;
        }
        return min;
    }
}
