import base.ArrayUtils;

/*
LC#765
N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.

The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).

The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

Example 1:

Input: row = [0, 2, 1, 3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
Example 2:

Input: row = [3, 2, 0, 1]
Output: 0
Explanation: All couples are already seated side by side.
Note:

len(row) is even and in the range of [4, 60].
row is guaranteed to be a permutation of 0...len(row)-1.
 */
public class CouplesHoldingHands {

    /*
    greedy!
     if row[i], row[i + 1] are not couple:
          find j > i such that row[i] and row[j] are couple
          swap row[i + 1] and row[j]
     */
    public int minSwapsCouples(int[] a) {
        int n = a.length;
        int i = 0;
        int r = 0;
        while (i + 1 < n) {
            if (a[i + 1] != (a[i] ^ 1)) {
                // i+1 bad, swap. note the trick of ^1 to get couple
                for (int j = i + 1; j < n; j++) {
                    if (a[j] == (a[i] ^ 1)) {
                        int tmp = a[i + 1];
                        a[i + 1] = a[j];
                        a[j] = tmp;
                        r++;
                        break;
                    }
                }
                i += 2;
            } else {
                i += 2;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new CouplesHoldingHands().minSwapsCouples(ArrayUtils.read1d("[1,0,3,2]")));
        System.out.println(new CouplesHoldingHands().minSwapsCouples(ArrayUtils.read1d("[11,5,10,13,4,1,3,7,8,6,12,9,0,2]")));
        System.out.println(new CouplesHoldingHands().minSwapsCouples(ArrayUtils.read1d("[9,12,2,10,11,0,13,6,4,5,3,8,1,7]")));
        System.out.println(new CouplesHoldingHands().minSwapsCouples(ArrayUtils.read1d("[10,7,4,2,3,0,9,11,1,5,6,8]")));

        System.out.println(new CouplesHoldingHands().minSwapsCouples(ArrayUtils.read1d("[0,2,4,6,7,1,3,5]")));


        System.out.println(new CouplesHoldingHands().minSwapsCouples(ArrayUtils.read1d("[5,4,2,6,3,1,0,7]")));
    }
}
