public class SignOfProductOfArray {
    public int arraySign(int[] a) {
        int res = 1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                res *= 1;
            } else if (a[i] < 0) {
                res *= -1;
            } else {
                return 0;
            }
        }
        return res;
    }
}
