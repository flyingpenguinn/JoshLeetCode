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
    Map<String, Set<String>> wm = new HashMap<>();
    Map<String, Set<String>> pred = new HashMap<>();
    Map<String, Integer> steps = new HashMap<>(); // use this as seen
    List<List<String>> r = new ArrayList<>();

    public List<List<String>> findLadders(String bw, String ew, List<String> wordList) {
        for (String w : wordList) {
            addwm(w);
        }
        Deque<String> q = new ArrayDeque<>();
        q.offerFirst(bw);
        steps.put(bw, 0);
        while (!q.isEmpty()) {
            String top = q.poll();
            if (top.equals(ew)) {
                continue;
            }
            int topstep = steps.get(top);
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
                        pred.computeIfAbsent(next, k -> new HashSet<>()).add(top);
                        q.offer(next);
                    } else if (cur == nextstep) {
                        // a-> b->c->d, a->e->c->d. we need to record 2 routes a-b-c and a-e-c.
                        // so for c need to record both b and e. no need to expand in q again
                        pred.computeIfAbsent(next, k -> new HashSet<>()).add(top);
                    }// throw away if cur <. cur won't >
                }
            }
        }
        dfs(ew, bw, new ArrayList<>());
        return r;
    }

    private void dfs(String ew, String bw, List<String> cur) {
        cur.add(ew);
        if (ew.equals(bw)) {
            ArrayList<String> copied = new ArrayList<>(cur);
            Collections.reverse(copied);
            r.add(copied);
        } else {
            for (String p : pred.getOrDefault(ew, new HashSet<>())) {
                dfs(p, bw, cur);
            }
        }
        cur.remove(cur.size() - 1); // dont remember to remove from cur when ew = bw
    }

    private void addwm(String w) {
        for (int i = 0; i < w.length(); i++) {
            StringBuilder sb = new StringBuilder(w);
            sb.setCharAt(i, '_');
            wm.computeIfAbsent(sb.toString(), k -> new HashSet<>()).add(w);
        }
    }

    public static void main(String[] args) {
        String[] wordArray = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(wordArray);
        System.out.println(new WordLadderII().findLadders("hit", "cog", wordList));
    }
}
