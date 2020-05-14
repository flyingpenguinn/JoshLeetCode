import java.util.*;

/*
LC#1434
There are n people and 40 types of hats labeled from 1 to 40.

Given a list of list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.

Return the number of ways that the n people wear different hats to each other.

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: hats = [[3,4],[4,5],[5]]
Output: 1
Explanation: There is only one way to choose hats given the conditions.
First person choose hat 3, Second person choose hat 4 and last one hat 5.
Example 2:

Input: hats = [[3,5,1],[3,5]]
Output: 4
Explanation: There are 4 ways to choose hats
(3,5), (5,3), (1,3) and (1,5)
Example 3:

Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
Output: 24
Explanation: Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.
Example 4:

Input: hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
Output: 111


Constraints:

n == hats.length
1 <= n <= 10
1 <= hats[i].length <= 40
1 <= hats[i][j] <= 40
hats[i] contains a list of unique integers.
 */
public class NumberOfWaysToWearHats {
    // 40 hats! so must have i<41, not i<40
    // each hat can give one of its recipients....  once given other hats can't give, so it's status based dp
    long Mod = 1000000007L;
    long[][] dp;

    public int numberWays(List<List<Integer>> hats) {
        Map<Integer, Set<Integer>> hm = new HashMap<>();
        int n = hats.size();
        dp = new long[41][1 << n];
        for (int i = 0; i < 41; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < hats.size(); i++) {
            for (int j = 0; j < hats.get(i).size(); j++) {
                int hat = hats.get(i).get(j);
                hm.computeIfAbsent(hat, k -> new HashSet<>()).add(i);
            }
        }
        return (int) don(0, 0, n, hm);
    }

    private long don(int i, int st, int n, Map<Integer, Set<Integer>> hm) {
        if (i == 41) {
            return st == ((1 << n) - 1) ? 1L : 0L;
        }
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        Set<Integer> man = hm.getOrDefault(i, new HashSet<>());
        long nopick = don(i + 1, st, n, hm);
        long sum = nopick;
        if (hm.containsKey(i)) {
            for (int m : man) {
                if (((st >> m) & 1) == 0) {
                    int nst = (st | (1 << m));
                    long cur = don(i + 1, nst, n, hm);
                    sum += cur;
                    sum %= Mod;
                }
            }
        }
        dp[i][st] = sum % Mod;
        return dp[i][st];
    }
}
