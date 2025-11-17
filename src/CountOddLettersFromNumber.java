public class CountOddLettersFromNumber {
    private String[] m = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int countOddLetters(int n) {
        String sn = String.valueOf(n);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sn.length(); ++i) {
            int cind = sn.charAt(i) - '0';
            String str = m[cind];
            res.append(str);
        }
        int[] cnt = new int[26];
        for (int i = 0; i < res.length(); ++i) {
            int cind = res.charAt(i) - 'a';
            ++cnt[cind];
        }
        int rres = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] % 2 == 1) {
                ++rres;
            }
        }
        return rres;
    }
}
