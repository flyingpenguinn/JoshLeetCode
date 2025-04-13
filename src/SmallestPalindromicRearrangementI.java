import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallestPalindromicRearrangementI {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        List<Integer> twos = new ArrayList<>();
        String ones = "";
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] == 0) {
                continue;
            }
            if (cnt[i] % 2 == 0) {
                twos.add(i);
            } else {
                ones += (char) ('a' + i);
                --cnt[i];
                if (cnt[i] > 0) {
                    twos.add(i);
                }
            }
        }
        Collections.sort(twos);
        StringBuilder half = new StringBuilder();
        for (int i = 0; i < twos.size(); ++i) {
            int value = twos.get(i);
            int vcnt = cnt[value] / 2;
            while (vcnt > 0) {
                half.append((char) ('a' + value));
                --vcnt;
            }
        }
        String other = new StringBuilder(half).reverse().toString();
        return half.toString() + ones + other;
    }
}
