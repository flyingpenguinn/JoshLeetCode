import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#243
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {


    // chasing algo
    public int shortestDistance(String[] ws, String w1, String w2) {
        int p1 = -1;
        int p2 = -1;
        int min = ws.length + 1;
        for (int i = 0; i < ws.length; i++) {
            if (ws[i].equals(w1)) {
                if (p2 != -1) {
                    min = Math.min(i - p2, min);
                }
                p1 = i;
            } else if (ws[i].equals(w2)) {
                if (p1 != -1) {
                    min = Math.min(i - p1, min);
                }
                p2 = i;
            }
        }
        return min;
    }

}
