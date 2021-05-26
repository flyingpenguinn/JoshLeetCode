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
        Arrays.sort(a);
        int n = a.length;
        int start = 0;
        int minv = n;
        // for min, we find a window of numbers whose gap is <=n. if it's >n, we can't "stuff" numbers in this range because it's too many holes to fill
        for(int i=0; i<n; i++){
            while(a[i] - a[start]+1>n){
                start++;
            }
            int already = i-start+1;
            if(already == n-1 && a[i] - a[start]+1 == n-1){
                // edge case: 3,4,5,6,10.
                // it's not one move but two moves: 6->8 then 10->7.
                // this only happens when there is an outlier number and the rest is well formed already. 1,3,4,5,6 is the same story
                minv = Math.min(minv, 2);
            }else{
                minv = Math.min(minv, n-already);
            }
        }
        // for max: move 0 past 1, then all the gaps between 1 and n-1. it's a[n-1] - a[1] - (n-1) gaps, plus the initial move of 0, it's a[n-1]-a[1]-n+2
        // however we dont know if moving 0 is better or moving n-1 is better, hence two choices for max to compare
        return new int[]{minv, Math.max(a[n-1] - a[1]-n+2, a[n-2]-a[0]-n+2)};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MoveStonesUntilConsecutiveII().numMovesStonesII(ArrayUtils.read1d("[7,4,9]"))));
    }
}
