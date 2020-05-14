/*
LC#11
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

Input: [1,8,6,2,5,4,8,3,7]
Output: 49

https://leetcode.com/problems/container-with-most-water/
 */


public class ContainerWithMostWater {
    public int maxArea(int[] h) {
        int l=0;
        int u= h.length-1;
        int max=0;
        while(l<u){
            int area= Math.min(h[l],h[u])*(u-l);
            max= Math.max(max,area);
            // move the shorter one because any line more inward on the longer side won't benefit
            // for example 1,8,6,2,5,4  for 1-4 pair, anything more inside is not helpful
            // because it's at most height =1 and width is even smaller
            if(h[l]<h[u]){
                l++;
            }else{
                // if eq either one is fine
                u--;
            }
        }
        return max;
    }
}
