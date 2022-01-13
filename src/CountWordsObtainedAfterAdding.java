import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountWordsObtainedAfterAdding {
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<String> set = new HashSet<>();
        for (String s : startWords) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            set.add(new String(sc));
        }
        int res = 0;
        for (String t : targetWords) {
            char[] tc = t.toCharArray();
            Arrays.sort(tc);
            int[] count = new int[26];
            for (char c : tc) {
                ++count[c - 'a'];
            }
            String st = new String(tc);
            for (int i = 0; i < st.length(); ++i) {
                char c = st.charAt(i);
                if (count[c - 'a'] > 1) {
                    continue;
                }
                StringBuilder sbt = new StringBuilder(st);
                sbt.deleteCharAt(i);
                String ssbt = sbt.toString();
                //
                if (set.contains(ssbt)) {
                    //    System.out.println(t);
                    ++res;
                    break;
                }
            }
        }
        return res;
    }
}
