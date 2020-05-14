import java.util.Arrays;
import java.util.PriorityQueue;

/*
LC#1199
You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built. A block can only be built by exactly one worker.

A worker can either split into two workers (number of workers increases by one) or build a block then go home. Both decisions cost some time.

The time cost of spliting one worker into two workers is given as an integer split. Note that if two workers split at the same time, they split in parallel so the cost would be split.

Output the minimum time needed to build all blocks.

Initially, there is only one worker.



Example 1:

Input: blocks = [1], split = 1
Output: 1
Explanation: We use 1 worker to build 1 block in 1 time unit.
Example 2:

Input: blocks = [1,2], split = 5
Output: 7
Explanation: We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.
Example 3:

Input: blocks = [1,2,3], split = 1
Output: 4
Explanation: Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
Then, use the two unassigned workers to build the first two blocks.
The cost is 1 + max(3, 1 + max(1, 2)) = 4.


Constraints:

1 <= blocks.length <= 1000
1 <= blocks[i] <= 10^5
1 <= split <= 100
 */
public class MinimumTimeToBuildBlocks {
    // huffman's tree....
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < blocks.length; i++) {
            pq.offer(blocks[i]);
        }
        while (pq.size() > 1) {
            int e1 = pq.poll();
            int e2 = pq.poll();
            // do smallest 2 in parallel
            int sp = split + Math.max(e1, e2);
            pq.offer(sp);
        }
        return pq.poll();
    }


}

class MinTimeToBuildBlocksDp {
    int[][] dp;
    public int minBuildTime(int[] b, int split) {
        Arrays.sort(b);
        int n=b.length;
        dp= new int[n][n*2];
        return dom(b,split,n-1,1);
    }
    int Max= 1000000000;

    int dom(int[] b, int c,int i, int w){

        int n=b.length;
        if(i==-1){
            return 0;
        }
        if(w<=0){
            return Max;
        }
        if(dp[i][w]!=0){
            return dp[i][w];
        }
        int r1= Math.max(b[i],dom(b,c,i-1,w-1));

        int r2= Max;
        if(i-w>=0){
            r2= dom(b,c,i,2*w)+c;
        }
        int rt= Math.min(r1,r2);
        dp[i][w]=rt;
        //  System.out.println(i+" "+w+" "+dp[i][w]);

        return rt;
    }
}

