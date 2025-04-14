import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestPalindromicRearrangementII {
    // gist is use prime technique to count
    // gist is use prime technique to count
    private static final int lim = 5001;
    private static boolean[] isprime = new boolean[lim];
    private static int[] primes;
    private static int psize = 0;
    private static int[][] primeexp;
    private static int[][] factorprimeexp;

    private static void initprimes() {
        Arrays.fill(isprime, true);
        isprime[1] = false;
        for (int i = 2; i < lim; ++i) {
            if (!isprime[i]) {
                continue;
            }
            for (int j = i * 2; j < lim; j += i) {
                isprime[j] = false;
            }
        }
        for (int i = 2; i < lim; ++i) {
            if (isprime[i]) {
                ++psize;
            }
        }
        primes = new int[psize];
        int pi = 0;
        for (int i = 2; i < lim; ++i) {
            if (isprime[i]) {
                primes[pi++] = i;
            }
        }
    }

    private static void initprimeexp() {
        initprimes();
        primeexp = new int[lim][psize];
        for (int i = 2; i < lim; ++i) {
            int cv = i;
            for (int j = 0; j < psize; ++j) {
                while (cv % primes[j] == 0) {
                    ++primeexp[i][j];
                    cv /= primes[j];
                }
            }
        }
        factorprimeexp = new int[lim][psize];
        for (int i = 2; i < lim; ++i) {
            factorprimeexp[i] = Arrays.copyOf(factorprimeexp[i - 1], psize);
            for (int j = 0; j < psize; ++j) {
                factorprimeexp[i][j] += primeexp[i][j];
            }
        }
    }

    private long BIG = (long) 2e6;

    private void minus(int[] a, int[] b) {
        for (int i = 0; i < psize; ++i) {
            a[i] -= b[i];
        }
    }

    private void plus(int[] a, int[] b) {
        for (int i = 0; i < psize; ++i) {
            a[i] += b[i];
        }
    }

    private long eval(int[] a) {
        long res = 1;
        for (int i = 0; i < psize; ++i) {
            if (a[i] == 0) {
                continue;
            }
            int times = a[i];
            while (times > 0) {
                res *= primes[i];
                if (res > BIG) {
                    return BIG;
                }
                --times;
            }
        }
        return res;
    }

    public String smallestPalindrome(String s, int k) {
        if (factorprimeexp == null) {
            initprimeexp();
        }
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            ++cnt[cind];
        }
        String mid = "";

        int halflen = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] % 2 == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
            cnt[i] /= 2;

            halflen += cnt[i];
        }
        int[] allfact = Arrays.copyOf(factorprimeexp[halflen], psize);
        for (int i = 0; i < 26; ++i) {
            minus(allfact, factorprimeexp[cnt[i]]);
        }
        long allcombi = eval(allfact);
        if (allcombi < k) {
            return "";
        }
        long rem = k;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < halflen; ++i) {
            int remtotal = halflen - 1 - i;
            for (int j = 0; j < 26; ++j) {

                if (cnt[j] == 0) {
                    continue;
                }
                int[] ptotal = Arrays.copyOf(factorprimeexp[remtotal], psize);
                --cnt[j];
                for (int p = 0; p < 26; ++p) {
                    if(cnt[p]==0){
                        continue;
                    }
                    minus(ptotal, factorprimeexp[cnt[p]]);
                }
                final long curcombi = eval(ptotal);
                if (curcombi >= rem) {
                    sb.append((char) ('a' + j));
                    break;
                } else {
                    ++cnt[j];
                    rem -= curcombi;
                }
            }

        }
        StringBuilder othersb = new StringBuilder(sb).reverse();
        return sb.toString() + mid + othersb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzzzzzzyyxxwwvvuuttssrrqqppoonnmmllkkjjiihhggffeeddccbbaa", 1000000));
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("ommjuujmmo", 49));
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("abba", 2));
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("smxggxms", 17));
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("yfwwfy", 6));
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("nyggyn", 4));

        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("aa", 2));
        System.out.println(new SmallestPalindromicRearrangementII().smallestPalindrome("bacab", 1));
    }


}
