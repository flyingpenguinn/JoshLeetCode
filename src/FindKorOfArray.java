public class FindKorOfArray {
    public int findKOr(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int j = 0; j < 32; ++j) {
            int count = 0;
            for (int ai : a) {
                if (((ai >> j) & 1) == 1) {
                    ++count;
                }
            }
            if (count >= k) {
                res |= (1 << j);
            }
        }
        return res;
    }
}
