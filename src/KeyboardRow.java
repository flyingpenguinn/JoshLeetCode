import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#500
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.






Example:

Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]


Note:

You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {

    int[] rows = {2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};

    public String[] findWords(String[] words) {
        List<String> lr = new ArrayList<>();
        for (String w : words) {
            if (w.isEmpty()) {
                continue;
            }
            char c = w.charAt(0);
            int row = rows[Character.toLowerCase(c) - 'a'];
            boolean bad = false;
            for (int i = 1; i < w.length(); i++) {
                c = w.charAt(i);
                int nrow = rows[Character.toLowerCase(c) - 'a'];
                if (nrow != row) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                lr.add(w);
            }
        }
        String[] r = new String[lr.size()];
        for (int i = 0; i < r.length; i++) {
            r[i] = lr.get(i);
        }
        return r;
    }

    public static void main(String[] args) {
        String[] input = {"Hello", "Alaska", "Qwe", "Peace"};
        System.out.println(Arrays.toString(new KeyboardRow().findWords(input)));
    }
}
