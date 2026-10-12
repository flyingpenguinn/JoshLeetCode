public class ThreeFibonacciSum {
    public boolean threeFibonacciSum(int n) {
        if(n%2==1){
            return false;
        }
        long half = n/2;
        if(issq(5*half*half+4) || issq(5*half*half-4)){
            return true;
        }
        return false;
    }

    private boolean issq(long n){
        long sqrt = (long)Math.sqrt(n);
        return sqrt*sqrt == n;
    }
}
