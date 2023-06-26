public class MinOperationsMakeIntegerZero {
    // a -> power of 2, at most bigcount numbers and at most = a
    public int makeTheIntegerZero(long a, long b) {
        for(int i=1; i<=64; ++i){
            long cur = a-i*b;
            int bits = Long.bitCount(cur);
            if(bits<=i && i<=cur){
                return i;
            }
        }
        return -1;
    }
}
