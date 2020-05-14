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
    // for each position i, p distinct songs have been picked.
    // we can either pick a new one out of n-p songs, without restriction, or pick an old one from p-k songs. here it's p-k
    // because we must wait for k songs to pick an old one. THE LAST K SONGS MUST BE DISTINCT
    // @SILU for dp counting problem, think about the restraining factors. here distinct songs is one of the restrict factor

    long[][] dp;

    long Mod = 1000000007;

    public int numMusicPlaylists(int n, int l, int k) {
        dp = new long[l][n + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return (int) don(0, 0, n, l, k);
    }

    // at ith slot, j unique songs played
    private long don(int i, int j, int n, int l, int k) {
        if (i == l) {
            return j >= n ? 1 : 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        long newsong = n - j <= 0 ? 0 : (n - j) * don(i + 1, j + 1, n, l, k);
        long oldsong = j - k <= 0 ? 0 : (j - k) * don(i + 1, j, n, l, k);
        long rt = newsong + oldsong;
        dp[i][j] = (rt % Mod);
        return dp[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfMusicPlaylist().numMusicPlaylists(16, 16, 4));
        //  System.out.println(new NumberOfMusicPlaylist().numMusicPlaylists(3, 3, 1));
    }
}
