public class NumberOfStudentsUnableToEat {
    // because student rotates, we count students and iterate sandwiches. As long as there is some student willing to eat we are good
    public int countStudents(int[] students, int[] sandwiches) {
        int n = sandwiches.length;
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            zeros += students[i] == 0 ? 1 : 0;
        }
        int ones = n - zeros;
        for (int i = 0; i < n; i++) {
            if (sandwiches[i] == 1) {
                if (ones == 0) {
                    return n - i;
                } else {
                    ones--;
                }
            } else {
                if (zeros == 0) {
                    return n - i;
                } else {
                    zeros--;
                }
            }
        }
        return 0;
    }
}
