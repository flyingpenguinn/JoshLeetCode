import java.util.HashSet;
import java.util.Set;

public class UniqueSubstringsWithEqualDigitFreq {
    // uniq substring1 rolling hash!
    private long key = 100000031L;
    private long mod = 10000000017L;

    public int equalDigitFrequency(String s) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            long hash = 0;
            int[] counter = new int[10];
            int uniq = 0;
            int maxf = 0;
            for (int j = i; j < n; ++j) {
                int cind = s.charAt(j) - '0';
                ++counter[cind];
                hash = key * hash + (cind + 1); // +1 so that 012 and 12 will hash differently
                hash %= mod;
                if (counter[cind] == 1) {
                    ++uniq;
                }
                maxf = Math.max(maxf, counter[cind]);
                if (maxf * uniq == j - i + 1) {
                    // the way to check it's all equal freq
                    set.add(hash);
                }
            }
        }
        return set.size();
    }
}
