public class MinimizeXor {
    public int minimizeXor(int num1, int num2) {
        int bits = 0;
        for (int j = 0; j < 32; ++j) {
            if (((num2 >> j) & 1) == 1) {
                ++bits;
            }
        }
        int res = 0;
        for (int i = 31; i >= 0; --i) {
            int nres = (res << 1);
            if (((num1 >> i) & 1) == 1) {
                if (bits > 0) {
                    nres += 1;
                    --bits;
                }
            } else {
                int rem = i + 1;
                if (rem == bits) {
                    nres += 1;
                    --bits;
                }
            }
            res = nres;
        }
        return res;

    }
}
