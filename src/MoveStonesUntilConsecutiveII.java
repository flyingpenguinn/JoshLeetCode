import base.ArrayUtils;

import java.util.Arrays;

/*
LC#1040
On an infinite number line, the position of the i-th stone is given by stones[i].  Call a stone an endpoint stone if it has the smallest or largest position.

Each turn, you pick up an endpoint stone and move it to an unoccupied position so that it is no longer an endpoint stone.

In particular, if the stones are at say, stones = [1,2,5], you cannot move the endpoint stone at position 5, since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]



Example 1:

Input: [7,4,9]
Output: [1,2]
Explanation:
We can move 4 -> 8 for one move to finish the game.
Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
Example 2:

Input: [6,5,4,3,10]
Output: [2,3]
We can move 3 -> 8 then 10 -> 7 to finish the game.
Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
Notice we cannot move 10 -> 2 to finish the game, because that would be an illegal move.
Example 3:

Input: [100,101,104,102,103]
Output: [0,0]


Note:

3 <= stones.length <= 10^4
1 <= stones[i] <= 10^9
stones[i] have distinct values.
 */
public class MoveStonesUntilConsecutiveII {
    public int[] numMovesStonesII(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        // move leftmost to a1+1,then to le. one stone move at a time,from moving a1 to le(not moving it)
        int ls = a[1];
        int le = a[n - 1] - (n - 1);
        int left2right = le - ls + 1;
        int rs = a[n - 2];
        int re = a[0] + n - 1;
        int right2left = rs - re + 1;
        int maxmoves = Math.max(right2left, left2right);
        // window of size n,how many holes
        int end = 0;
        int minmoves = n;
        for (int i = 0; i < n; i++) {
            while (end < n && a[i] + n > a[end]) {
                end++;
            }
            // i...end-1 are nums
            int nums = end - i;
            int holes = n - nums;
            // when there is one hole still two moves:3->8,9->7 for34569
            // note 479 has holes =1 and its genuine 1
            if (holes == 1 && a[i] + n - 2 == a[end - 1]) {
                holes = 2;
            }
            minmoves = Math.min(minmoves, holes);
        }
        return new int[]{minmoves, maxmoves};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MoveStonesUntilConsecutiveII().numMovesStonesII(ArrayUtils.read1d("[7,4,9]"))));
    }
}
