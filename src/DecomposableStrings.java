public class DecomposableStrings {
    public boolean isDecomposable(String s) {
        int i = 0;
        int n = s.length();
        boolean has2 = false;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int size = j - i;
            if (size % 3 == 1) {
                return false;
            } else if (size % 3 == 2) {
                if (has2) {
                    return false;
                }
                has2 = true;
            }
            i = j;
        }
        return has2;
    }
}
