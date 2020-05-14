
import java.util.PriorityQueue;

/*
LC#42
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
public class TrappingRainWater {

    // gist is the area of a bar is the min of max left(excluding current bar) and max right minus the current bar
    // this is actually the simplied version of queue based solution below
    // this can be further simplified to remove the arrays
    public int trap(int[] a) {
        int n = a.length;
        int[] maxleft = new int[n];
        int[] maxright = new int[n];
        // excluding oneself and monotonously increasing
        for (int i = 1; i < n; i++) {
            maxleft[i] = Math.max(maxleft[i - 1], a[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            maxright[i] = Math.max(maxright[i + 1], a[i + 1]);
        }
        int r = 0;
        // left and right border can't hold rain anyway
        for (int i = 1; i < n - 1; i++) {
            int min = Math.min(maxleft[i], maxright[i]);
            r += min > a[i] ? min - a[i] : 0;
        }
        return r;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWaterQueueBased().trap(height));
    }
}

// can be extended to 2d cases
class TrappingRainWaterQueueBased {
    class Pos {
        int x;
        int h;

        public Pos(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }

    int[] directions = {-1, 1};

    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        PriorityQueue<Pos> q = new PriorityQueue<>((a, b) -> Integer.compare(a.h, b.h));
        int n = height.length;
        boolean[] visited = new boolean[n];
        q.offer(new Pos(0, height[0]));
        q.offer(new Pos(n - 1, height[n - 1]));
        visited[0] = true;
        visited[n - 1] = true;
        int count = 2;
        int water = 0;
        while (count < n) {
            Pos top = q.poll();
            for (int d : directions) {
                int nx = top.x + d;
                if (nx >= 0 && nx < n && !visited[nx]) {
                    if (top.h > height[nx]) {
                        water += top.h - height[nx];
                    }
                    q.offer(new Pos(nx, Math.max(top.h, height[nx])));
                    visited[nx] = true;
                    count++;
                }
            }
        }
        return water;
    }
}
