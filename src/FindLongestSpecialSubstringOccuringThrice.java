public class FindLongestSpecialSubstringOccuringThrice {
    // binary search solution that barely passes...
    public int maximumLength(String s) {
        int n = s.length();
        int l = 1;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            ++count[c - 'a'];
        }

        int u = count[0];
        for (int i = 1; i < 26; ++i) {
            u = Math.max(u, count[i]);
        }
        char[] chars = s.toCharArray();
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (doable(chars, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u == 0 ? -1 : u;
    }


    private boolean doable(char[] a, int t) {
        int n = a.length;
        int[] count = new int[26];
        int[] m = new int[26];
        for (int i = 0; i < t - 1; ++i) {
            int aind = a[i] - 'a';
            ++m[aind];
        }

        for (int i = t - 1; i < n; ++i) {
            int aind = a[i] - 'a';
            ++m[aind];
            int k = onlyone(m);
            if (k >= 0) {
                ++count[k];
                if (count[k] >= 3) {
                    return true;
                }
            }
            // System.out.println(i + " " + t);
            int head = a[i - t + 1] - 'a';
            --m[head];
        }
        return false;
    }

    private int onlyone(int[] m) {
        int res = -1;
        for (int i = 0; i < m.length; ++i) {
            if (m[i] > 0) {
                if (res == -1) {
                    res = i;
                } else {
                    return -1;
                }
            }
        }
        return res;
    }
}
