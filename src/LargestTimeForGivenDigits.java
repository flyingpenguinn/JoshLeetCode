import base.ArrayUtils;

/*
LC#949
Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.



Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""


Note:

A.length == 4
0 <= A[i] <= 9
 */
public class LargestTimeForGivenDigits {
    // turn time to minutes, chck all permutations
    private String maxTime = "";
    private int maxTimeValue = -1;

    public String largestTimeFromDigits(int[] a) {
        boolean[] used = new boolean[4];
        dfs(a, 0, new StringBuilder(), used);
        return maxTime;
    }

    private void dfs(int[] a, int i, StringBuilder sb, boolean[] used) {
        if (i == 2) {
            sb.append(":");
            dfs(a, i + 1, sb, used);
            sb.setLength(sb.length() - 1); // still need to set it back!
            return;
        }
        if (i == 5) {
            int hour = Integer.valueOf(sb.substring(0, 2));
            int min = Integer.valueOf(sb.substring(3, 5));
            int value = hour * 60 + min;
            if (hour < 24 && min < 60 && value > maxTimeValue) {
                maxTimeValue = value;
                maxTime = sb.toString();
            }
            return;
        }
        for (int j = 0; j < 4; j++) {
            if (!used[j]) {
                used[j] = true;
                sb.append(a[j]);
                dfs(a, i + 1, sb, used);
                sb.setLength(sb.length() - 1);
                used[j] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("5,5,5,5")));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("0,0,0,3")));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("1,2,3,4")));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(ArrayUtils.read1d("2,0,6,6")));

    }
}

class LargestTimeIterative {
    // must filter same index case!
    public String largestTimeFromDigits(int[] a) {
        String max = "";
        int maxValue = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    continue;
                }
                int hour = 10 * a[i] + a[j];
                if (hour >= 24) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    if (j == k || i == k) {
                        continue;
                    }
                    int l = 6 - i - j - k;
                    // i~l are 0,1,2,3 permutation. so the last one must be the remaining numbe
                    // if i, j, k are different then l is guranteed to be different
                    int min = 10 * a[k] + a[l];
                    if (min >= 60) {
                        continue;
                    }
                    int value = hour * 60 + min;
                    if (value > maxValue) {
                        maxValue = value;
                        max = "" + a[i] + a[j] + ":" + a[k] + a[l];
                    }
                }
            }
        }
        return max;
    }
}