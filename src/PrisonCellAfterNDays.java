import java.util.Arrays;

// we found that 14 is the magic number for a new start...
public class PrisonCellAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int n) {
        n = n % 14;
        if (n == 0) {
            n = 14;
        }
        for (int j = 0; j < n; j++) {
            int im1 = cells[0];
            cells[0] = 0;
            for (int i = 1; i < 7; i++) {
                int oldi = cells[i];
                if (im1 == cells[i + 1]) {
                    cells[i] = 1;
                } else {
                    cells[i] = 0;
                }
                im1 = oldi;
            }
            cells[7] = 0;
            //  System.out.println(Arrays.toString(cells));
        }
        return cells;
    }

    public static void main(String[] args) {
        int[] cells = {1, 0, 0, 1, 0, 0, 0, 1};
        long start = System.currentTimeMillis();
        System.out.println(new PrisonCellAfterNDays().prisonAfterNDays(cells, 826));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
