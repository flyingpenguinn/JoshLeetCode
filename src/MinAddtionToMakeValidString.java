public class MinAddtionToMakeValidString {
    public int addMinimum(String s) {
        int n = s.length();
        String expected = "abc";
        int ei = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (expected.charAt(ei) != c) {
                ++res;
                ++ei;
                ei %= 3;
            }
            ++ei;
            ei %= 3;
        }
        while (ei != 0) {
            ++res;
            ++ei;
            ei %= 3;
        }
        return res;
    }
}
