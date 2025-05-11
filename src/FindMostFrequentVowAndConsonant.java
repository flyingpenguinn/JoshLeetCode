public class FindMostFrequentVowAndConsonant {
    private String vow = "aeiou";
    private int[] vc = new int[26];
    private int[] cc = new int[26];
    private int maxv = 0;
    private int maxc = 0;

    public int maxFreqSum(String s) {
        int n = s.length();
        for (char c : s.toCharArray()) {
            int cind = c - 'a';
            if (vow.indexOf(c) != -1) {
                ++vc[cind];
                maxv = Math.max(maxv, vc[cind]);
            } else {
                ++cc[cind];
                maxc = Math.max(maxc, cc[cind]);
            }
        }
        return maxv + maxc;
    }
}
