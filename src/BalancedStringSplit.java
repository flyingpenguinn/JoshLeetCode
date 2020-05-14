public class BalancedStringSplit {
    public int balancedStringSplit(String s) {
        int ls = 0;
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                ls++;
            } else {
                ls--;
            }
            if (ls == 0) {
                r++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new BalancedStringSplit().balancedStringSplit("LLLLRRR"));
    }
}
