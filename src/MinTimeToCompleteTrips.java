public class MinTimeToCompleteTrips {
    public long minimumTime(int[] a, int totalTrips) {
        long l = 1;
        long u = (long) 1e14;
        int n= a.length;
        while(l<=u){
            long mid = l+(u-l)/2;
            long cur = 0;
            boolean good = false;
            for(int i=0; i<n; ++i){
                long trips = mid/a[i];
                cur += trips;
                if(cur>=totalTrips){
                    good = true;
                    break;
                }
            }
            if(good){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
