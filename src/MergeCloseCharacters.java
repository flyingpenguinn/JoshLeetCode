public class MergeCloseCharacters {
    private String domerge(String s, int k) {
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n && j <= i + k; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    String p1 = s.substring(0, j);
                    String p2 = s.substring(j + 1);
                    return domerge(p1 + p2, k);
                }
            }
        }
        return s;
    }

    public String mergeCharacters(String s, int k) {

        return domerge(s, k);
    }
}
