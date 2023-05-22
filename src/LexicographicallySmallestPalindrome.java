public class LexicographicallySmallestPalindrome {
    public String makeSmallestPalindrome(String s) {
        char[] ns = s.toCharArray();
        int i = 0;
        int j = ns.length - 1;
        while (i < j) {
            if (ns[i] < ns[j]) {
                ns[j] = ns[i];
            } else if (ns[i] > ns[j]) {
                ns[i] = ns[j];
            } else {
                //
            }
            ++i;
            --j;
        }
        return new String(ns);
    }
}
