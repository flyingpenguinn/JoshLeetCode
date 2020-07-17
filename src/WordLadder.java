import java.util.*;

/*
LC#127
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {

    // shortest path is usually not solved by DFS, but should do bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // verify input throw if error
        Set<String> set = new HashSet<>();
        for (String w : wordList) {
            set.add(w);
        }
        Deque<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        Map<String, Integer> m = new HashMap<>();
        m.put(beginWord, 1);
        while (!q.isEmpty()) {
            // when sth is in the queue its m is also populated already
            String top = q.poll();
            int steps = m.get(top);
            if (top.equals(endWord)) {
                break;
            }
            for (int i = 0; i < top.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    StringBuilder sb = new StringBuilder(top);
                    sb.setCharAt(i, c);
                    String str = sb.toString();
                    if (set.contains(str) && !m.containsKey(str)) {
                        m.put(str, steps + 1);
                        q.offer(str);
                    }
                }
            }
        }
        return m.getOrDefault(endWord, 0);
    }

    public static void main(String[] args) {
        String[] array = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(array);
        System.out.println(new WordLadder().ladderLength("hit", "cog", wordList));
    }
}
