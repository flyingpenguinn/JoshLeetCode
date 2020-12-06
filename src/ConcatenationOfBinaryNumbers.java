public class ConcatenationOfBinaryNumbers {
    // note when size doesn't change we know it's just <<size+the number
    private int Mod = 1000000007;

    public int concatenatedBinary(int n) {
        long res = 0;
        int size = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                size++;
            }
            res = (res << size) + i;
            res %= Mod;
        }
        return (int) res;
    }
}
