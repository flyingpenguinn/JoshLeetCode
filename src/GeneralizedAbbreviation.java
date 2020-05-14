import java.util.ArrayList;
import java.util.List;

/*
LC#320
Write a function to generate the generalized abbreviations of a word.

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class GeneralizedAbbreviation {
    // either keep the char or make it number 1 and merge with prev
    List<String> r = new ArrayList<>();

    public List<String> generateAbbreviations(String word) {
        dog(word.toCharArray(), 0, "", 0);
        return r;
    }

    void dog(char[] s, int i, String cur, int accu) {
        int n = s.length;
        if (i == n) {
            if (accu != 0) {
                r.add(cur + accu);
            } else {
                r.add(cur);
            }
            return;
        }
        dog(s, i + 1, cur, accu + 1);
        if (accu != 0) {
            dog(s, i + 1, cur + accu + s[i], 0);
        } else {
            dog(s, i + 1, cur + s[i], 0);
        }
    }
}
