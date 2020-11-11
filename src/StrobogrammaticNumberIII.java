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
    // use bigger allowed and eq allowed to filter bad numbers
    // O(5^n/2) at most
    private int[] m = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    public int strobos(long n) {
        int len = String.valueOf(n).length();
        long res = 0;
        for(int l = 1; l<=len; l++){
            res += dfs(0, l,n, "", "");
        }
        return (int)res;
    }

    private long dfs(int i, int len, long n, String p1, String p2){
        int start = i>0 || len==1? 0:1;
        long res = 0;
        if(i==len/2){
            if(len % 2==0){
                String str = p1+p2;
                if(Long.valueOf(str)<=n){
                    //     System.out.println(str);
                    res++;
                }
            }else{

                for(int j=start; j<10; j++){
                    if(m[j]==j){
                        String str = p1+j+p2;
                        if(Long.valueOf(str)<=n){
                            //     System.out.println(str);
                            res++;
                        }
                    }
                }
            }
            return res;
        }
        for(int j=start; j<10;j++){
            if(m[j]!= -1){
                res += dfs(i+1, len, n, p1+j, m[j]+p2);
            }
        }
        return res;
    }

    public int strobogrammaticInRange(String low, String high) {
        long hn = Long.valueOf(high);
        long ln = Long.valueOf(low);
        if(hn<ln){
            return 0;
        }
        return strobos(hn) - strobos(ln-1);
    }
}