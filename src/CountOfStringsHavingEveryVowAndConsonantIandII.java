public class CountOfStringsHavingEveryVowAndConsonantIandII {
    // two pointers!
    private String vow = "aeiou";

    public long countOfSubstrings(String s, int k) {
        int n = s.length();
        int[] vm = new int[vow.length()];
        int[] nc = new int[n + 1];
        nc[n] = n;
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if (vow.indexOf(c) == -1) {
                nc[i] = i;
            } else {
                nc[i] = nc[i + 1];
            }
        }
        int j = 0;
        int cons = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int vi = vow.indexOf(c);
            if (vi != -1) {
                ++vm[vi];
            } else {
                ++cons;
            }
            while (cons > k) {
                char jc = s.charAt(j);
                int vji = vow.indexOf(jc);
                if (vji != -1) {
                    --vm[vji];
                } else {
                    --cons;
                }
                ++j;
            }
            while (good(vm) && cons == k) {
                res += 0L + nc[i + 1] - i;
                char jc = s.charAt(j);
                int vji = vow.indexOf(jc);
                if (vji != -1) {
                    --vm[vji];
                } else {
                    --cons;
                }
                ++j;
            }
        }

        return res;
    }

    private boolean good(int[] vm) {
        for (int i = 0; i < vm.length; ++i) {
            if (vm[i] <= 0) {
                return false;
            }
        }
        return true;
    }
}

class CountOfSubstringVowelAndConsonantAlternative {
    // exactly k == solve(k) - solve(k+1)
    private String vow = "aeiou";

    public long countOfSubstrings(String s, int k) {
        long v1 = solve(s, k);
        long v2 = solve(s, k + 1);
        return v1 - v2;
    }

    private long solve(String s, int k) {
        int n = s.length();
        int[] vm = new int[vow.length()];
        int j = 0;
        int cons = 0;
        long res = 0;

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int vi = vow.indexOf(c);
            if (vi != -1) {
                ++vm[vi];
            } else {
                ++cons;
            }
            while (good(vm) && cons >= k) {
                long rem = 0L + n - i;
                res += rem;
                char jc = s.charAt(j);
                int vji = vow.indexOf(jc);
                if (vji != -1) {
                    --vm[vji];
                } else {
                    --cons;
                }
                ++j;
            }
        }

        return res;
    }

    private boolean good(int[] vm) {
        for (int i = 0; i < vm.length; ++i) {
            if (vm[i] <= 0) {
                return false;
            }
        }
        return true;
    }
}
