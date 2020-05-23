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
        int[] r = new int[n];
        r[0] = 1;
        boolean plus = true;
        int high = 1 + k;
        int low = 2;
        int i = 1;
        int ok = k;
        while (i < n) {
            if (k == 0) {
                break;
            } else {
                if (plus) {
                    r[i] = high--;
                    plus = false;
                } else {
                    r[i] = low++;
                    plus = true;
                }
                k--;
            }
            i++;
        }
        while (low <= high) {
            r[i++] = low++;
        }
        for (int j = ok + 2; j <= n; j++) {
            r[i++] = j;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new BeautifulArrangementII().constructArray(5, 4));
    }
}
