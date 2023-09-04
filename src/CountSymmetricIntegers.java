public class CountSymmetricIntegers {
    public int countSymmetricIntegers(int low, int high) {
        int res = 0;
        for (int i = low; i <= high; ++i) {
            if (good(i)) {
                ++res;
            }
        }
        return res;
    }

    private boolean good(int num) {
        String sn = String.valueOf(num);
        int len = sn.length();
        if (len % 2 == 1) {
            return false;
        }
        int half = len / 2;
        int sum1 = 0;
        for (int i = 0; i < half; ++i) {
            sum1 += sn.charAt(i) - '0';
        }
        int sum2 = 0;
        for (int i = len - half; i < len; ++i) {
            sum2 += sn.charAt(i) - '0';
        }
        return sum1 == sum2;
    }
}
