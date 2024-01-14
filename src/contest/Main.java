package contest;

import java.util.*;
import java.io.*;

public class Main {


    // Driver Code
    public static void main(String[] args) {
        long u = 10000000000L;
        long k = 10000000000L;
        long res = 0;
        int x = 5;
        for (long i = 1; i <= u; ++i) {
            for(int j=x; j<=40; j += x){

                if(((i>>(j-1))&1)==1){
                    ++res;
                }
                if(res>k){
                    System.out.println(i-1);
                    return;
                }
            }
        }

    }
}


