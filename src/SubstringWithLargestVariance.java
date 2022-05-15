public class SubstringWithLargestVariance {
    // adapted Kadane
    public int largestVariance(String sc) {
        char[] s = sc.toCharArray();
        int n = s.length;
        int[] last = new int[26];
        for (int i = 0; i < n; ++i) {
            int cind = s[i] - 'a';
            last[cind] = i;
        }
        int res = 0;
        for (int j = 0; j < 26; ++j) {
            for (int k = 0; k < 26; ++k) {
                if (j == k) {
                    continue;
                }
                int csum = 0;
                boolean seenj = false;
                boolean seenk = false;
                for (int i = 0; i < n; ++i) {
                    int cind = s[i] - 'a';
                    int cv = 0;
                    if (cind == j) {
                        seenj = true;
                        cv = 1;
                    } else if (cind == k) {
                        seenk = true;
                        cv = -1;
                    }
                    // at least we would keep one k if we start from i
                    if (csum < 0 && last[k] >= i) {
                        csum = cv;
                        if (cind == j) {
                            seenk = false;
                        }
                        if (cind == k) {
                            seenj = false;
                        } else {
                            seenk = false;
                            seenj = false;
                        }

                    } else {
                        csum += cv;
                    }
                    if (seenj && seenk) {
                        res = Math.max(res, csum);
                    }

                }
            }
        }
        return res;
    }
}
