import java.util.Arrays;
import java.util.TreeSet;
/*
LC#475
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:

Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.


Example 1:

Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.


Example 2:

Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */
public class Heaters {
    public int findRadius(int[] a, int[] b) {
        if(a.length==0){
            return 0;
        }
        Arrays.sort(a);
        Arrays.sort(b);

        int r=0;
        int i=0;
        int j=0;
        while(i<a.length){
            int cur=Integer.MAX_VALUE;
            while(j<b.length && b[j]<=a[i]){
                j++;
            }
            if(j>0){
                cur= a[i]-b[j-1];
            }
            if(j<b.length){
                cur= Math.min(cur,b[j]-a[i]);
            }
            r= Math.max(r,cur);
            i++;
        }
        return r;
    }
}


// since the criteria is easy to check...
class HeatersBinarySearchOnRadius {
    public int findRadius(int[] houses, int[] heaters) {
        int maxDist = 0;
        int j = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int i = 0; i < houses.length; i++) {
            while (j < heaters.length && houses[i] > heaters[j]) {
                j++;
            }
            int distprev = Integer.MAX_VALUE;
            if (j > 0) {
                distprev = Math.abs(houses[i] - heaters[j - 1]);
            }
            int distcur = Math.abs(houses[i] - heaters[heaters.length - 1]);
            if (j < heaters.length) {
                distcur = Math.abs(houses[i] - heaters[j]);
            }
            int curDist = Math.min(distprev, distcur);
            maxDist = Math.max(maxDist, curDist);
        }
        return maxDist;
    }
}
