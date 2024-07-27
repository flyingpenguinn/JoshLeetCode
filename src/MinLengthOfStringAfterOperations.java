public class MinLengthOfStringAfterOperations {
    public int minimumLength(String s) {
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++count[cind];
        }
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            if (count[i] == 0) {
                continue;
            }
            if (count[i] % 2 == 0) {
                res += 2;
            } else {
                res += 1;
            }
        }
        return res;
    }
}
