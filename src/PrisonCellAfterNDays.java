import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*
LC#957
There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)



Example 1:

Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation:
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

Example 2:

Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]


Note:

cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9
 */

public class PrisonCellAfterNDays {
    // we found that 14 is the magic number for a new start...
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

class PrisonCellsHashMap {
    public int[] prisonAfterNDays(int[] a, int n) {
        if (a == null || a.length == 0 || n < 0) {
            return new int[0];
        }
        Map<Integer, String> id2code = new HashMap<>();
        Map<String, Integer> code2id = new HashMap<>();
        int seg = 0;
        int prec = 0;
        // 256 choices in all...
        for (int i = 0; i <= 256; i++) {
            // a is the layout of day i
            if (i == n) {
                return a;
            }
            String code = toCode(a);
            if (code2id.containsKey(code)) {
                prec = code2id.get(code);
                seg = i - prec;
                break;
            } else {
                code2id.put(code, i);
                id2code.put(i, code);
            }
            a = transform(a);
        }
        int mod = (n + 1 - prec) % seg; // +1 here we have n+1 items in all if we count in 0. there are prec items that are not in the loop
        if (mod == 0) {
            mod = seg;
        }
        // now mod = 1 to seg. prec is the 0th
        return toArray(id2code.get(prec - 1 + mod));
    }

    private int[] transform(int[] a) {
        int[] na = new int[a.length];
        na[0] = 0;
        na[na.length - 1] = 0;
        for (int i = 1; i + 1 < na.length; i++) {
            if (a[i - 1] == a[i + 1]) {
                na[i] = 1;
            }
        }
        return na;
    }

    private String toCode(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            sb.append(",");
        }
        return sb.toString();
    }

    private int[] toArray(String s) {
        String[] ss = s.split(",");
        int[] r = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            r[i] = Integer.valueOf(ss[i]);
        }
        return r;
    }
}
