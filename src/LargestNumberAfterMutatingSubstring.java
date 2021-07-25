public class LargestNumberAfterMutatingSubstring {
    public String maximumNumber(String num, int[] change) {
        char[] str = num.toCharArray();
        int n = num.length();
        int i = 0;
        while (i < n) {
            int cind = str[i] - '0';
            int changed = change[cind];
            if (changed <= cind) {
                i++;
            } else {
                break;
            }
        }
        while (i < n) {
            int cind = str[i] - '0';
            int changed = change[cind];
            if (changed >= cind) {
                str[i] = (char) ('0' + changed);
                i++;
            } else {
                break;
            }
        }
        return new String(str);
    }
}
