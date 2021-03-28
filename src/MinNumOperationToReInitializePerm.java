import java.util.Arrays;

public class MinNumOperationToReInitializePerm {
    public int reinitializePermutation(int n) {
        int[] init = new int[n];
        for (int i = 0; i < n; i++) {
            init[i] = i;
        }
        int[] arr = Arrays.copyOf(init, n);
        int steps = 0;
        while (true) {
            int[] carr = Arrays.copyOf(arr, n);
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    arr[i] = carr[i / 2];
                } else {
                    arr[i] = carr[n / 2 + (i - 1) / 2];
                }
            }
            steps++;
            if (Arrays.equals(arr, init)) {
                return steps;
            }
        }

    }
}
