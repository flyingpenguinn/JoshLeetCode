public class NumberOfXorTripletsI {
    /*
    If a number num is of p bits, then the number of possible XOR values between numbers from 1 to n is 2^p.
     */
    public int uniqueXorTriplets(int[] a) {
        int n = a.length;
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int base = 1;
        int res = 1;
        while(base<=n){
            res *= 2;
            base *= 2;
        }
        return res;
    }
}
