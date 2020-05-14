import java.util.TreeSet;
/*
LC#667

Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

If there are multiple answers, print any of them.

Example 1:
Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
Example 2:
Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
Note:
The n and k are in the range 1 <= k < n <= 104.
 */
public class BeautifulArrangementII {
    // jump around between 1...k+1,then +1 from min= k+2
    public int[] constructArray(int n, int k) {
        int[] r= new int[n];
        int sign=1;
        r[0]=1;
        boolean[] used= new boolean[n+1];
        used[1]=true;
        int i=1;
        int oldk=k;
        while(i<n){
            if(k>0){
                r[i]= r[i-1] + k*sign;
                k--;
                sign *= -1;
                used[r[i]]=true;
                i++;
            }else{
                break;
            }
        }
        int j=oldk+2;
        while(i<n){
            r[i++] = j++;
        }
        return r;
    }
}
