public class MakeStringAntiPalindrome {
    // greedily take smallest, if have to cross middle, make sure we choose next ones to avoid middle char repeating
    public String makeAntiPalindrome(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            int cind = c - 'a';
            ++cnt[cind];
            if (cnt[cind] > n / 2) {
                return "-1";
            }
        }
        char[] res = new char[n];
        int j = 0;
        int i = 0;
        while (i < 26) {
            char cv = (char) ('a' + i);
            if (cnt[i] == 0) {
                ++i;
                continue;
            }
            if (j < n / 2 && j + cnt[i] - 1 >= n / 2) {
                int avoids = n / 2 - j;
                while (j < n / 2) {
                    res[j++] = cv;
                    --cnt[i];
                }
                int k = i + 1;
                while (k < 26 && avoids > 0) {
                    if (cnt[k] == 0) {
                        ++k;
                        continue;
                    }
                    while (cnt[k] > 0 && avoids > 0) {
                        res[j++] = (char) ('a' + k);
                        --cnt[k];
                        --avoids;
                    }
                    if (avoids > 0) {
                        ++k;
                    }
                }
                while (cnt[i] > 0) {
                    res[j++] = cv;
                    --cnt[i];
                }
                i = k;
            } else {
                while (cnt[i] > 0) {
                    res[j++] = cv;
                    --cnt[i];
                }
                ++i;
            }
        }
        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(new MakeStringAntiPalindrome().makeAntiPalindrome("lmmn"));
    }
}
