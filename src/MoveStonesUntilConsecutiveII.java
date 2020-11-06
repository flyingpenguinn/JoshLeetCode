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
    // mostly concerning unoccupied spots
    public int[] numMovesStonesII(int[] a) {
        Arrays.sort(a);
        return new int[]{getmin(a), getmax(a)};
    }

    private int getmax(int[] a){
        // every unoccupied spot in the big ranges can be attained once. for example 4,7,9, we know all the empty spots: 5,6 between 4,7 can be stood on once: 9=> 6, 7=>5
        // or if it's 9,12,15,18,
        int n = a.length;
        int moveleft = a[n-1]-a[1]-(n-2);
        // n-2, excluding a[0] (moving) and a[1] (subtracted), rest should be in this a[n-1]-a[1]
        int moveright = a[n-2]-a[0]-(n-2);

        return Math.max(moveleft, moveright);
    }

    private int getmin(int[] a){
        int n = a.length;
        // if already all done then 0
        if(a[n-1] == a[0]+n-1){
            return 0;
        }
        // special case: 3,4,5,6,10
        // another: 2,5,6,7,8
        // note the tricky ==2 check first these special cases!
        if(a[n-2]-a[0]==n-2){
            return a[n-1]-a[n-2]==2?1:2;
            // if diff more than one then need 2 moves:3,4,5,6,10
            // if diff==1 then we need one move: 5,6,7,8,10, 5=>9
        }
        if(a[n-1]-a[1]== n-2){
            return a[1]-a[0]==2?1:2;
        }
        int min = Integer.MAX_VALUE;
        // if no special case, check if each i should be the starting point we then know the range and see how many moves needed. all unoccupied spot is needed one move
        int j = 0;
        for(int i=0; i<n; i++){
            while(j<n && a[j]<=a[i]+n-1){
                j++;
            }
            // range is from a[i] to a[i]+n-1. n spots in all. we have j-1 to i elements in this range already
            int occu = j-i;
            int cur = n-occu;
            min = Math.min(min, cur);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MoveStonesUntilConsecutiveII().numMovesStonesII(ArrayUtils.read1d("[7,4,9]"))));
    }
}
