public class MaxHeightOfTriangle {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(solve(red, blue, 0, 1), solve(red, blue, 1, 1));
    }

    private int solve(int r, int b, int isr, int t) {
        // System.out.println(r+" "+b+" "+isr+" "+t);
        if (isr == 1) {
            if (r < t) {
                return 0;
            } else {
                r -= t;
                return 1 + solve(r, b, isr ^ 1, t + 1);
            }
        } else {
            if (b < t) {
                return 0;
            } else {
                b -= t;
                return 1 + solve(r, b, isr ^ 1, t + 1);
            }
        }
    }
}
