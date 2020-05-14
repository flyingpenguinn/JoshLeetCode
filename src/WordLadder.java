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

    // shortest path is usually not solved by DFS
    Map<String, Set<String>> wm = new HashMap<>();
    Map<String, Integer> steps = new HashMap<>(); // use this as seen

    public int ladderLength(String bw, String ew, List<String> wordList) {
        for (String w : wordList) {
            addwm(w);
        }
        Deque<String> q = new ArrayDeque<>();
        q.offer(bw);
        steps.put(bw, 1);
        while (!q.isEmpty()) {
            String top = q.poll();
            int topstep = steps.get(top);
            if (top.equals(ew)) {
                return topstep;
            }
            int nextstep = topstep + 1;
            for (int i = 0; i < top.length(); i++) {
                StringBuilder sb = new StringBuilder(top);
                sb.setCharAt(i, '_');
                for (String next : wm.getOrDefault(sb.toString(), new HashSet<>())) {
                    if (next.equals(top)) {
                        continue;
                    }
                    Integer cur = steps.get(next);
                    if (cur == null) { // never seen before
                        steps.put(next, nextstep);
                        q.offer(next);
                    }
                }
            }
        }
        return 0;
    }

    private void addwm(String w) {
        for (int i = 0; i < w.length(); i++) {
            StringBuilder sb = new StringBuilder(w);
            sb.setCharAt(i, '_');
            wm.computeIfAbsent(sb.toString(), k -> new HashSet<>()).add(w);
        }
    }

    public static void main(String[] args) {
        String[] array = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(array);
        System.out.println(new WordLadder().ladderLength("hit", "cog", wordList));
    }
}
