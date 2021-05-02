import java.util.Arrays;

public class MinAdjacentSwapsToReachKth {
    // first get the kth
    // then simulate the swap: to get j-->i we need j-i swaps and the effect is to insert j at ith position
    public int getMinSwaps(String s, int k) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
        }
        int[] oa = Arrays.copyOf(a, n);
        while (k > 0) {
            nextPermutation(a);
            k--;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (oa[i] != a[i]) {
                for (int j = i + 1; j < n; j++) {
                    if (oa[j] == a[i]) {
                        insert(oa, i, j, a[i]);
                        res += j - i;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private void insert(int[] a, int s, int e, int i) {
        for (int k = e; k > s; k--) {
            a[k] = a[k - 1];
        }
        a[s] = i;
    }


    public void nextPermutation(int[] a) {
        // validate input, null-> throw or return
        int n = a.length;
        int swap1 = -1; //index
        for (int i = n - 1; i >= 1; i--) {
            if (a[i] > a[i - 1]) {
                swap1 = i - 1;
                break;
            }
        }
        if (swap1 == -1) {
            Arrays.sort(a);
            return;
        }
        int swap2 = -1; //index
        for (int i = n - 1; i > swap1; i--) {
            if (a[i] > a[swap1]) {
                swap2 = i;
                break;
            }
        }
        swap(a, swap1, swap2);
        reverse(a, swap1 + 1, n - 1);
    }

    private void swap(int[] a, int i, int j) {

        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void reverse(int[] a, int i, int j) {
        while (i < j) {
            swap(a, i++, j--);
        }
    }
}
