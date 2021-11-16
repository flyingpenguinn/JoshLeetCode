public class CheckWhetherTwoStringsAlmostEqual {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] m1 = new int[26];
        int[] m2 = new int[26];
        for (char w1 : word1.toCharArray()) {
            ++m1[w1 - 'a'];
        }
        for (char w2 : word2.toCharArray()) {
            ++m2[w2 - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (Math.abs(m1[i] - m2[i]) > 3) {
                return false;
            }
        }
        return true;
    }
}
