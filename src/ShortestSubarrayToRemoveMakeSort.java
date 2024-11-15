import java.util.TreeMap;

public class ShortestSubarrayToRemoveMakeSort {
    // for each i, what if we delete from i+1 to some end point?
    // if it's part of the end slope, nothing is needed
    // otherwise we find the ceiling of it in the "end slope"
    // we can use two pointers in finding ceiling
    public int findLengthOfShortestSubarray(int[] a) {
        int n = a.length;
        int i = n-1;
        while(i>0 && a[i]>=a[i-1]){
            --i;
        }
        // i is the last good, 0...i-1 could be removed
        int res = i;
        int j = 0;
        while(j<i && (j==0 || a[j-1] <= a[j])){
            while(i<n && a[j]>a[i]){
                ++i;
            }
            // j+1... i-1 removed
            int cur = i-j-1;
            res = Math.min(res, cur);
            ++j;
        }
        return res;
    }
}
