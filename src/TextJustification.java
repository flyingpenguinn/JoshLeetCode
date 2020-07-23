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
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> r = new ArrayList<>();
        if(words== null || words.length==0 || maxWidth <=0){
            return r;
        }
        // assuming every line is long enough to contain all words
        int len = words[0].length();
        int wcount = 1;
        int wlen = words[0].length();

        int start = 0;
        int i = 1;
        int n = words.length;
        while(i<n){
            len += (1+words[i].length());
            wlen += words[i].length();
            wcount++;
            if(len> maxWidth){
                len -= (1+words[i].length());
                wcount--;
                wlen -= words[i].length();
                String justified = justify(words, start, i-1, wlen, wcount, maxWidth);
                r.add(justified);
                len = words[i].length();
                wcount = 1;
                wlen = words[i].length();
                start = i;
            }
            i++;
        }
        // start...i-1 is the last line
        String last = lastLine(words, start, n-1, maxWidth);
        r.add(last);
        return r;
    }

    private String pad(int num){
        StringBuilder sb = new StringBuilder();
        while(num>0){
            sb.append(" ");
            num--;
        }
        return sb.toString();
    }

    // start......end can be fit into this line
    private String justify(String[] ws, int start, int end, int wlen, int wcount, int maxWidth){
        int slots = wcount-1;
        if(slots==0){
            String padded = pad(maxWidth-wlen);
            return ws[start]+padded;
        }
        // slots,
        int spaces = maxWidth - wlen;
        StringBuilder sb = new StringBuilder();
        int i = start;
        while(spaces>0){
            sb.append(ws[i++]);
            int allocated = (int)Math.ceil(spaces*1.0/slots);
            sb.append(pad(allocated));
            spaces -= allocated;
            slots--;
        }
        sb.append(ws[end]);
        return sb.toString();
    }

    private String lastLine(String[] words, int start, int end, int maxWidth){
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<=end; i++){
            if(sb.length()>0){
                sb.append(" ");
            }
            sb.append(words[i]);
        }
        while(sb.length()<maxWidth){
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        System.out.println(tj.fullJustify(words, 16));
    }
}
