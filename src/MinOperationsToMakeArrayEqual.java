public class MinOperationsToMakeArrayEqual {
    // if even, 1,...n-1, n/2 items
    // if odd, 0....n-1, (n+1)/2 items
    public int minOperations(int n) {
        if (n % 2 == 0) {
            return n * n / 4;
        } else {
            return (n - 1) * (n + 1) / 4;
        }
    }
}
