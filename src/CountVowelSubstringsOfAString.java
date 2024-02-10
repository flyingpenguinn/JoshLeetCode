import java.util.*;

public class CountVowelSubstringsOfAString {
    private String vow = "aeiou";

    private boolean good(List<Integer> v) {
        for (int vi : v) {
            if (vi == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isVow(char c) {
        return vow.indexOf(c) != -1;
    }

    private int count(String s, int b, int e) {
        int j = b;
        List<Integer> vm = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vm.add(0);
        }
        int res = 0;
        for (int i = b; i < e; ++i) {
            int pos = vow.indexOf(s.charAt(i));
            vm.set(pos, vm.get(pos) + 1);
            while (good(vm)) {
                res += e - i; //all good from i all the way to e
                int pj = vow.indexOf(s.charAt(j));
                vm.set(pj, vm.get(pj) - 1);
                ++j;
            }
        }
        return res;
    }

    public int countVowelSubstrings(String s) {
        int n = s.length();
        int i = 0;
        int res = 0;
        while (i < n) {
            if (!isVow(s.charAt(i))) {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && isVow(s.charAt(j))) {
                ++j;
            }
            res += count(s, i, j);
            i = j;
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