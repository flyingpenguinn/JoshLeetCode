import java.util.Arrays;

public class MinDeletionToMakeStringKSpecial {
    public int minimumDeletions(String word, int k) {
        int n = word.length();
        int[] f = new int[26];
        for (char c : word.toCharArray()) {
            ++f[c - 'a'];
        }
        Arrays.sort(f);
        // System.out.println(Arrays.toString(f));
        int i = 0;
        while (i < n && f[i] == 0) {
            ++i;
        }
        int res = n;
        for (; i < 26; ++i) {
            int cur = 0;
            for (int j = 0; j < i; ++j) {
                cur += f[j];
            }
            //    System.out.println(cur);
            // use f[i] as min
            for (int j = i + 1; j < 26; ++j) {
                if (f[j] - f[i] > k) {
                    int t = f[i] + k;
                    cur += f[j] - t;
                }
            }
            res = Math.min(res, cur);
        }
        return res;
    }
}
