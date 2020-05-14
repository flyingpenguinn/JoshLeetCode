import java.util.ArrayList;
import java.util.List;

/*
LC#68
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */
public class TextJustification {

    private final String Blank = " ";

    // next space length = ceil(rem space/ rem slots) the subtract and continue
    public List<String> fullJustify(String[] w, int max) {
        List<String> r = new ArrayList<>();
        List<String> cr = new ArrayList<>();
        cr.add(w[0]);
        int clen = w[0].length();
        int cwlen = clen;
        for (int i = 1; i < w.length; i++) {
            String wi = w[i];
            int nl = clen + 1 + wi.length();
            if (nl <= max) {
                cr.add(wi);
                clen = nl;
                cwlen += wi.length();
            } else {
                String reo = reorg(cr, cwlen, max);
                r.add(reo);
                cr.clear();
                cr.add(wi);
                clen = cwlen = wi.length();
            }
        }
        String last = reorglast(cr, max);
        r.add(last);
        return r;
    }

    String pad(int c) {
        StringBuilder sb = new StringBuilder();
        while (c > 0) {
            sb.append(Blank);
            c--;
        }
        return sb.toString();
    }

    String reorg(List<String> words, int wl, int max) {
        StringBuilder r = new StringBuilder();
        int wc = words.size();
        if (wc == 1) {
            int sp = max - wl;
            r.append(words.get(0));
            r.append(pad(sp));
        } else {
            int slots = wc - 1;
            int sps = max - wl;
            for (int i = 0; i < words.size(); i++) {
                r.append(words.get(i));
                if (slots > 0) {
                    int sp = sps % slots == 0 ? sps / slots : (sps / slots + 1);// int ceil division, key here
                    r.append(pad(sp));
                    sps -= sp;
                    slots--;
                }
            }
        }
        return r.toString();
    }

    String reorglast(List<String> cur, int max) {
        StringBuilder sb = new StringBuilder();
        for (String s : cur) {
            if (sb.length() > 0) {
                sb.append(Blank);
            }
            sb.append(s);
        }
        while (sb.length() < max) {
            sb.append(Blank);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        System.out.println(tj.fullJustify(words, 16));
    }
}
