import java.util.Arrays;

/*
LC#611
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
 */
public class ValidTriangleNumbers {
    // diff of j, k <a[i]
    public int triangleNumber(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int r = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            int k = j + 1;
            while (j < n) {
                while (k < j + 1) {
                    k++;
                }
                while (k < n && a[k] - a[j] < a[i]) {
                    k++;
                }
                // j+1...k-1 are good
                r += k - j - 1;
                j++;
            }
        }
        return r;
    }


    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4, 5, 6};
        System.out.println(new ValidTriangleNumbers().triangleNumber(nums));
    }
}

