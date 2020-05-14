/*
LC#31
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */
public class NextPermutation {
    public void nextPermutation(int[] a) {
        int n = a.length;
        int i = n - 1;
        // find the first > i-1
        for (; i >= 1; i--) {
            if (a[i] > a[i - 1]) {
                break;
            }
        }
        if (i <= 0) {  // < to handle empty ones
            reverse(a, 0, n - 1);
            return;
        }
        // find the first >a[i-1] from n-1 right to left scan
        for (int j = n - 1; j >= i; j--) {
            // must >, swap the right most item bigger than a[i-1]
            if (a[j] > a[i - 1]) {
                swap(a, j, i - 1);
                break;
            }
        }
        // swap and reverse,effectively sort i...n-1 after the swap
        reverse(a, i, n - 1);
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    void reverse(int[] a, int i, int j) {
        while (i < j) {
            swap(a, i++, j--);
        }
    }
}
