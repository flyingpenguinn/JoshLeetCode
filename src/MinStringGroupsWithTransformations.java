import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinStringGroupsWithTransformations {
    // Booth algo for LMSR lexco minimal string rotation. left rotation and right rotation same idea
    public int minimumGroups(String[] words) {
        int n = words.length;
        Set<String> cnt = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            String even = getsub(words[i], 0);
            String odd = getsub(words[i], 1);
            String ne = leastRotation(even);
            String no = leastRotation(odd);
            String sig = ne + "|" + no;
            cnt.add(sig);
        }
        // System.out.println(cnt);
        return cnt.size();
    }

    private String getsub(String word, int start) {
        StringBuilder sb = new StringBuilder();
        int n = word.length();
        for (int i = start; i < n; i += 2) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }

    private String leastRotation(String s) {
        int n = s.length();
        int[] f = new int[2 * n];
        Arrays.fill(f, -1);

        int k = 0;

        for (int j = 1; j < 2 * n; j++) {
            int i = f[j - k - 1];

            while (i != -1
                    && s.charAt(j % n) != s.charAt((k + i + 1) % n)) {
                if (s.charAt(j % n) < s.charAt((k + i + 1) % n)) {
                    k = j - i - 1;
                }

                i = f[i];
            }

            if (i == -1
                    && s.charAt(j % n) != s.charAt((k + i + 1) % n)) {
                if (s.charAt(j % n) < s.charAt((k + i + 1) % n)) {
                    k = j;
                }

                f[j - k] = -1;
            } else {
                f[j - k] = i + 1;
            }
        }

        return s.substring(k) + s.substring(0, k);
    }
}
