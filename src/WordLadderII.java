import java.util.*;

/*
LC#126
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

public class WordLadderII {
    // improved BFS, results are backtrack generated from a map.
// key observation is if bfs sees first some word, the step count must be the shortest
// only caveat is there could be multiple sources, each landing at current word at the same step. we need to record them
    // shortest path is usually not solved by DFS
    public List<List<String>> findLadders(String s, String e, List<String> wordList) {
        // non null
        List<List<String>> r = new ArrayList<>();
        if (s == null || e == null || s.equals(e) || wordList.isEmpty()) {
            return r;
        }
        Set<String> dict = new HashSet<>();
        for (String str : wordList) {
            dict.add(str);
        }
        Map<String, Set<String>> pre = bfs(s, e, dict);
        if (pre == null) {
            return r;
        }
        List<String> list = new ArrayList<>();
        list.add(e);
        dfs(s, e, pre, r, list);
        return r;
    }

    private Map<String, Set<String>> bfs(String s, String e, Set<String> dict) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, Set<String>> pre = new HashMap<>();
        Deque<String> q = new ArrayDeque<>();
        q.offer(s);
        dist.put(s, 0);

        while (!q.isEmpty()) {
            String top = q.poll();
            if (e.equals(top)) {
                continue;
            }
            int curDist = dist.get(top);
            int newDist = curDist + 1;
            for (int i = 0; i < top.length(); i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    StringBuilder sb = new StringBuilder(top);
                    sb.setCharAt(i, j);
                    String next = sb.toString();
                    if (dict.contains(next)) {
                        Integer nextDist = dist.get(next);
                        if (nextDist == null) {
                            dist.put(next, newDist);
                            q.offer(next);
                            pre.computeIfAbsent(next, key -> new HashSet<>()).add(top);
                        } else if (nextDist == newDist) {
                            pre.computeIfAbsent(next, key -> new HashSet<>()).add(top);
                        }
                    }
                }
            }
        }
        return pre;
    }


    // list contains up to cur
    private void dfs(String s, String cur, Map<String, Set<String>> pre, List<List<String>> r, List<String> list) {
        if (s.equals(cur)) {
            List<String> added = new ArrayList<>(list);
            Collections.reverse(added);
            r.add(added);
            return;
        }
        // what if there is no such path?
        if (pre.get(cur) == null) {
            return;
        }
        for (String next : pre.get(cur)) {
            list.add(next);
            dfs(s, next, pre, r, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        String[] wordArray = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(wordArray);
        System.out.println(new WordLadderII().findLadders("hit", "cog", wordList));
    }
}
