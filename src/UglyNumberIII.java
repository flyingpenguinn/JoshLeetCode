public class UglyNumberIII {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long l = 1;
        long u = 2000000000;
        while(l<=u){
            long mid = l+(u-l)/2;
            if(nth(mid, a,b,c)>=n){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return (int)l;
    }

    private long nth(long t, long a, long b, long c){
        return t/a+t/b+t/c-t/lcm(a,b)-t/lcm(a,c)-t/lcm(b,c)+t/lcm(a,b,c);
    }

    private long gcd(long a, long b){
        if(a<b){
            return gcd(b,a);
        }
        return b==0?a:gcd(b, a%b);
    }

    private long lcm(long a, long b){
        return a*b/gcd(a,b);
    }

    private long lcm(long a, long b, long c){
        return lcm(lcm(a,b),c);
    }

}
