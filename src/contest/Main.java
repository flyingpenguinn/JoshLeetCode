package contest;

import java.util.*;
import java.io.*;

public class Main {

    // Function to find the count of greater elements
// to right of each index
    public static int[] countGreaterRight(int[] A, int lenn,
                                          int[] countGreater_right)
    {

        // Store elements of array in sorted order
        TreeMap<Integer, Integer> s = new TreeMap<Integer, Integer>();

        // Traverse the array in reverse order
        for (int i = lenn - 1; i >= 0; i--) {
            int it = s.headMap(A[i]).size();

            // Stores count of greater elements on the right of i
            countGreater_right[i] = it;

            // Insert current element
            s.put(A[i], 1);
        }
        return countGreater_right;
    }

    // Function to find the count of greater elements
// to left of each index
    public static int[] countGreaterLeft(int[] A, int lenn, int[] countGreater_left) {
        // Store elements of array in sorted order
        TreeMap<Integer, Integer> s = new TreeMap<Integer, Integer>();

        // Traverse the array in forward order
        for (int i = 0; i < lenn; i++) {
            int it = s.headMap(A[i]).size();

            // Stores count of greater elements on the left of i
            countGreater_left[i] = it;

            // Insert current element
            s.put(A[i], 1);
        }
        return countGreater_left;
    }

    // Function to find the count of operations required
// to remove all the array elements such that If
// 1st elements is smallest then remove the element
// otherwise move the element to the end of array
    public static void cntOfOperations(int N, int[] A) {
        // Store {A[i], i}
        int[][] a = new int[N][2];

        // Traverse the array
        for (int i = 0; i < N; i++) {
            // Insert {A[i], i}
            a[i][0] = A[i];
            a[i][1] = i;
        }

        // Sort the array according to elements of the array, A[]
        Arrays.sort(a, Comparator.comparingInt(o -> o[0]));

        // countGreater_right[i]: Stores count of greater elements on the right side of i
        int[] countGreater_right = new int[N];

        // countGreater_left[i]: Stores count of greater elements on the left side of i
        int[] countGreater_left = new int[N];

        // Function to fill the arrays
        countGreater_right = countGreaterRight(A, N, countGreater_right);
        countGreater_left = countGreaterLeft(A, N, countGreater_left);

        // Index of smallest element in array A[]
        int prev = a[0][1], ind = 0;

        // Stores count of greater element on left side of index i
        int count = prev;

        // Iterate over remaining elements in of a[][]
        for (int i = 1; i < N; i++) {
            // Index of next smaller element
            ind = a[i][1];

            // If ind is greater
            if (ind > prev) {
                // Update count
                count += countGreater_right[prev] - countGreater_right[ind];
            } else {
                // Update count
                count += countGreater_right[prev] + countGreater_left[ind] + 1;
            }

            // Update prev
            prev = ind;
        }

        // Print count as total number of operations
        System.out.println(count+1);
    }

    // Driver Code
    public static void main(String[] args)
    {

        // Given array
        int[] A = {1,2,3};

        // Given size
        int N = A.length;

        // Function Call
        cntOfOperations(N, A);
    }
}


