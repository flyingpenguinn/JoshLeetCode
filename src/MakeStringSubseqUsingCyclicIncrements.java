public class MakeStringSubseqUsingCyclicIncrements {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();

        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            char c1 = str1.charAt(i);
            int cind1 = c1 - 'a';
            char c2 = str2.charAt(j);
            int cind2 = c2 - 'a';
            if (cind1 == cind2) {
                ++i;
                ++j;
                continue;
            } else if ((cind1 + 1) % 26 == cind2) {
                ++i;
                ++j;
                continue;
            } else {
                ++i;
            }
        }
        if (j == n2) {
            return true;
        }

        return false;
    }
}
