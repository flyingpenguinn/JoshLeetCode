package crap;

public class Basics {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        sort3(2, 1, 3);
        sort3(3, 2, 1);
        sort3(3, 1, 2);
        sort3(1, 3, 2);
        sort3(2, 3, 1);
    }

    private static void sort3(int a, int b, int c) {
        if (b > c) {
            int tmp = b;
            b = c;
            c = tmp;
        }
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (a > c) {
            int tmp = a;
            a = c;
            c = tmp;
        }


        System.out.println(a + "," + b + "," + c);
    }
}
