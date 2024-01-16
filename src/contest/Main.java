package contest;

import java.util.*;
import java.io.*;

public class Main {


    // Driver Code
    public static void main(String[] args) {
        solve(9, 1);
        solve(7, 2);
        solve(100000000, 5);
    }

    protected static void solve(int k, int x) {
        long u = 1000000000L;

        long res = 0;

        for (long i = 1; i <= u; ++i) {
            for (int j = 1; j <= 40; ++j) {
                if (j % x != 0) {
                    continue;
                }
                if (((i >> (j - 1)) & 1) == 1) {
                    ++res;
                }
                if (res > k) {
                    System.out.println(i - 1);
                    return;
                }
            }
        }
    }
}


