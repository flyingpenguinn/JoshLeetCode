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
    public List<String> fullJustify(String[] ws, int len) {
        int n = ws.length;
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i<n){
            StringBuilder row = new StringBuilder();
            int j = i;
            int clen = 0;
            while(j<n){
                String w = ws[j];
                int cur = 0;
                if(clen > 0){
                    cur += 1;
                }
                cur += w.length();
                if(clen + cur>len){
                    break;
                }else{
                    clen += cur;
                    ++j;
                }
            }
            if(j==n){
                for(int k=i; k<n; ++k){
                    if(row.length()>0){
                        row.append(" ");
                    }
                    row.append(ws[k]);
                }
                padZero(len, row);
                res.add(row.toString());
                break;
            }
            // from i to j-1
            int words = 0;
            for(int k=i; k<=j-1; ++k){
                words += ws[k].length();
            }
            int spaces = len-words;
            int slots = j-i-1;
            for(int k=i; k<=j-1; ++k){
                if(slots == 0){
                    row.append(ws[k]);
                    padZero(len, row);
                    break;
                }else{
                    row.append(ws[k]);
                    int cs = (int)Math.ceil(spaces*1.0/slots);
                    spaces -= cs;
                    --slots;
                    while(cs>0){
                        row.append(" ");
                        --cs;
                    }
                }
            }
            res.add(row.toString());
            i = j;
        }
        return res;
    }

    protected void padZero(int len, StringBuilder row) {
        while (row.length() < len) {
            row.append(" ");
        }
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        System.out.println(tj.fullJustify(words, 16));
    }
}
