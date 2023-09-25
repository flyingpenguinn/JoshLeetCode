public class MaxOddBinaryNumber {
    public String maximumOddBinaryNumber(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            int index = c - '0';
            if (index == 1) {
                ++ones;
            }
        }
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ones - 1; ++i) {
            sb.append("1");
        }
        for (int i = ones - 1; i < n - 1; ++i) {
            sb.append("0");
        }
        sb.append("1");
        return sb.toString();
    }
}
