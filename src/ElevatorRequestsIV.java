class Solution {
    private long Max = (long) 4e18;

    public long elevatorRequests(int n, int start, int[][] requests) {
        Arrays.sort(requests, (x, y) -> Integer.compare(x[1], y[1]));

        long l = 0;
        long maxArrival = 0;

        for (int[] r : requests) {
            maxArrival = Math.max(maxArrival, r[0]);
        }

        long u = maxArrival + 2L * (n - 1);

        while (l < u) {
            long mid = l + (u - l) / 2;

            if (can(requests, start, mid)) {
                u = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean can(int[][] a, int start, long t) {
        int n = a.length;

        long[][] left = new long[n][n];
        long[][] right = new long[n][n];

        for (int i = 0; i < n; ++i) {
            Arrays.fill(left[i], Max);
            Arrays.fill(right[i], Max);
        }

        for (int i = 0; i < n; ++i) {
            if (a[i][0] <= t) {
                left[i][i] = 0;
                right[i][i] = 0;
            }
        }

        for (int len = 1; len <= n; ++len) {
            for (int l = 0; l + len - 1 < n; ++l) {
                int r = l + len - 1;

                if (l > 0) {
                    long deadline = t - a[l - 1][0];

                    if (deadline >= 0) {
                        long way1 = left[l][r];
                        if (way1 < Max) {
                            way1 += (long) a[l][1] - a[l - 1][1];
                        }

                        long way2 = right[l][r];
                        if (way2 < Max) {
                            way2 += (long) a[r][1] - a[l - 1][1];
                        }

                        long best = Math.min(way1, way2);

                        if (best <= deadline) {
                            left[l - 1][r] = Math.min(
                                    left[l - 1][r],
                                    best
                            );
                        }
                    }
                }

                if (r + 1 < n) {
                    long deadline = t - a[r + 1][0];

                    if (deadline >= 0) {
                        long way1 = left[l][r];
                        if (way1 < Max) {
                            way1 += (long) a[r + 1][1] - a[l][1];
                        }

                        long way2 = right[l][r];
                        if (way2 < Max) {
                            way2 += (long) a[r + 1][1] - a[r][1];
                        }

                        long best = Math.min(way1, way2);

                        if (best <= deadline) {
                            right[l][r + 1] = Math.min(
                                    right[l][r + 1],
                                    best
                            );
                        }
                    }
                }
            }
        }

        long way1 = left[0][n - 1];
        if (way1 < Max) {
            way1 += Math.abs((long) a[0][1] - start);
        }

        long way2 = right[0][n - 1];
        if (way2 < Max) {
            way2 += Math.abs((long) a[n - 1][1] - start);
        }

        return Math.min(way1, way2) <= t;
    }
}
