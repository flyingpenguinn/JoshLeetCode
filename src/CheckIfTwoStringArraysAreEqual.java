public class CheckIfTwoStringArraysAreEqual {
    public boolean arrayStringsAreEqual(String[] w1, String[] w2) {
        int i1 = 0;
        int i2 = 0;
        int j1 = 0;
        int j2 = 0;
        while (i1 < w1.length && j1 < w2.length) {
            if (w1[i1].charAt(i2) == w2[j1].charAt(j2)) {
                i2++;
                j2++;
            } else {
                return false;
            }
            if (i2 == w1[i1].length()) {
                i1++;
                i2 = 0;
            }
            if (j2 == w2[j1].length()) {
                j1++;
                j2 = 0;
            }
        }
        return true;
    }
}
