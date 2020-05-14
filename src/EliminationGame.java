/*
LC#390
There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6
 */
public class EliminationGame {
    // similar to joseph ring! think of remaining ones as 2i-1 or 2i+1 of the original list
    public int lastRemaining(int n) {
        return dol(n, 1);
    }

    int dol(int n, int fromleft) {
        if (n == 1) {
            return 1;
        }
        if (n % 2 == 0 && fromleft == 0) {
            return 2 * dol(n / 2, fromleft ^ 1) - 1;
        } else {
            return 2 * dol(n / 2, fromleft ^ 1);
        }
    }
}
