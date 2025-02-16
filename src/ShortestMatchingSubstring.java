import java.util.ArrayList;
import java.util.List;

public class ShortestMatchingSubstring {
    // cut to 3 parts and do kmp
    private int Max = (int) 1e9;

    public int shortestMatchingSubstring(String s, String p) {
        if (p.equals("**")) {
            return 0;
        }
        int s1 = p.indexOf("*");
        int s2 = p.indexOf("*", s1 + 1);
        String p1 = p.substring(0, s1);
        String p2 = p.substring(s1 + 1, s2);
        String p3 = p.substring(s2 + 1);
        if (p1.isEmpty() && p3.isEmpty()) {
            return oneofthem(s, p2);
        }
        if (p1.isEmpty() && p2.isEmpty()) {
            return oneofthem(s, p3);
        }
        if (p2.isEmpty() && p3.isEmpty()) {
            return oneofthem(s, p1);
        }
        if (p1.isEmpty()) {
            List<Integer> i2 = kmpSearch(s, p2);
            List<Integer> i3 = kmpSearch(s, p3);
            return twofothem(i2, i3, p2.length(), p3.length());
        }
        if (p2.isEmpty()) {
            List<Integer> i1 = kmpSearch(s, p1);
            List<Integer> i3 = kmpSearch(s, p3);
            return twofothem(i1, i3, p1.length(), p3.length());
        }
        if (p3.isEmpty()) {
            List<Integer> i1 = kmpSearch(s, p1);
            List<Integer> i2 = kmpSearch(s, p2);
            return twofothem(i1, i2, p1.length(), p2.length());
        }
        List<Integer> i1 = kmpSearch(s, p1);
        int n1 = i1.size();
        int len1 = p1.length();
        List<Integer> i2 = kmpSearch(s, p2);
        int len2 = p2.length();
        List<Integer> i3 = kmpSearch(s, p3);
        int len3 = p3.length();
        int n2 = i2.size();
        int n3 = i3.size();
        int res = Max;
        for (int i = 0; i < n2; ++i) {
            int v2 = i2.get(i);
            int pos1 = binarylastnonbigger(i1, v2 - len1);
            int pos3 = binaryFirstNonSmaller(i3, v2 + len2);
            if (pos1 >= 0 && pos1 < n1 && pos3 >= 0 && pos3 < n3) {
                int v1 = i1.get(pos1);
                int v3 = i3.get(pos3);
                int cur = v3 + len3 - 1 - v1 + 1;
                res = Math.min(res, cur);
            }
        }
        return res >= Max ? -1 : res;
    }

    private int binaryFirstNonSmaller(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int twofothem(List<Integer> l1, List<Integer> l2, int len1, int len2) {
        int n1 = l1.size();
        int n2 = l2.size();
        int res = Max;
        for (int i = 0; i < n2; ++i) {
            int v2 = l2.get(i);
            int pos = binarylastnonbigger(l1, v2 - len1);
            if (pos >= 0 && pos < n1) {
                int v1 = l1.get(pos);
                int cur = v2 + len2 - 1 - v1 + 1;
                res = Math.min(res, cur);
            }
        }
        return res >= Max ? -1 : res;
    }

    private int binarylastnonbigger(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid) <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    protected int oneofthem(String s, String p2) {
        if (s.indexOf(p2) != -1) {
            return p2.length();
        } else {
            return -1;
        }
    }


    public List<Integer> kmpSearch(String s, String a) {
        List<Integer> indices = new ArrayList<>();
        int[] lps = computeLPSArray(a);

        int j = 0; // index for a (pattern)
        int i = 0; // index for s (text)
        while (i < s.length()) {
            if (a.charAt(j) == s.charAt(i)) {
                j++;
                i++;
            }
            if (j == a.length()) {
                indices.add(i - j);
                j = lps[j - 1];
            } else if (i < s.length() && a.charAt(j) != s.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return indices;
    }

    private int[] computeLPSArray(String a) {
        int[] lps = new int[a.length()];
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < a.length()) {
            if (a.charAt(i) == a.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
