import java.util.Arrays;

public class DesignAtmMachines {
    private int[] values = {20, 50, 100, 200, 500};
    private long[] notes = new long[5];

    public DesignAtmMachines() {

    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; ++i) {
            notes[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        long[] old = Arrays.copyOf(notes, 5);
        long rem = amount;
        int[] res = new int[5];
        boolean bad = false;
        for (int i = 4; i >= 0 && rem > 0; --i) {
            if (notes[i] == 0) {
                continue;
            }
            if (rem < values[i]) {
                continue;
            }
            long count = Math.min(notes[i], rem / values[i]);
            res[i] = (int) count;
            notes[i] -= count;
            rem -= count * values[i];
        }
        if (rem > 0) {
            notes = old;
            return new int[]{-1};
        } else {
            return res;
        }
    }
}
