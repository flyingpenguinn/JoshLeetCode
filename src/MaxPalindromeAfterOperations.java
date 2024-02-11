import java.util.Arrays;

public class MaxPalindromeAfterOperations {
    // if odd not enough split an even to help it
    public int maxPalindromesAfterOperations(String[] a) {
        int[] cnt = new int[26];
        int n = a.length;
        int[] lens = new int[n];
        for (int i = 0; i < n; ++i) {
            String s = a[i];
            for (char c : s.toCharArray()) {
                ++cnt[c - 'a'];
            }
            lens[i] = s.length();
        }
        int odds = 0;
        int evens = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] % 2 == 1) {
                evens += cnt[i] - 1;
                ++odds;
            } else {
                evens += cnt[i];
            }
        }

        Arrays.sort(lens);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (lens[i] % 2 == 0) {
                if (evens >= lens[i]) {
                    ++res;
                    evens -= lens[i];
                }
            } else {
                if (evens >= lens[i] - 1 && odds > 0) {
                    ++res;
                    evens -= lens[i] - 1;
                    --odds;
                } else if (evens >= lens[i]) {
                    ++res;
                    evens -= lens[i] - 1;
                    ++odds;
                }
            }
        }
        return res;
    }
}
