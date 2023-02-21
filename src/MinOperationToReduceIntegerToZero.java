public class MinOperationToReduceIntegerToZero {
    // go for the nearest pow of 2
    public int minOperations(int n) {
        if (n == 0) {
            return 0;
        }
        if ((n & (n - 1)) == 0) {
            return 1;
        }
        // System.out.println(n);
        int p1 = (int) Math.ceil(Math.log(n) / Math.log(2));
        int p2 = (int) Math.floor(Math.log(n) / Math.log(2));
        //   System.out.println(p1+" "+p2);
        int num1 = (int) Math.pow(2, p1);
        int num2 = (int) Math.pow(2, p2);
        if (num1 - n <= n - num2) {
            return 1 + minOperations(num1 - n);
        } else {
            return 1 + minOperations(n - num2);
        }
    }
}
