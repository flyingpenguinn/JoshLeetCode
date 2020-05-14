import java.util.Arrays;

/*
LC#1320
You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).

Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.

Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.



Example 1:

Input: word = "CAKE"
Output: 3
Explanation:
Using two fingers, one optimal way to type "CAKE" is:
Finger 1 on letter 'C' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
Finger 2 on letter 'K' -> cost = 0
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
Total distance = 3
Example 2:

Input: word = "HAPPY"
Output: 6
Explanation:
Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6
Example 3:

Input: word = "NEW"
Output: 3
Example 4:

Input: word = "YEAR"
Output: 7


Constraints:

2 <= word.length <= 300
Each word[i] is an English uppercase letter.
 */
public class MinDistToTypeWordsWithTwoFingers {
    int[][][] dp;

    public int minimumDistance(String word) {
        char[] cs = word.toCharArray();
        dp = new int[cs.length][27][27];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int cur = dom(cs, 0, i, j);
                min = Math.min(min, cur);
            }
        }
        return min;
    }

    private int dom(char[] cs, int loc, int f1, int f2) {
        if (loc == cs.length) {
            return 0;
        }
        if (dp[loc][f1][f2] != -1) {
            return dp[loc][f1][f2];
        }
        int cind = cs[loc] - 'A';
        int dist1 = dist(cind, f1);
        int way1 = dist1 + dom(cs, loc + 1, cind, f2);
        int dist2 = dist(cind, f2);
        int way2 = dist2 + dom(cs, loc + 1, f1, cind);
        int rt = Math.min(way1, way2);
        dp[loc][f1][f2] = rt;
        return rt;
    }

    private int dist(int a, int b) {
        int ax = a / 6;
        int ay = a % 6;
        int bx = b / 6;
        int by = b % 6;
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }
}
