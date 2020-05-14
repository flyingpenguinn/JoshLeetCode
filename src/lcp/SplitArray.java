package lcp;

import base.ArrayUtils;

import java.io.*;
import java.util.*;

public class SplitArray {
    static boolean[] isprime = null;
    static Set<Integer> primeset = new HashSet<>();
    static List<Integer> primelist = new ArrayList<>();
    int maxprime = 0;

    public int splitArray(int[] a) {
        int n = a.length;
        if (isprime == null) {
            isprime = new boolean[1000001];
            Arrays.fill(isprime, true);
            isprime[0] = false;
            isprime[1] = false;
            getprimes();
        }
        // System.out.println("got primes");
        Set<Integer>[] sp = new HashSet[n];
        for (int i = 0; i < n; i++) {
            splitprime(sp, a, i);
        }
        // System.out.println("split");
        int[] map = new int[maxprime + 1];
        Arrays.fill(map, -1);
        int[] dp = new int[n + 1];
        dp[n] = 0;
        //   System.out.println("split");
        for (int i = n - 1; i >= 0; i--) {
            Set<Integer> primes = sp[i];
            for (int p : primes) {
                int cv = map[p];
                if (cv == -1 || dp[i + 1] < cv) {
                    map[p] = dp[i + 1];
                }
            }

            int min = dp[i + 1];
            for (int p : primes) {
                if (map[p] != -1) {
                    min = Math.min(min, map[p]);
                }
            }

            dp[i] = 1 + min;
        }
        return dp[0];
    }

    private void splitprime(Set<Integer>[] sp, int[] a, int i) {
        int v = a[i];
        sp[i] = new HashSet<>();
        if (primeset.contains(v)) {
            sp[i].add(v);
            maxprime = Math.max(maxprime, v);
            return;
        }
        for (int p : primelist) {
            if (v % p == 0) {
                while (v % p == 0) {
                    v /= p;
                }
                sp[i].add(p);
                maxprime = Math.max(maxprime, p);
            } else if (v < p) {
                break;
            }
        }
    }

    private void getprimes() {
        int lim = isprime.length;
        for (long i = 2; i < lim; i++) {
            if (!isprime[(int) i]) {
                continue;
            }
            for (long j = i * i; j < lim; j += i) {
                isprime[(int) j] = false;
            }
        }
        for (int i = 0; i < isprime.length; i++) {
            if (isprime[i]) {
                primelist.add(i);
                primeset.add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\splitarray";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();

        System.out.println(new SplitArray().splitArray(ArrayUtils.read1d(s)));
        System.out.println(new SplitArray().splitArray(ArrayUtils.read1d("2,3,3,2,3,3")));
        System.out.println(new SplitArray().splitArray(ArrayUtils.read1d("[326614, 489921]")));
    }
}
