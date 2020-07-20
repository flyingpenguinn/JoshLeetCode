import java.util.Arrays;

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
    // 1. find the first number i that is >i-1. if not found, sort
    // 2. find the first number from n-1 that is >i-1. may be i, may be sth after i.
    // 3. swap that number and i-1
    // 4. reverse everything from i to n-1
    public void nextPermutation(int[] a) {
        // validate input, null-> throw or return
        int n = a.length;
        int swap1 = -1; //index
        for(int i=n-1; i>=1; i--){
            if(a[i]>a[i-1]){
                swap1= i-1;
                break;
            }
        }
        if(swap1==-1){
            Arrays.sort(a);
            return;
        }
        int swap2 = -1; //index
        for(int i=n-1; i>swap1; i--){
            if(a[i] > a[swap1]){
                swap2 = i;
                break;
            }
        }
        swap(a, swap1, swap2);
        reverse(a, swap1+1, n-1);
    }

    private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void reverse(int[] a, int i, int j){
        while(i<j){
            swap(a, i++, j--);
        }
    }
}
