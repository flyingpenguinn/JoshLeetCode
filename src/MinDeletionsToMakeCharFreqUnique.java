import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinDeletionsToMakeCharFreqUnique {
    // focus on each char how many times we need to delete it
    public int minDeletions(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
        }
        Set<Integer> seen = new HashSet<>();
        int res = 0;
        for (int i = 0; i < 26; i++) {
            int t = cnt[i];
            if (t == 0) {
                continue;
            }
            while (t > 0 && seen.contains(t)) {
                t--;
                res++;
            }
            if (t > 0) {
                seen.add(t);
            }
        }
        return res;
    }
}

class MinDeletionToMakeCharsFreqUnique {
    // focus on the frequencies: if we know freq[1] = 3, freq[2] = 2, how to make them unique?
    public int minDeletions(String s) {
        int[] cnt = new int[26];
        int maxocc = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
            maxocc = Math.max(maxocc, cnt[c - 'a']);
        }
        int[] b = new int[maxocc + 1];
        for (int i = 0; i < 26; i++) {
            b[cnt[i]]++;
        }
        int res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 1; i < b.length; i++) {
            if (b[i] == 0) {
                st.push(i);
            } else if (b[i] > 1) {
                int rem = b[i] - 1;
                while (!st.isEmpty() && rem > 0) {
                    res += i - st.poll(); // stuff it to the previous hole if possible. meaning we delete this char to freq = st.poll
                    rem--;
                }
                res += i * rem; // for all the remaining ones there is no free hole we have to delete all of them
            }
        }
        return res;
    }
}
