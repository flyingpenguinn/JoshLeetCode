public class LexicographicallySmallestAfterOneOperation {
    public String smallestString(String s) {
        char[] c = s.toCharArray();
        int n = s.length();
        int i = 0;
        while (i < n && c[i] == 'a') {
            ++i;
        }
        while (i < n && c[i] != 'a') {
            c[i] -= 1;
            ++i;
        }

        String ns = new String(c);

        return ns;
    }
}
