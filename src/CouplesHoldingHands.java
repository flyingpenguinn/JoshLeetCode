import java.util.*;

public class CouplesHoldingHands {

    // can improve further with a position map
    public int minSwapsCouples(int[] row) {
        int swaps = 0;
        for (int index = 0; index + 1 < row.length; index += 2) {
            // don't need to check moving index+1 around! just need to check index and make index+1 good
            // for example it's 3,9,....4...8
            // the resultant array between a 3,4...9..8 swap and 8,9...4...3 swap is the same in terms of swaps
            System.out.println(Arrays.toString(row));
            if (row[index] % 2 == 0 && row[index + 1] != row[index] + 1) {
                swapLater(row, index, row[index] + 1, index + 1);
                swaps++;
            } else if (row[index] % 2 == 1 && row[index + 1] != row[index] - 1) {
                swapLater(row, index, row[index] - 1, index + 1);
                swaps++;
            }
        }
        return swaps;
    }

    private void swapLater(int[] cur, int index, int targetValue, int swapwith) {
        for (int j = index + 1; j < cur.length; j++) {
            if (cur[j] == targetValue) {
                swap(cur, j, swapwith);
            }
        }
    }

    private void swap(int[] cur, int j, int i) {
        int tmp = cur[i];
        cur[i] = cur[j];
        cur[j] = tmp;
    }

    public static void main(String[] args) {
        int[] rows = {0, 2, 1, 3};
        System.out.println(new CoupleHoldingHandsCyclicSwap().minSwapsCouples(rows));

    }
}

class CoupleHoldingHandsCyclicSwap {
    public int minSwapsCouples(int[] row) {
        int res = 0;
        int n = row.length;

        int[] couple = new int[n];
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            // meaning of couple: seat pos 2 has couple's seat at 3
            // also person 2 has couple's label as 3
            // serves both seat and person
            couple[i] = (i % 2 == 0 ? i + 1 : i - 1);
            pos[row[i]] = i;
        }

        for (int i = 1; i < n; i += 2) {
            while (i != couple[pos[couple[row[i]]]]) {
                int j = couple[pos[couple[row[i]]]];
                swap(row, i, j);
                swap(pos, row[i], row[j]);
                res++;
            }
        }

        return res;
    }


    private void swap(int[] cur, int j, int i) {
        int tmp = cur[i];
        cur[i] = cur[j];
        cur[j] = tmp;
    }

}
