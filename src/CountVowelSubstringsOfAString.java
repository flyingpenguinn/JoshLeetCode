import java.util.*;

public class CountVowelSubstringsOfAString {
    // 3 pointers!
    private String vows = "aeiou";

    private void update(Map<Character, Integer> m, char k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int countVowelSubstrings(String s) {
        int n = s.length();
        int j = 0;
        int k = 0;
        int res = 0;
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (vows.indexOf(c) == -1) {
                j = i + 1;
                k = j;
                m.clear();
            } else {
                update(m, c, 1);
                while (k <= i && m.size() == 5) {
                    char vk = s.charAt(k);
                    update(m, vk, -1);
                    ++k;
                }
                res += k - j;
            }
        }
        return res;
    }
}


class CountVowelSubstringsOfAStringConvertToKDistinct {
    private String vowel = "aeiou";


    private int vindex(char c) {
        return vowel.indexOf(c);
    }

    // same as #992 k different integers trick
    public int countVowelSubstrings(String s) {
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            a[i] = vindex(s.charAt(i));
        }
        return subarraysWithKDistinct(a, 5);
    }

    private int subarraysWithKDistinct(int[] a, int k) {
        return atmost(a, k) - atmost(a, k - 1);
    }

    private int atmost(int[] a, int k) {
        if (k == 0) {
            return 0;
        }
        int n = a.length;
        int start = 0;
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) {
                m.clear();
                start = i + 1;
                continue;
            }
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
            while (m.size() > k) {
                m.put(a[start], m.get(a[start]) - 1);
                if (m.get(a[start]) == 0) {
                    m.remove(a[start]);
                }
                start++;
            }
            // start...i good, have at most k chars
            res += i - start + 1;
        }
        return res;
    }
}