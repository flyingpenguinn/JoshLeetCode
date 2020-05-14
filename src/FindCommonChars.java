import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;


public class FindCommonChars {
    public static void main(String[] args) {
        String[] strs = {"bella", "label", "roller"};
        System.out.println(new FindCommonChars().commonChars(strs));
    }

    public List<String> commonChars(String[] a) {
        int[] c = new int[26];
        Arrays.fill(c, Integer.MAX_VALUE);
        for (int i = 0; i < a.length; i++) {
            String as = a[i];
            int[] curmap = new int[26];
            for (int j = 0; j < as.length(); j++) {
                char ch = as.charAt(j);
                curmap[ch - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                c[j] = Math.min(c[j], curmap[j]);
            }
        }
        List<String> r = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < c[i]; j++) {
                char ch = (char) ('a' + i);
                r.add(String.valueOf(ch));
            }
        }
        return r;
    }
}
