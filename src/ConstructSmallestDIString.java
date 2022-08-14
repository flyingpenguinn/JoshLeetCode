public class ConstructSmallestDIString {
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        char[] res = new char[n + 1];
        for (int i = 0; i < res.length; ++i) {
            res[i] = (char) (i + '1');
        }
        int i = 1;
        while (i < res.length) {

            if (pattern.charAt(i - 1) == 'D') {
                int j = i;
                int start = i - 1;
                while (j < res.length && pattern.charAt(j - 1) == 'D') {
                    ++j;
                }
                reverse(res, start, j - 1);
                i = j;

            } else {
                ++i;
            }
        }
        return new String(res);
    }

    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            ++i;
            --j;
        }
    }
}
