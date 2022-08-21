public class MinRecolorsToGetKConsecutiveBlocks {
    public int minimumRecolors(String blocks, int k) {
        char[] bs = blocks.toCharArray();
        int n = blocks.length();
        int blacks = 0;
        for (int i = 0; i < k - 1; ++i) {
            blacks += bs[i] == 'B' ? 1 : 0;
        }
        int res = (int) 1e9;
        for (int i = k - 1; i < n; ++i) {
            blacks += bs[i] == 'B' ? 1 : 0;
            int gap = k - blacks;
            res = Math.min(gap, res);
            int head = i - k + 1;
            blacks -= bs[head] == 'B' ? 1 : 0;
        }
        return res;
    }
}
