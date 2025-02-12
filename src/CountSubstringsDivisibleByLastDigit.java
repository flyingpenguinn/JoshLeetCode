public class CountSubstringsDivisibleByLastDigit {
    public long countSubstrings(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        long res = 0;
        long[] mod3 = new long[3];
        long[] mod9 = new long[9];
        ++mod3[0];
        ++mod9[0];
        int[] freq7 = new int[7];
        freq7[0] = 1;
        long pref7 = 0;
        int[] inv_map = {1, 5, 4, 6, 2, 3};

        long sum = 0;
        for (int i = 0; i < n; ++i) {
            int v = c[i] - '0';
            sum += v;
            pref7 = (pref7 * 10 + v) % 7;
            int rem = (i + 1) % 6;
            long norm7 = (pref7 * inv_map[rem]) % 7;
            long cur = 0;
            if (v == 0) {
                //
            } else if (v == 7) {
                cur = freq7[(int) norm7];

            } else if (v == 1 || v == 2 || v == 5) {
                cur = i + 1;
            } else if (v == 4) {
                cur = 1;
                if (i - 1 >= 0) {
                    int lastd = c[i - 1] - '0';
                    int num = lastd * 10 + v;
                    if (num % 4 == 0) {
                        cur += i;
                    }
                }
            } else if (v == 3 || v == 6) {
                long cmod = sum % 3;
                cur = mod3[(int) cmod];
            } else if (v == 8) {
                cur = 1;
                if (i - 1 >= 0) {
                    int d2 = c[i - 1] - '0';
                    int num = d2 * 10 + v;
                    if (num % 8 == 0) {
                        ++cur;
                    }
                }
                if (i - 2 >= 0) {
                    int d1 = c[i - 2] - '0';
                    int d2 = c[i - 1] - '0';
                    int num = d1 * 100 + d2 * 10 + v;
                    if (num % 8 == 0) {
                        cur += i - 1;
                    }
                }
            } else if (v == 9) {
                long cmod = sum % 9;
                cur = mod9[(int) cmod];
            }
            if (v != 0) {
                cur = Math.max(cur, 1);
            }
            //    System.out.println("My at " + v + " =" + cur);
            res += cur;
            ++mod3[(int) (sum % 3)];
            ++mod9[(int) (sum % 9)];
            ++freq7[(int) norm7];

        }
        return res;
    }
}
