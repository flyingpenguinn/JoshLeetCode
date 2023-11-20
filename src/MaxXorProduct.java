public class MaxXorProduct {

    // give "1" to the currently smaller value
    private long mod = (long) (1e9 + 7);

    public int maximumXorProduct(long a, long b, int n) {
        for (long i = n - 1; i >= 0; i--) {
            long bit = (long) (1) << i;
            if ((bit & a) == (bit & b)) {
                a = a | bit;
                b = b | bit;
            } else {
                if (a > b) {
                    long tmp = a;
                    a = b;
                    b = tmp;
                }
                a = a | bit;
                b = b & (~bit);
            }
        }
        return (int) (((a % mod) * (b % mod)) % mod);
    }

    public static void main(String[] args) {
        System.out.println(new MaxXorProduct().maximumXorProduct(12, 5, 4));
    }
}
