import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
LC#368
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int[] p = new int[n];
        int[] len = new int[n];
        int maxlen = -1;
        int maxi = -1;
        for (int i = 0; i < n; i++) {
            int curlen = 1;
            int curj = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] % a[j] == 0 && len[j] + 1 >= curlen) {
                    curlen = len[j] + 1;
                    curj = j;
                }
            }
            p[i] = curj;
            len[i] = curlen;
            if (curlen > maxlen) {
                maxlen = curlen;
                maxi = i;
            }
        }
        List<Integer> r = new ArrayList<>();
        while (maxi != -1) {
            r.add(a[maxi]);
            maxi = p[maxi];
        }
        Collections.reverse(r);
        return r;
    }
}
