public class SumOfSortableIntegers {
    public int sortableIntegers(int[] a) {
        int n = a.length;
        int[] inc = new int[n];
        inc[n - 1] = 1;

        for (int i = n - 2; i >= 0; --i) {
            if (a[i] <= a[i + 1]) {
                inc[i] = inc[i + 1] + 1;
            } else {
                inc[i] = 1;
            }
        }
        int res = 0;
        for (int k = 1; k <= n; ++k) {
            if (n % k != 0) {
                continue;
            }
            int i = 0;
            boolean bad = false;
            int maxsofar = 0;
            while (i < n) {
                int j = i + k - 1;
                if (a[i] < a[j] && inc[i] < k) {
                    bad = true;
                    break;
                }

                int maxv = 0;
                if (a[i] == a[j]) {
                    if (inc[i] >= k) {
                        //
                    } else {
                        int len1 = inc[i];
                        int realmin = a[i + len1];
                        if (realmin < maxsofar) {
                            bad = true;
                            break;
                        }
                        int len2 = inc[i + len1];
                        if (len1 + len2 < k) {
                            bad = true;
                            break;
                        }
                        maxv = a[i + len1-1];
                    }
                }

                if (a[i] <= a[j]) {
                    if (a[i] < maxsofar) {
                        bad = true;
                        break;
                    }
                    maxv = a[j];
                }
                if (a[i] > a[j]) {
                    int len1 = inc[i];
                    int len2 = inc[i + len1];
                    if (len2 < k - len1) {
                        bad = true;
                        break;
                    }
                    int minv = a[i + len1];
                    if (minv < maxsofar) {
                        bad = true;
                        break;
                    }
                    maxv = a[i];
                }
                if (!bad) {
                    i += k;
                    maxsofar = Math.max(maxsofar, maxv);
                } else {
                    break;
                }
            }
            if (!bad) {
                res += k;
            }
        }
        return res;
    }
}
