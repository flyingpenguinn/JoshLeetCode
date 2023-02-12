import java.util.Arrays;

public class SubstringWithMinScore {
    // thought about binary search, but didnt come up with pref/suff myself
    private int[] pref;
    private int[] suff;

    public int minimumScore(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        pref = new int[tn];
        suff = new int[tn];
        Arrays.fill(pref, -1);
        Arrays.fill(suff, -1);
        precompute(s, t);
        int l = 0;
        int u = tn;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (can(mid, t, s)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean can(int mid, String t, String s) {
        int tn = t.length();
        for (int i = 0; i + mid - 1 < tn; ++i) {
            int j = i + mid - 1;
            int a = (i == 0) ? 0 : pref[i - 1];
            int b = (j == tn - 1) ? 0 : suff[j + 1];
            //cout<<i<<"   "<<j<<"    "<<a<<"    "<<b<<endl;
            if (a == -1 || b == -1) {
                continue;
            }
            if (a < b) {
                return true;
            }
            if (j == tn - 1 && a >= 0) {
                return true;
            }
            if (i == 0 && b >= 0) {
                return true;
            }
        }
        return false;
    }

    private void precompute(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int i = 0;
        int j = 0;
        while (i < sn && j < tn) {
            if (s.charAt(i) == t.charAt(j)) {
                pref[j] = i;
                ++j;
            }
            ++i;
        }
        i = sn - 1;
        j = tn - 1;
        while (i >= 0 && j >= 0) {
            if (s.charAt(i) == t.charAt(j)) {
                suff[j] = i;
                --j;
            }
            --i;
        }
    }
}
