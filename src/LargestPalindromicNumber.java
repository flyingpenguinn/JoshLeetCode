public class LargestPalindromicNumber {
    public String largestPalindromic(String num) {
        int[] dig = new int[10];
        for (int i = 0; i < num.length(); ++i) {
            int cind = num.charAt(i) - '0';
            ++dig[cind];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; --i) {
            if (dig[i] > 0 && dig[i] % 2 == 0) {
                sb.append(i);
            } else if (dig[i] % 2 == 1 && dig[i] > 1) {
                sb.append(i);
            }
        }
        if (sb.length() == 1 && sb.charAt(0) == '0') {
            sb = new StringBuilder();
        }
        int middle = -1;
        for (int i = 9; i >= 0; --i) {
            if (dig[i] % 2 == 1) {
                middle = i;
                break;
            }
        }
        if (sb.length() == 1 && sb.charAt(0) == '0') {
            sb = new StringBuilder();
        }
        if (sb.length() == 0 && middle == -1) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sb.length(); ++i) {
            int cind = sb.charAt(i) - '0';
            for (int j = 0; j < dig[cind] / 2; ++j) {
                res.append(cind);
            }
        }
        if (middle == -1) {
            return res.toString() + res.reverse().toString();
        } else {
            return res.toString() + middle + res.reverse().toString();
        }
    }
}
