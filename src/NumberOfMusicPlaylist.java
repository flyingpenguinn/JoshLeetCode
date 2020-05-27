import java.util.Arrays;

/*
LC#920
Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:

Every song is played at least once
A song can only be played again only if K other songs have been played
Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.



Example 1:

Input: N = 3, L = 3, K = 1
Output: 6
Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
Example 2:

Input: N = 2, L = 3, K = 0
Output: 6
Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
Example 3:

Input: N = 2, L = 3, K = 1
Output: 2
Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]


Note:

0 <= K < N <= L <= 100
 */
public class NumberOfMusicPlaylist {
    // two choices at each step: play an old song or play a new song
    long[][] dp;
    long MOD = 1000000007;

    public int numMusicPlaylists(int n, int l, int k) {
        dp = new long[l][n + 1];
        for (int i = 0; i < l; i++) {
            Arrays.fill(dp[i], -1);
        }
        return (int) dom(0, 0, l, n, k);
    }

    // at song i, listened j different songs in 0...i-1
    long dom(int i, int j, int l, int n, int k) {
        if (i == l) {
            return j == n ? 1 : 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        long way1 = (n > j) ? (n - j) * dom(i + 1, j + 1, l, n, k) : 0;
        long way2 = (j > k) ? (j - k) * dom(i + 1, j, l, n, k) : 0;
        long rt = way1 + way2;
        dp[i][j] = rt % MOD;
        return dp[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfMusicPlaylist().numMusicPlaylists(16, 16, 4));
        //  System.out.println(new NumberOfMusicPlaylist().numMusicPlaylists(3, 3, 1));
    }
}
