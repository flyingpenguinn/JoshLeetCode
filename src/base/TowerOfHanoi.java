package base;

public class TowerOfHanoi {
    // n plates from a to b using c
    void hanoi(int n, int a, int b, int c) {
        if (n > 0) {
            hanoi(n - 1, a, c, b);
            System.out.println(a + "->" + b);
            hanoi(n - 1, c, b, a);
        }
    }

    public static void main(String[] args) {
        new TowerOfHanoi().hanoi(64,1,2,3);
    }
}
