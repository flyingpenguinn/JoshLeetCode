public class LongestSubseqWithNonzeroBitwiseXor {
    public int longestSubsequence(int[] a) {
        int n = a.length;
        int xor = 0;
        for (int ai : a) {
            xor ^= ai;
        }
        if (xor != 0) {
            return n;
        } else {
            for (int ai : a) {
                if (ai != 0) {
                    return n - 1;
                }
            }
            return 0;
        }
    }
}
