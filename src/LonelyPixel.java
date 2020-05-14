/*
LC#531
Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input:
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].

 */

public class LonelyPixel {
    public int findLonelyPixel(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] rm = new int[m];
        int[] cm = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'B') {
                    rm[i]++;
                    cm[j]++;
                }
            }
        }
        int r = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'B' && rm[i] == 1 && cm[j] == 1) {
                    r++;
                }
            }
        }
        return r;
    }
}