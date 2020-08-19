public class MinInsertionToBalanceParenthesis {
    public int minInsertions(String s) {
        int openLeft = 0;
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                openLeft++;
                i++;
            } else {
                if (!s.startsWith("))", i)) {
                    // single ) is invalid
                    res++;
                    i++;
                } else {
                    i += 2;
                }
                if (openLeft == 0) {
                    res++;
                } else {
                    openLeft--;
                }
            }
        }
        res += openLeft * 2;
        return res;
    }
}
