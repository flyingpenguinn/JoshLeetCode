/*
LC#779
On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
Note:

N will be an integer in the range [1, 30].
K will be an integer in the range [1, 2^(N-1)].
 */
public class KthSymbolInGrammar {
    // cur = last + ~last. so it's either the same as last , or the flip of it, in k- cur len/2 position
    public int kthGrammar(int n, int k) {
        return dok(n, k);
    }

    int dok(int n, int k) {
        //  System.out.println(n+" "+k);
        if (n == 1) {
            return 0;
        }
        int len = 1 << (n - 1);
        if (k <= len / 2) {
            return dok(n - 1, k);
        } else {
            int rt = dok(n - 1, k - len / 2);
            return rt == 0 ? 1 : 0;
        }
    }
}
