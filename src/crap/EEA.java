package crap;

import java.util.Arrays;

public class EEA {

    int[] egcd(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0};
        } else {
            int[] r = egcd(b, a % b);
            int x = r[2];
            int y = r[1];
            y -= x * (a / b);
            return new int[]{r[0], x, y};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new EEA().egcd(6,-21)));
    }
}
