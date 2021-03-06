public class SumOfBeautyOfAllSubstrings {
    public int beautySum(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                int cind = s.charAt(j) - 'a';
                freq[cind]++;
                int max = 0;
                int min = n + 1;
                for (int k = 0; k < 26; k++) {
                    if (freq[k] == 0) {
                        continue;
                    }
                    max = Math.max(max, freq[k]);
                    min = Math.min(min, freq[k]);
                }
                res += max - min;
            }
        }
        return res;
    }
}
