import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountVowelSubstringsOfAString {
    // from oj to j all satisfy i as the ending index
    private String vowel = "aeiou";

    private boolean isvowel(char c) {
        return vowel.indexOf(c) != -1;
    }

    private int vindex(char c) {
        return vowel.indexOf(c);
    }

    public int countVowelSubstrings(String s) {
        return solve(s.toCharArray());
    }

    private int solve(char[] s) {
        int n = s.length;
        int res = 0;
        int j = 0;
        int[] counter = new int[5];
        int oj = j;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (!isvowel(c)) {
                oj = i + 1;
                j = oj;
                Arrays.fill(counter, 0);
            } else {
                ++counter[vindex(c)];
                while (good(counter)) {
                    --counter[vindex(s[j])];
                    ++j;
                }
                res += j - oj;
            }
        }
        return res;

    }

    private boolean good(int[] counter) {
        for (int ci : counter) {
            if (ci == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CountVowelSubstringsOfAString().countVowelSubstrings("bbaeixoubb"));
    }
}


class CountVowelSubstringsOfAStringConvertToKDistinct{
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
            if(a[i]<0){
                m.clear();
                start = i+1;
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