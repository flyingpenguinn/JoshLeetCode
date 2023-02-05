public class CountVowelStringInRanges {
    private String vowels = "aeiou";

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] vows = new int[n];
        for (int i = 0; i < n; ++i) {
            String w = words[i];

            if (vowels.indexOf(w.charAt(0)) != -1 && vowels.indexOf(w.charAt(w.length() - 1)) != -1) {
                vows[i] = (i == 0 ? 0 : vows[i - 1]) + 1;
            } else {
                vows[i] = (i == 0 ? 0 : vows[i - 1]);
            }
        }
        //   System.out.println(Arrays.toString(vows));
        int qn = queries.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int l = queries[i][0];
            int u = queries[i][1];
            res[i] = vows[u] - (l == 0 ? 0 : vows[l - 1]);
        }
        return res;
    }
}
