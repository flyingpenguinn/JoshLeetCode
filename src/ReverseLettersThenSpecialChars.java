public class ReverseLettersThenSpecialChars {
    public String reverseByType(String s) {
        int n = s.length();
        StringBuilder p1 = new StringBuilder();
        StringBuilder p2 = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isAlphabetic(c)) {
                p1.append(c);
            } else {
                p2.append(c);
            }
        }
        p1.reverse();
        p2.reverse();
        StringBuilder res = new StringBuilder();
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isAlphabetic(c)) {
                res.append(p1.charAt(i1++));
            } else {
                res.append(p2.charAt(i2++));
            }
        }
        return res.toString();
    }
}
