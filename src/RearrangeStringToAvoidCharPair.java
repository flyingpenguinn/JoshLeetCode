public class RearrangeStringToAvoidCharPair {
    public String rearrangeString(String s, char x, char y) {
        int n = s.length();
        int yc = 0;
        for (char c : s.toCharArray()) {
            if (c == y) {
                ++yc;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (yc > 0) {
            sb.append(y);
            --yc;
        }
        for (char c : s.toCharArray()) {
            if (c != y) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
