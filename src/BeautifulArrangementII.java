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
    // jump around between 1...k+1,then just poll from the middle part, and the part that was originally beyond high
    public int[] constructArray(int n, int k) {
        int[] a = new int[n];
        a[0] = 1;
        int j = 1+k;
        int p = 2;
        int i = 1;
        int rem = k;
        for(; i<n && rem>0; i++){
            if(i%2==0){
                a[i] = p++;
            }else{
                a[i] = j--;
            }
            rem--;
        }
        p = 1+k+1;
        for(; i<n; i++){
            a[i] = p++;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new BeautifulArrangementII().constructArray(5, 4));
    }
}
