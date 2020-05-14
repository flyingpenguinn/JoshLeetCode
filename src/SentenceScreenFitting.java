/*
LC#418
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output:
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output:
2

Explanation:
a-bcd-
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output:
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#418
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output:
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output:
2

Explanation:
a-bcd-
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output:
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] ss, int rows, int cols) {
        int k = 0;
        int len = 0;
        for (String s : ss) {
            len += s.length();
            len++;
        }
        int r = 0;
        int n = ss.length;
        // O(row*len), use % to skip lots of cols
        for (int i = 0; i < rows; i++) {
            r += cols / len;
            int remcol = cols % len;
            // if it's abc abc vs 7, we will handle the abc separately below and still record a fit
            while (remcol >= ss[k].length()) {
                remcol -= ss[k].length() + 1;
                //look t this elegant way: if happens to be == ss[k].length(), remcol will be <0 so terminate at the next move
                if (k == n - 1) {
                    r++;
                }
                k = (k + 1) % n;
            }
        }
        return r;
    }


    public static void main(String[] args) {
        //  System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"hello", "world"}, 10000, 9));
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"a", "bcd", "e"}, 4, 6));
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"f", "p", "e"}, 8, 7));


        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"hello"}, 10000, 1));
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"a", "bcd", "e"}, 5, 6));
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"try", "to", "be", "better"}, 10000, 9001));
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"h"}, 2, 3));


        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6));
        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"hello", "world"}, 2, 8));

        System.out.println(new SentenceScreenFitting().wordsTyping(new String[]{"I", "had", "apple", "pie"}, 4, 5));

    }
}

class SentenceScreenFittingFaster {
    // similar to count of repetition! here we record the position of he ss array when we start a new line.
    // note when we start a new line we are always on some ss element
    // we will visit at most ss.length rows and each of them takes n time to traverse at most
    public int wordsTyping(String[] ss, int m, int n) {
        int sn = ss.length;
        int[] start = new int[sn + 1];
        int[] count = new int[m];
        int sum = 0;
        for (int i = 0; i < sn; i++) {
            if (ss[i].length() > n) {
                return 0;
            }
            sum += (i == 0 ? 0 : 1) + ss[i].length();
        }
        Arrays.fill(start, -1);
        int k = 0;
        int cur = 0;
        // main acceleration: when we see a repetition we can bolt early
        for (int i = 0; i < m; i++) {
            if (start[k] != -1) {
                int pk = start[k];
                int loop = (m - pk) / (i - pk);
                int appear = cur - count[pk];
                int times = loop * appear;
                int stub = (m - pk) % (i - pk);
                int stublen = count[pk + stub];
                return times + stublen;
            } else {
                start[k] = i;
            }
            count[i] = cur;
            // acceleration! when n is very big we should % first
            cur += n / (sum + 1);
            int rem = n % (sum + 1);
            while (rem > 0) {
                int toadd = ss[k].length() + 1;
                if (toadd - 1 > rem) {
                    // cant even fit after throw away the space.
                    // note the elegance of processing the space here: count the tracing space. we can throw it away for the last word if needed
                    // otherwise after -toadd it's either 0 or -1, won't sustain the next one
                    break;
                }
                rem -= toadd;
                k++;
                if (k == sn) {
                    k = 0;
                    cur++;
                }
            }
        }
        return cur;
    }
}