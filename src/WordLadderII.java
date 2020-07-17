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
// only caveat is there could be multiple sources, each landing at current word at the same step. we need to record them in the pre map
    // also note there could be no solutions!

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // verify input throw if error
        Set<String> set = new HashSet<>();
        for (String w : wordList) {
            set.add(w);
        }
        Deque<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        Map<String, Integer> m = new HashMap<>();
        Map<String, Set<String>> pre = new HashMap<>();
        m.put(beginWord, 1);
        while (!q.isEmpty()) {
            // when sth is in the queue its m is also populated already
            String top = q.poll();
            int steps = m.get(top);
            int nextStep = steps + 1;
            if (top.equals(endWord)) {
                break;
            }
            for (int i = 0; i < top.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    StringBuilder sb = new StringBuilder(top);
                    sb.setCharAt(i, c);
                    String next = sb.toString();
                    if (set.contains(next)) {
                        if (!m.containsKey(next)) {
                            // new node, setup its steps and put to queue
                            m.put(next, nextStep);
                            q.offer(next);
                            pre.computeIfAbsent(next, k -> new HashSet<>()).add(top);
                        } else if (m.get(next) == nextStep) {
                            // if same steps, setup the pre set
                            pre.computeIfAbsent(next, k -> new HashSet<>()).add(top);
                        } // otherwise just throw away arrived too late
                    }
                }
            }
        }
        List<List<String>> r = new ArrayList<>();
        dfs(endWord, beginWord, new ArrayList<>(), r, pre);
        return r;
    }

    // we are at cur and we want to grow from it to the begin word
    private void dfs(String cur, String begin, List<String> list, List<List<String>> r, Map<String, Set<String>> pre) {
        list.add(cur);
        if (cur.equals(begin)) {
            List<String> rev = new ArrayList<>(list);
            Collections.reverse(rev);
            r.add(rev);
        } else {
            Set<String> nexts = pre.getOrDefault(cur, new HashSet<>());
            for (String next : nexts) {
                dfs(next, begin, list, r, pre);
            }
        }
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        String[] wordArray = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(wordArray);
        System.out.println(new WordLadderII().findLadders("hit", "cog", wordList));
    }
}
