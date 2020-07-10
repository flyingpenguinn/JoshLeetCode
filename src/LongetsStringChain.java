import java.util.*;

/*
LC#1048
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.



Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".


Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
 */
public class LongetsStringChain {
    // n*l*l
    // use the fact that we can get a previous match in l^2 time rather than n*l time
    // sort by len
    // can remove one word to reach the previous one? if so, has an edge
    public int longestStrChain(String[] ws) {
        if(ws==null || ws.length==0){
            return 0;
        }
        // n*lgn*l+n*l^2
        // if we look forward and compare it's nlgn*l+n^2*l
        Arrays.sort(ws, (x,y) -> Integer.compare(x.length(), y.length()));
        Map<String, Integer> map = new HashMap<>(); // string to max chain ending at this string
        int max = 1;
        for(int i=0; i<ws.length;i++){
            String cur = ws[i];
            if(map.containsKey(cur)){
                continue;
            }
            int curMax = 1;
            for(int j=0; j<cur.length(); j++){
                StringBuilder sb = new StringBuilder(cur);
                sb.deleteCharAt(j);
                int curCount = map.getOrDefault(sb.toString(),0)+1;
                curMax = Math.max(curMax, curCount);
            }
            map.put(cur, curMax);
            max = Math.max(max, curMax);
        }
        return max;
    }
}