/*
LC#478
Given the radius and x-y positions of the center of a circle, write a function randPoint which generates a uniform random point in the circle.

Note:

input and output values are in floating-point.
radius and x-y position of the center of the circle is passed into the class constructor.
a point on the circumference of the circle is considered to be in the circle.
randPoint returns a size 2 array containing x-position and y-position of the random point, in that order.
Example 1:

Input:
["Solution","randPoint","randPoint","randPoint"]
[[1,0,0],[],[],[]]
Output: [null,[-0.72939,-0.65505],[-0.78502,-0.28626],[-0.83119,-0.19803]]
Example 2:

Input:
["Solution","randPoint","randPoint","randPoint"]
[[10,5,-7.5],[],[],[]]
Output: [null,[11.52438,-8.33273],[2.46992,-16.21705],[11.13430,-12.42337]]

 */

import java.util.Random;

public class GenerateRandomPointInCircle {
    public static void main(String[] args) {
        Solution solution = new Solution(0.01, -73839.1, -3289891.3);
        for (int i = 0; i < 30000; i++) {
            solution.randPoint();
        }
    }
}

class Solution {
    Random ran = new Random();
    double xc;
    double yc;
    double pi = Math.PI;
    double ra;

    public Solution(double ra, double xc, double yc) {
        this.xc = xc;
        this.yc = yc;
        this.ra = ra;
    }

    public double[] randPoint() {
        // sqrt is key here otherwise the distribution concentrates on inner circles
        double ranr = Math.sqrt(ran.nextDouble()) * ra;
        double ranp = ran.nextDouble() * 2 * pi;
        double rx = ranr * Math.cos(ranp);
        double ry = ranr * Math.sin(ranp);
        return new double[]{rx + xc, ry + yc};
    }
}
