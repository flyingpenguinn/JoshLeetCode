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
    // at each i, count how many blocks can we get by k. block= (n-i-1)!
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        return dog(n, 0, k, used, factor(n));
    }

    int factor(int n) {
        return n == 0 ? 1 : factor(n - 1) * n;
    }

    String dog(int n, int i, int k, boolean[] used, int blocksize) {
        if (i == n) {
            return "";
        }
        blocksize /= (n - i);
        // how many full blocks. note k from 1
        int blocks = (k - 1) / blocksize;
        //   System.out.println("k="+k+" blocksize= "+blocksize +" ith= "+ith);
        int rem = blocks;
        for (int j = 1; j <= n; j++) {
            if (used[j]) {
                continue;
            }
            if (rem == 0) {
                used[j] = true;
                return String.valueOf(j) + dog(n, i + 1, k - (blocks) * blocksize, used, blocksize);
            } else {
                rem--;
            }
        }
        return "err";
    }
}
