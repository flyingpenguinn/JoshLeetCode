import base.ArrayUtils;

import java.util.Arrays;

public class SortArrayByMovingItemsToEmptySpace {
    // only 2 ways: move 0 to its own position, or swap a number that is misplaced with 0, then repeat
    public int sortArray(int[] a) {

        return Math.min(solve(a, 0), solve(a, 1));
    }

    private int solve(int[] a, int s) {
        // if 0 is not in the right pos, swap it with the number that should be there
        // then find the first misplaced value, swap it with 0, rinse and repeat from this number onward. any number <this number must have been placed correctly
        int n = a.length;
        int[] npos = new int[n];
        for (int i = 0; i < n; ++i) {
            npos[a[i]] = i;
        }
        int count = 0;
        int cur = 1;
        int cand = -1;
        while (cur < n) {
            // s==0, it's 0,1,2,3. s==1, it's 1,2,3,0
            if (npos[0] == s * (n - 1)) {
                while (npos[cur] == cur - s) {
                    // if s==0 it's cur. otherwise it's 1,2,3,0
                    ++cur;
                    if (cur == n) {
                        return count;
                    }
                }
                cand = cur;
            } else {
                cand = npos[0] + s;
            }
            // note cand will not be a number that we have swapped before
            int tmp = npos[cand];
            npos[cand] = npos[0];
            npos[0] = tmp;
            ++count;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SortArrayByMovingItemsToEmptySpace().sortArray(ArrayUtils.read1d("[]")));
    }
}
