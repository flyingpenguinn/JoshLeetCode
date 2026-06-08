public class ExactlyOneConsecutiveBitsSetPair {
    public boolean consecutiveSetBits(int num) {
        String str = Integer.toBinaryString(num);
        int n = str.length();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (str.startsWith("11", i)) {
                //  System.out.println("hit");
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
