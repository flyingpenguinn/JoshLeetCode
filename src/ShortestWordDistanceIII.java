/*
LC#245
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3
Note:
You may assume word1 and word2 are both in the list.
 */
public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] ws, String w1, String w2) {
        if (!w1.equals(w2)) {
            return diffw(ws, w1, w2);
        } else {
            return samew(ws, w1);
        }
    }

    int samew(String[] ws, String w) {
        int last = -1;
        int min = ws.length + 1;
        for (int i = 0; i < ws.length; i++) {
            if (ws[i].equals(w)) {
                if (last != -1) {
                    int diff = i - last;
                    min = Math.min(min, diff);
                }
                last = i;
            }
        }
        return min;
    }

    int diffw(String[] ws, String w1, String w2) {
        int i1 = -1;
        int i2 = -1;
        int min = ws.length + 1;
        for (int i = 0; i < ws.length; i++) {
            if (ws[i].equals(w1)) {
                if (i2 != -1) {
                    int diff = i - i2;
                    min = Math.min(min, diff);
                }
                i1 = i;
            } else if (ws[i].equals(w2)) {
                if (i1 != -1) {
                    int diff = i - i1;
                    min = Math.min(min, diff);
                }
                i2 = i;
            }
        }
        return min;
    }
}
