import java.util.*;

/*
LC#1244
Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0. It is guaranteed that the player was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.



Example 1:

Input:
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output:
[null,null,null,null,null,null,73,null,null,null,141]

Explanation:
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;


Constraints:

1 <= playerId, K <= 10000
It's guaranteed that K is less than or equal to the current number of players.
1 <= score <= 100
There will be at most 1000 function calls.
 */
public class DesignLeaderboard {
    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1, 73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2, 56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3, 39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4, 51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5, 4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(1));           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2, 51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(3));           // returns 141 = 51 + 51 + 39;

    }
}


class Leaderboard {
    Map<Integer, Integer> pm = new HashMap<>();
    TreeMap<Integer, Integer> sm = new TreeMap<>(Collections.reverseOrder());

    public Leaderboard() {

    }

    public void addScore(int playerId, int score) {
        Integer oldscore = pm.get(playerId);
        int newscore = (oldscore == null ? 0 : oldscore) + score;
        pm.put(playerId, newscore);
        updateOldScore(oldscore);
        sm.put(newscore, sm.getOrDefault(newscore, 0) + 1);
    }

    private void updateOldScore(Integer oldscore) {
        if (oldscore == null) {
            return;
        }
        int oldcount = sm.get(oldscore);
        if (oldcount == 1) {
            sm.remove(oldscore);
        } else {
            sm.put(oldscore, oldcount - 1);
        }
    }

    public int top(int k) {
        int sum = 0;
        Iterator<Map.Entry<Integer, Integer>> it = sm.entrySet().iterator();
        while (k > 0 && it.hasNext()) {
            Map.Entry<Integer, Integer> cur = it.next();
            int count = cur.getValue();
            int counted = Math.min(k, count);
            sum += counted * cur.getKey();
            k -= counted;
        }
        return sum;
    }

    public void reset(int playerId) {
        Integer oldscore = pm.get(playerId);
        if (oldscore == null) {
            return;
        }
        pm.put(playerId, 0);
        updateOldScore(oldscore);
        sm.put(0, sm.getOrDefault(0, 0) + 1);
    }
}