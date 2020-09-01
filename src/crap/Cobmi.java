package crap;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;


public class Cobmi {

    static void prob(int n) {
        double res = 0;
        for (int i = 0; i <= n; i++) {
            res += Math.pow(5.0 / 6.0, i) * i;
        }

        System.out.println(res);
    }

    static BigInteger factorial(int n) {

        BigInteger f = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }

    static int c(int n, int p) {
        BigInteger b1 = factorial(n);
        BigInteger b2 = factorial(p);
        BigInteger b3 = factorial(n - p);
        return b1.divide(b2.multiply(b3)).intValue();
    }

    static int p(int n) {
        return n == 0 ? 1 : n * p(n - 1);
    }

    static BigInteger p(int n, int p) {
        BigInteger b1 = factorial(n);
        BigInteger b3 = factorial(n - p);
        return b1.divide(b3);
    }

    static BigInteger sum(BigInteger... bi) {
        BigInteger r = BigInteger.ZERO;
        for (BigInteger b : bi) {
            r = r.add(b);
        }
        return r;
    }

    static BigInteger sub(BigInteger b1, BigInteger b2) {
        return b1.subtract(b2);
    }

    static BigInteger multi(BigInteger... bi) {
        BigInteger r = BigInteger.ONE;
        for (BigInteger b : bi) {
            r = r.multiply(b);
        }
        return r;
    }

    static int fivedigits() {
        int r = 0;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r112 = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    for (int s = 0; s <= 9; s++) {
                        String num = i + "" + j + "6" + k + "" + s;
                        int nn = Integer.valueOf(num);
                        if (nn % 3 == 0) {
                            r++;
                            if (i % 3 == 0) {
                                r3++;
                            } else if (i % 3 == 1) {
                                r1++;
                                if (j % 3 == 2 || k % 3 == 2 || s % 3 == 2) {
                                    System.out.println(nn);
                                }
                            } else {
                                r2++;
                            }
                        }

                    }
                }
            }
        }
        System.out.println(r1 + " " + r2 + " " + r3 + " " + r112);
        return r;
    }

    static int f(int n, int t, List<Integer> list) {
        if (t == 0) {
            System.out.println(list);
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        int r = 0;
        for (int i = 0; i <= t; i++) {
            list.add(i);
            r += f(n - 1, t - i, list);
            list.remove(list.size() - 1);
        }
        return r;
    }

    static Map<String, Long> dp = new HashMap<>();

    static long f12(int[] cs, int rem, int start) {
        if (rem == 0) {
            return 1;
        }
        String code = code(cs, start);
        if (dp.containsKey(code)) {
            return dp.get(code);
        }
        long r = 0;
        for (int i = 1; i <= 4; i++) {
            if (cs[i] > 0) {
                if (rem == 1 && i == start) {
                    continue;
                }
                cs[i] -= 1;
                r += f12(cs, rem - 1, start == -1 ? i : start);
                cs[i] += 1;
            }
        }
        dp.put(code, r);
        return r;
    }

    private static String code(int[] cs, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            sb.append(cs[i]);
            sb.append(",");
        }
        sb.append(start);
        return sb.toString();
    }

    static long f15(int i, int gap, int finalgap, int labs, int days) {
        if (labs == 0) {
            return 1;
        }
        // as long as we make sure i is always valid...
        if (days - i < finalgap) {
            return 0L;
        }
        long pick = f15(i + gap + 1, gap, finalgap, labs - 1, days);
        long nopick = f15(i + 1, gap, finalgap, labs, days);
        return pick + nopick;
    }

    static long f18(int i, int n, int cur, int limit, int start) {
        if (i == 6) {
            return cur <= limit ? 1 : 0;
        }
        if (cur > limit) {
            return 0;
        }
        long r = 0;
        for (int j = start; j <= 9; j++) {
            r += f18(i + 1, n, cur + j, limit, 0);
        }
        return r;
    }

    static int f25(int n) {
        int r = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (good(i, n)) {
                r++;
            }
        }
        return r;
    }

    private static boolean good(int st, int n) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int bit = ((st >> i) & 1);
            if (bit == 1) {
                set.add(i + 1);
            }
        }
        for (int k1 : set) {
            for (int k2 : set) {
                if (k1 != k2) {
                    if ((k1 + k2) % 3 == 0) {
                        return false;
                    }
                }
            }
        }
        System.out.println("good " + set);
        return true;
    }

    static int split(int n) {
        if (n <= 2) {
            return 1;
        }
        int max = -1;
        int maxi = -1;
        for (int i = 1; i <= n / 2; i++) {
            int cur = split(i) + i * (n - 1);
            if (cur > max) {
                max = cur;
                maxi = i;
            }
        }
        return max;
    }

    static int count(int n) {
        int max = 0;
        int[] sol = new int[3];
        for (int i = 0; i <= n; i++) {
            for (int j = i; j + i <= n; j++) {
                int k = n - i - j;
                int cur = p(n) / (p(i) * p(j) * p(k));
                if (cur > max) {
                    max = cur;
                    sol[0] = i;
                    sol[1] = j;
                    sol[2] = k;
                }
            }
        }
        System.out.println(Arrays.toString(sol));
        return max;
    }

    static int f10(int n) {
        int r = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) > n / 2) {
                r++;
            }
        }
        return r;
    }

    static void fspoke(int x, int k) {
        HashSet<List<Integer>> seen = new HashSet<>();
        dfs(0, x, k, new ArrayDeque<>(), seen);
        System.out.println(seen.size());
        System.out.println(seen);
    }

    private static void dfs(int i, int x, int k, Deque<Integer> picked, Set<List<Integer>> seen) {
        if (i == k) {
            Deque<Integer> cpi = new ArrayDeque<>(picked);
            for (int j = 0; j < picked.size(); j++) {
                cpi.offerLast(cpi.pollFirst());
                List<Integer> rp = new ArrayList<>(cpi);
                Collections.reverse(rp);
                if (seen.contains(rp)) {
                    return;
                }
            }
            List<Integer> rp = new ArrayList<>(picked);
            Collections.reverse(rp);
            seen.add(rp);
            return;
        }
        for (int j = 1; j <= x; j++) {
            picked.offerLast(j);
            dfs(i + 1, x, k, picked, seen);
            picked.pollLast();
        }
    }

    static double f26(double x) {
        double r = 0.0;
        for (int i = 1; i <= 100; i++) {
            r += i * Math.pow(x, i - 1);
        }
        return r;
    }

    static int stirling2(int n, int k) {
        if (k == 1 || k == n) {
            return 1;
        }
        return stirling2(n - 1, k - 1) + k * stirling2(n - 1, k);
    }


    static int stirling1(int n, int k) {
        if (n == 0 && k == 0) {
            return 1;
        }
        if (k == 0 || n < k) {
            return 0;
        }
        return stirling1(n - 1, k - 1) + (n - 1) * stirling1(n - 1, k);
    }

    static List<List<Integer>> pf(int n, int i) {
        List<List<Integer>> r = new ArrayList<>();
        if (n >= i) {
            // must add this!
            ArrayList<Integer> single = new ArrayList<>();
            single.add(n);
            r.add(single);
        }
        for (int j = i; j <= n - 1; j++) {
            List<List<Integer>> later = pf(n - j, j);
            for (List<Integer> li : later) {
                li.add(j);
                r.add(li);
            }
        }
        return r;
    }

    static void dfs(Set<Integer> rem, List<Set<Integer>> sets, int[] limit, Set<List<Set<Integer>>> r) {
        if (rem.isEmpty()) {
            List<Set<Integer>> cur = new ArrayList<>();
            for (int i = 0; i < sets.size(); i++) {
                cur.add(new HashSet<>(sets.get(i)));
            }
            r.add(cur);
            return;
        }
        Set<Integer> crem = new HashSet<>(rem);
        for (int ri : crem) {
            rem.remove(ri);
            for (int i = 0; i < sets.size(); i++) {
                Set<Integer> set = sets.get(i);
                if (set.size() < limit[i]) {
                    sets.get(i).add(ri);
                    dfs(rem, sets, limit, r);
                    sets.get(i).remove(ri);
                }
            }
            rem.add(ri);
        }
    }

    static void f4() {
        int[] domain = {1, 1, 2, 4, 8, 16};
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < (1 << domain.length); i++) {
            int sum = 0;
            for (int j = 0; j < 32; j++) {
                if (((i >> j) & 1) == 1) {
                    sum += domain[j];
                }
            }
            set.add(sum);
        }
        System.out.println(set);
        System.out.println(set.size());
    }


    static void f5(int[] a, int[] c, int i, int cur, Set<Integer> set) {
        int n = c.length;
        if (i == n) {
            if (!set.contains(cur)) {
                set.add(cur);
            } else {
                System.out.println(cur + " is in set already");
            }
            return;
        }
        f5(a, c, i + 1, cur, set);
        for (int j = 1; j <= c[i]; j++) {
            cur += a[i];
            f5(a, c, i + 1, cur, set);
        }
    }

    static void f7(int i, int n, int colorsize, int textsize, Set<String> rem, TreeSet<String> selected, Set<String> r) {
        if (i == n) {
            if (setgood(selected, colorsize, textsize)) {
                StringBuilder sb = new StringBuilder();
                for (String si : selected) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(si);
                }
                r.add(sb.toString());
            }
            return;
        }
        Set<String> crem = new HashSet<>(rem);
        for (String re : crem) {
            rem.remove(re);
            selected.add(re);
            f7(i + 1, n, colorsize, textsize, rem, selected, r);
            selected.remove(re);
            rem.add(re);
        }
    }

    private static boolean setgood(Set<String> selected, int colorsize, int textsize) {
        Set<Character> colors = new HashSet<>();
        Set<String> texts = new HashSet<>();
        for (String s : selected) {
            colors.add(s.charAt(0));
            texts.add(s.substring(1));
        }
        return colors.size() == colorsize && texts.size() == textsize;
    }


    public static void main(String[] args) {
        prob(1000000);
    }

    protected static int f2(int n) {
        return 2 * c(n, 3) + (c(n, 2) * c(n - 2, 2)) / 2;
    }
}
