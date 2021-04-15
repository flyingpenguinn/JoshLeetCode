import java.util.Arrays;

public class FaultySensor {
    public int badSensor(int[] a, int[] b) {
        if (Arrays.equals(a, b)) {
            return -1;
        }
        boolean f1 = fail(a, b);
        boolean f2 = fail(b, a);
        if (f1 && f2) {
            return -1;
        }
        if (!f1 && !f2) {
            return -1;
        }
        return f1 ? 1 : 2;
    }

    // a is the failed one? we know they are not equal
    private boolean fail(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        while (i < a.length - 1 && j < b.length) {
            if (a[i] != b[j]) {
                return same(a, i, a.length - 1, b, j + 1, b.length);
            }
            i++;
            j++;
        }
        // all same before, but last one is the faulty one. not they are not equal as we checked earlier
        return true;
    }

    private boolean same(int[] a, int ai, int an, int[] b, int bi, int bn) {
        while (ai < an && bi < bn) {
            if (a[ai++] != b[bi++]) {
                return false;
            }
        }
        return true;
    }
}
