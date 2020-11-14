public class DefuseTheBomb {
    public int[] decrypt(int[] a, int k) {
        int n = a.length;

        int[] res = new int[n];
        if (k == 0) {
            return res;
        }
        int psum = 0;
        if (k < 0) {
            for (int i = k; i < n; i++) {

                if (i >= 0) {
                    res[i] = psum;

                    psum -= eval(a, i + k);
                }
                psum += eval(a, i);
            }
        } else {
            for (int i = n + k - 1; i >= 0; i--) {

                if (i < n) {
                    res[i] = psum;
                    psum -= eval(a, i + k);
                }
                psum += eval(a, i);
            }
        }
        return res;
    }

    private int eval(int[] a, int i) {
        int n = a.length;
        int mod = i % n;
        if (mod < 0) {
            mod += n;
        }
        return a[mod];
    }
}
