import java.util.Arrays;

public class MaxHeightByStackingCuboids {
    // almost last min ac in mock contest...
    // note it's asking for a subset, not a subsequence. the diff is in subsequence we can in subset we dont need to conform to an order!

    // two observations:
    //  sort by smallest edge, this gives us the sequence we need to iterate. now it becomes max increasing subsequence
    //  height must be the longest edge. because if height is not the longest edge:
    // 1. if it's the top one, then we simply rotate. the area will be smaller anyway
    // 2. otheerwise it's in the middle: a1, a3, a2 => b1, b2, b3. b is put on a
    // a2 >=b3>=b2
    // a3 >=a2>=b3
    // hence we can rotate and make the whole thing taller
    public int maxHeight(int[][] a) {
        int n = a.length;
        for(int i=0; i<n; i++){
            Arrays.sort(a[i]);
        }
        // must sort by all 3 dimensions. if we only sort by first dimension then order could be reversed when first dim is the same
        Arrays.sort(a, (x,y)-> {
            if(x[0]== y[0]){
                if(x[1]!= y[1]){
                    return Integer.compare(x[1], y[1]);
                }else{
                    return Integer.compare(x[2], y[2]);
                }
            }else{
                return Integer.compare(x[0], y[0]);
            }
        });
        int[] dp = new int[n];
        int res = 0;
        for(int i=0; i<n; i++){
            dp[i] = a[i][2];
            for(int j=0; j<i; j++){
                if(a[j][1] <= a[i][1] && a[j][2]<= a[i][2]){
                    dp[i] = Math.max(dp[i], a[i][2]+dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
