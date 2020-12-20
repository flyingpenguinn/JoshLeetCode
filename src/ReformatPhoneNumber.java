public class ReformatPhoneNumber {
    public String reformatNumber(String s) {
        int n = s.length();
        StringBuilder res = new StringBuilder();
        StringBuilder curblock = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                continue;
            }
            curblock.append(c);
            if (curblock.length() == 3) {
                append(res, curblock);
                curblock = new StringBuilder();
            }
        }
        if (curblock.length() == 1) {
            // must be a 4 -> 3+1, make it 2+2 if it's a 3+1. if it's single 1 block then we merely add it
            if (res.length() > 0) {
                char last = res.charAt(res.length() - 1);
                res.setLength(res.length() - 1);
                res.append("-");
                res.append(last);
            }
            res.append(curblock.charAt(0));
        } else if (curblock.length() > 0) {
            append(res, curblock);
        }
        return res.toString();
    }

    protected void append(StringBuilder res, StringBuilder curblock) {
        if (res.length() > 0) {
            res.append("-");
        }
        res.append(curblock);
    }
}
