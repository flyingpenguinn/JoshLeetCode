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

class MinNumOfOperationsOn {
    public int reinitializePermutation(int n) {
        // focus on one index, check how many steps
        int mid = n / 2;
        // after first pemutation, 1 is at n/2 position
        int x = n / 2;
        // it takes one operation to get 1 at n/2 position
        int cnt = 1;
        // apply formula until 1 is not back at it's original positions, i.e 1
        while (x != 1) {
            if (x % 2 == 1) {
                x = mid + x / 2;
            } else {
                x /= 2;
            }
            cnt++;
        }
        return cnt;
    }
}
