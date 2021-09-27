import java.util.ArrayList;
import java.util.List;

/*
LC#248
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */
public class StrobogrammaticNumberIII {
    // O(5^n/2) at most
    // no string needed at all!
    private int res = 0;
    private int[] middles = {0, 1, 8};
    private int[] m = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    public int strobogrammaticInRange(String low, String high) {
        dfs(0, 0, 1, Long.valueOf(low), Long.valueOf(high));
        return res;
    }

    private void dfs(long first, long second, long base, long low, long high){
        for(int mi: middles){
            if(first==0 && mi==0){
                // avoid double counting 0 here and later
                continue;
            }
            long cur = first*base*10+mi*base+second;
            if(cur>=low && cur<=high){
                ++res;
            }
        }
        long cur = first*base+second;
        if(cur>=low && cur<=high){
            ++res;
        }
        if(cur>high){
            return;
        }
        for(int i=0; i<10; ++i){
            if(m[i]==-1){
                continue;
            }
            if(first==0 && i==0){
                // avoid adding 0 to 0, there is only one 0
                continue;
            }
            dfs(first*10+i, m[i]*base+second, base*10, low, high);
        }
    }
}