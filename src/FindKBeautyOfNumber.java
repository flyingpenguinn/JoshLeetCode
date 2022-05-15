public class FindKBeautyOfNumber {
    public int divisorSubstrings(int num, int k) {
        String str = String.valueOf(num);
        int n = str.length();
        int res = 0;
        for (int i = 0; i + k - 1 < n; ++i) {
            String sub = str.substring(i, i + k);
            Long cur = Long.valueOf(sub);
            if (cur != 0 && num % cur == 0) {
                ++res;
            }
        }
        return res;
    }
}
