public class HashDividedString {
    public String stringHash(String s, int k) {
        int n = s.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i += k) {
            String sub = s.substring(i, i + k);
            int sum = 0;
            for (char c : sub.toCharArray()) {
                sum += c - 'a';
            }
            char hc = (char) ((sum % 26) + 'a');
            result.append(hc);
        }

        return result.toString();
    }
}
