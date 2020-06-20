/*
LC#60
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
 */
public class PermutationSequence {
    // decide each number at ith position then reduce k and move forward
    public String getPermutation(int n, int k) {
        int blocksize = 1;
        for (int i = 1; i <= n; i++) {
            blocksize *= i;
        }
        boolean[] used = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            blocksize /= (n - i + 1);
            int passedblocks = (k - 1) / blocksize;
            // how many blocks to pass for example n==3, k==3, then we pass (3-1)/2 = 1 block, land on 2
            int cur = 1;
            k -= blocksize * passedblocks;
            for (; cur <= n; cur++) {
                if (used[cur]) {
                    continue;
                }
                if (passedblocks == 0) {
                    break;
                }
                passedblocks--;
            }
            sb.append(cur);
            used[cur] = true;
        }
        return sb.toString();
    }
}
