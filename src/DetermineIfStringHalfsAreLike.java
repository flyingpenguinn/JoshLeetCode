public class DetermineIfStringHalfsAreLike {
    private String vow = "aeiouAEIOU";

    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int hn = n / 2;
        int v1 = 0;
        int v2 = 0;
        for (int i = 0; i < hn; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + hn);
            if (vow.indexOf(c1) != -1) {
                v1++;
            }
            if (vow.indexOf(c2) != -1) {
                v2++;
            }
        }
        return v1 == v2;
    }
}
