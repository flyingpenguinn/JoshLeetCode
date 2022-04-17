public class CalculateDigiSumOfStrings {
    public String digitSum(String s, int k) {
        if (s.length() <= k) {
            return s;
        }
        String next = convert(s, k);
        return digitSum(next, k);
    }

    private String convert(String s, int k) {
        int n = s.length();
        int count = 0;
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - '0';
            cur += cind;
            ++count;
            if (count == k) {
                count = 0;
                sb.append(cur);
                cur = 0;
            }
        }
        if (count > 0) {
            sb.append(cur);
        }
        return sb.toString();
    }
}
