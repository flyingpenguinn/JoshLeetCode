public class RemoveTrailingZerosFromString {
    public String removeTrailingZeros(String s) {
        int n = s.length();
        int i = n - 1;
        while (i >= 0 && s.charAt(i) == '0') {
            --i;
        }
        return s.substring(0, i + 1);
    }
}
