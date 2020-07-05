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
    public List<String> fullJustify(String[] ws, int wid) {
        // check null error out if needed
        // wid must be >= any single word
        // words are non empty
        List<String> r = new ArrayList<>();
        if (ws.length == 0) {
            return r;
        }
        int rowStart = 0; // row starts from this index
        int rowLen = ws[0].length(); // always contains one word at least try to fit the next one
        int wordLen = ws[0].length();
        for (int i = 1; i < ws.length; i++) {
            String w = ws[i];
            rowLen += (1 + w.length());
            wordLen += w.length();
            if (rowLen > wid) {
                // from i can't be fit into the row
                r.add(justify(ws, rowStart, i - 1, wordLen - w.length(), wid));
                rowLen = w.length(); // can't be ==0, need to count i in
                wordLen = w.length();
                rowStart = i;
            }
        }
        r.add(lastRow(ws, wid, rowStart).toString());
        return r;
    }

    protected StringBuilder lastRow(String[] ws, int wid, int rowStart) {
        // last row just normal padding
        StringBuilder sb = new StringBuilder();
        for (int i = rowStart; i < ws.length; i++) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(ws[i]);
        }
        // need to pad last row but still need it to be full wid
        while (sb.length() < wid) {
            pad(sb, 1);
        }
        return sb;
    }

    private String justify(String[] ws, int start, int end, int wordLen, int wid) {
        int words = end - start + 1;
        int spaces = wid - wordLen;
        int spaceSlots = words - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (sb.length() > 0) {
                // won't execute this line if spaceSlots is 0 so won't /0
                int curspace = (int) (Math.ceil(1.0 * spaces / spaceSlots));
                spaces -= curspace;
                spaceSlots--;
                pad(sb, curspace);
            }
            sb.append(ws[i]);
        }
        pad(sb, spaces); // in case just one word...still need to pad
        return sb.toString();
    }

    private void pad(StringBuilder sb, int spaces) {
        while (spaces > 0) {
            sb.append(" ");
            spaces--;
        }
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        System.out.println(tj.fullJustify(words, 16));
    }
}
