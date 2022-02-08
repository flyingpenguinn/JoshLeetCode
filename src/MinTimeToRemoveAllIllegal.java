import java.util.ArrayList;
import java.util.List;

public class MinTimeToRemoveAllIllegal {
  // meet in the middle! iterate and calc the best from left and from right values
    public int minimumTime(String s) {
        int n = s.length();
        int l[] = new int[n];
        int r[] = new int[n+1];
        if(s.charAt(0)=='1') l[0]=1;
        if(s.charAt(n-1)=='1') r[n-1]=1;
        for(int i = 1; i<n; i++) {
            if(s.charAt(i)=='1') {
                l[i] = Math.min(l[i - 1] + 2, i + 1);
            }else{
                l[i] = l[i-1];
            }
        }
        for(int i = n-2; i>=0; i--) {
            if(s.charAt(i)=='1') {
                r[i] = Math.min(r[i + 1] + 2, n - i);
            }else{
                r[i] = r[i+1];
            }
        }
        int ans = Math.min(l[n-1], r[0]);
        for(int i = 0; i<n; i++) {
            if(s.charAt(i)=='0'){
                continue;
            }
            // we iterate from left till i, then iterate from right till here
            ans = Math.min(ans, l[i] + r[i+1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MinTimeToRemoveAllIllegal().minimumTime("000001"));
      //  System.out.println(new MinTimeToRemoveAllIllegal().minimumTime("110100"));
    }
}
