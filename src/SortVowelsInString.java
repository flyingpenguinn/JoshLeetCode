import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortVowelsInString {
    private String vow = "aeiouAEIOU";

    public String sortVowels(String s) {
        int n = s.length();
        char[] res = s.toCharArray();
        List<Character> vs = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vow.indexOf(c) != -1) {
                vs.add(c);
            }
        }
        Collections.sort(vs);

        //   System.out.println(vs);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (vow.indexOf(res[i]) != -1) {
                res[i] = vs.get(j++);
            }
        }
        return new String(res);
    }
}
