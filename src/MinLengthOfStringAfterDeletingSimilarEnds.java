public class MinLengthOfStringAfterDeletingSimilarEnds {
    public int minimumLength(String s) {
        int i = 0;
        int j = s.length() - 1;
        char lastc = '*';
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
            char c = s.charAt(i);

            while (i < j && s.charAt(i) == c) {
                i++;
            }
            // i-1 is the end of prefix; we now start from i
            while (i - 1 < j && s.charAt(j) == c) {
                j--;
            }
            // i-1 is the end of prefix, so j can move to i
        }
        return j - i + 1;
    }
}
