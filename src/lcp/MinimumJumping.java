package lcp;

import base.ArrayUtils;

import java.io.*;
import java.util.*;

/*
LCP09
为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。

为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。

示例 1：

输入：jump = [2, 5, 1, 1, 1, 1]

输出：3

解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。

限制：

1 <= jump.length <= 10^6
1 <= jump[i] <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumJumping {

    // a refined bfs...
    public int minJump(int[] a) {
        int n = a.length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        boolean[] v = new boolean[n];
        int[] pre = new int[n];
        int maxpending = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int dist = top[1];
            if (i >= n) {
                return dist;
            }
            if (i + a[i] >= n) {
                return dist + 1;
            }
            if (!v[i + a[i]]) {
                v[i + a[i]] = true;
                pre[i + a[i]] = i;
                q.offer(new int[]{i + a[i], dist + 1});
            }
            for (int j = maxpending; j < i; j++) {
                if (!v[j]) {
                    v[j] = true;
                    pre[j] = i;
                    q.offer(new int[]{j, dist + 1});
                }
            }
            maxpending = i + 1;
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\minjump";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();

        System.out.println(new MinimumJumping().minJump(ArrayUtils.read1d(s)));


        System.out.println(new MinimuJumpingDp().minJump(ArrayUtils.read1d("[3,7,6,1,4,3,7,8,1,2,8,5,9,8,3,2,7,5,1,1]")));
        //    System.out.println(new MinimumJumping().minJump(ArrayUtils.read1d("[4,6,10,8,3,5,3,5,7,8,6,10,3,7,3,10,7,10,10,9,1,4,7,4,8,6,9,8,8,2,7,2,4,5,4,3,3,2,2,2,3,4,4,1,1,5,6,8,1,2]")));
    }
}

class MinimuJumpingDp {
    // if we update to dp[j]<dp[i]+1, then all later js would be updated by this j already.
    // note we still need to work when it's ==: later values could be this value+1, i.le. dp[i]+2
    public int minJump(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i + a[i] >= n) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i + a[i]] + 1;
            }
            for (int j = i + 1; j < n && dp[j] >= dp[i] + 1; j++) {
                dp[j] = dp[i] + 1;
            }
        }
        return dp[0];
    }
}