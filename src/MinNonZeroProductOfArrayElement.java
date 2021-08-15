public class MinNonZeroProductOfArrayElement {
    // we must calculate the participants not using mod
    // then we can power with mod but be careful of the base % mod
    private int mod = 1000000007;

    public int minNonZeroProduct(long p) {
        if(p==1){
            return 1;
        }
        long power2 = powerNoMod(p, 2);
        long power2m1 = powerNoMod(p-1, 2)-1;
        long power21 = power2-1;
        long power21mod = power21%mod ;
        long power22 = power2-2;
        long part1 = power(power2m1, power22);
        part1 *= power21mod;
        part1 %= mod;
        return (int)part1;
    }

    private long powerNoMod(long p, long base){
        if(p==0){
            return 1;
        }
        long half = powerNoMod(p/2, base);
        long halfp = half*half;
        if(p%2==0){
            return halfp;
        }else{
            halfp *= base;
            return halfp;
        }
    }

    private long power(long p, long base){
        base %= mod;
        if(p==0){
            return 1L;
        }
        long half = power(p/2, base);
        long halfp = half*half;
        halfp %= mod;
        if(p%2==0){
            return halfp;
        }else{
            halfp *= base;
            halfp %= mod;
            return halfp;
        }
    }
}
