public class CheckIfPointIsReachable {
    // first divide by 2 as much as possible
    // then check they are co prime
    public boolean isReachable(int tx, int ty) {
        while (tx % 2 == 0) {
            tx /= 2;
        }
        while (ty % 2 == 0) {
            ty /= 2;
        }
        return gcd(tx, ty) == 1;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
