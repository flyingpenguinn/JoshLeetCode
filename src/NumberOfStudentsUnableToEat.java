public class NumberOfStudentsUnableToEat {
    // as long as sb wants to eat the b sandwich we are fine.. otherwise we are stuck
    public int countStudents(int[] a, int[] b) {
        int n = a.length;
        int a1 = 0;
        for (int i = 0; i < n; i++) {
            a1 += a[i] == 1 ? 1 : 0;
        }
        int a0 = n - a1;

        for (int i = 0; i < n; i++) {
            if (b[i] == 1) {
                a1--;
            } else {
                a0--;
            }
            if (a0 < 0 || a1 < 0) {
                return n - i;
            }
        }
        return 0;
    }
}
