import java.util.Arrays;

/*
LC#1051
Students are asked to stand in non-decreasing order of heights for an annual photo.

Return the minimum number of students that must move in order for all students to be standing in non-decreasing order of height.



Example 1:

Input: heights = [1,1,4,2,1,3]
Output: 3


Constraints:

1 <= heights.length <= 100
1 <= heights[i] <= 100
 */
public class HeightChecker {
    // sort and compare out of ordre students
    public int heightChecker(int[] h) {
        int[] copyh = Arrays.copyOf(h, h.length);
        Arrays.sort(copyh);
        int r = 0;
        for (int i = 0; i < h.length; i++) {
            if (h[i] != copyh[i]) {
                r++;
            }
        }
        return r;
    }
}
