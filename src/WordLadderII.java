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

    public List<List<String>> findLadders(String bw, String ew, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Deque<String> q = new ArrayDeque<>();
        Map<String,Integer> m = new HashMap<>();
        m.put(bw, 1);
        q.offer(bw);
        Map<String, Set<String>> pre = new HashMap<>();
        while(!q.isEmpty()){
            String top = q.poll();
            if(top.equals(ew)){
                break;
            }
            int dist = m.get(top);
            StringBuilder sb = new StringBuilder(top);
            for(int i=0; i<top.length(); i++){
                char sc = top.charAt(i);
                for(char c = 'a'; c<='z'; c++){
                    if(c==sc){
                        continue;
                    }
                    sb.setCharAt(i, c);
                    String next = sb.toString();
                    if(set.contains(next)){
                        Integer cdist = m.get(next);
                        if(cdist==null){
                            m.put(next, dist+1);
                            q.offer(next);
                        }
                        // for all qualified paths we recortd the pres
                        if(cdist == null || cdist==dist+1){
                            pre.computeIfAbsent(next, k-> new HashSet<>()).add(top);
                        }
                    }
                }
                sb.setCharAt(i, sc);
            }
        }
        //  System.out.println(pre);
        List<List<String>> res = new ArrayList<>();
        dfs(ew, bw, new ArrayList<>(), res, pre);
        return res;
    }

    private void dfs(String w, String bw, List<String> cur, List<List<String>> res, Map<String, Set<String>> pre){
        //   System.out.println(cur);
        cur.add(w);
        if(w.equals(bw)){
            List<String> rcur = new ArrayList<>(cur);
            // make begin word appear first
            Collections.reverse(rcur);
            res.add(rcur);
            cur.remove(cur.size()-1);
            return;
        }
        for(String next: pre.getOrDefault(w, new HashSet<>())){
            //System.out.println(w+"->"+next);
            dfs(next, bw, cur, res, pre);
        }
        cur.remove(cur.size()-1);
    }

    public static void main(String[] args) {
        String[] wordArray = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(wordArray);
        System.out.println(new WordLadderII().findLadders("hit", "cog", wordList));
    }
}
