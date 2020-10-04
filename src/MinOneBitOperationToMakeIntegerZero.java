public class MinOneBitOperationToMakeIntegerZero {
    // by observation we know 2^n needs 2^(n+1)-1 steps
    // for numbers bigger than 2^k, we need n-2^k LESS steps. note the conversion is undoable
    public int minimumOneBitOperations(int n) {
        if (n <= 1) {
            return n;
        }
        int bits = 0;
        while ((1 << bits) <= n) {
            bits++;
        }
        return (1 << bits) - 1 - minimumOneBitOperations(n - (1 << (bits - 1)));
    }
}
