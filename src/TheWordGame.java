import java.util.Arrays;

public class TheWordGame {
    public boolean canAliceWin(String[] a, String[] b) {
        int an = a.length;
        int bn = b.length;
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        boolean bturn = true;
        System.out.println(a[0]);
        while (i < an && j < bn) {
            if (bturn) {
                j = process(b, a, j, i);
                if (j == -1) {
                    return true;
                }
                System.out.println(b[j]);
            } else {
                i = process(a, b, i, j);
                if (i == -1) {
                    return false;
                }
                System.out.println(a[i]);
            }
            bturn = !bturn;
        }
        return j == bn;
    }

    private int process(String[] a, String[] b, int i, int j) {
        int an = a.length;
        int k = i;
        while (k < an && a[k].charAt(0) <= b[j].charAt(0)) {
            ++k;
        }
        --k;
        if (k <0 || a[k].compareTo(b[j]) <= 0) {
            ++k;
            while (k < an && a[k].charAt(0) <= b[j].charAt(0) + 1) {
                ++k;
            }
            --k;

            if (a[k].compareTo(b[j]) <= 0) {
                return -1;
            }

        }
        return k;
    }


    public static void main(String[] args) {
        System.out.println(new TheWordGame().canAliceWin(new String[]{"avokado","dabar"}, new String[]{"brazil"}));
    }
}
