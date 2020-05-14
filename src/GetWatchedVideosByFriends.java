import java.util.*;

/*
LC#1311
There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.

Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.



Example 1:



Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
Output: ["B","C"]
Explanation:
You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
Person with id = 1 -> watchedVideos = ["C"]
Person with id = 2 -> watchedVideos = ["B","C"]
The frequencies of watchedVideos by your friends are:
B -> 1
C -> 2
Example 2:



Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
Output: ["D"]
Explanation:
You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).


Constraints:

n == watchedVideos.length == friends.length
2 <= n <= 100
1 <= watchedVideos[i].length <= 100
1 <= watchedVideos[i][j].length <= 8
0 <= friends[i].length < n
0 <= friends[i][j] < n
0 <= id < n
1 <= level < n
if friends[i] contains j, then friends[j] contains i
 */
public class GetWatchedVideosByFriends {
    // get level in graph must be bfs

    public List<String> watchedVideosByFriends(List<List<String>> w, int[][] f, int id, int l) {
        Deque<int[]> q = new ArrayDeque<>();
        List<String> rl = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        q.offer(new int[]{id, 0});
        boolean[] v = new boolean[f.length];
        v[id] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int level = top[1];
            int nid = top[0];
            //    System.out.println("level "+level + ","+nid);
            if (level == l) {
                List<String> vs = w.get(nid);
                for (String s : vs) {
                    int nc = map.getOrDefault(s, 0) + 1;
                    map.put(s, nc);
                    if (nc == 1) {
                        rl.add(s);
                    }
                }
                continue;
            }
            for (int fi : f[nid]) {
                if (!v[fi]) {
                    v[fi] = true;
                    q.offer(new int[]{fi, level + 1});
                }
            }
        }
        Collections.sort(rl, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // they are from keyset
                int c1 = map.get(o1);
                int c2 = map.get(o2);

                if (c1 != c2) {
                    return Integer.compare(c1, c2);
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        return rl;
    }
}
