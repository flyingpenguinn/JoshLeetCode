public class MinTimeToCompleteAllDeliveries {
    // set problem! either consider individual and overall, or use only a, only b... to segregate counting
    public long minimumTime(int[] d, int[] r) {
        long l = 1;
        long u = (long)1e15;
        while(l<=u){
            long mid = l+(u-l)/2;
            if(good(d, r, mid)){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

    private long gcd(long a, long b){
        return b==0?a: gcd(b, a%b);
    }
    private long lcm(long a, long b){
        return a*b / gcd(a,b);
    }

    private boolean good(int[] d, int[] r, long mid){
        long lcm = lcm(r[0], r[1]);
        long either = mid - mid / lcm;
        long onlya = mid/r[1] - mid/lcm;
        long onlyb = mid/r[0] - mid/lcm;
        long both = either - onlya - onlyb;
        long shared = Math.max(d[0] - onlya, 0) + Math.max(d[1] - onlyb, 0);
        return shared <= both;
    }
}
