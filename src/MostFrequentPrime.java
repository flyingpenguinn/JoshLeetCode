import java.util.HashMap;
import java.util.Map;

public class MostFrequentPrime {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public int mostFrequentPrime(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Map<Integer, Integer> freq = new HashMap<>();
        int maxf = -1;
        int res = -1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                //   System.out.println("starting "+i+" "+j);

                for (int k = 0; k < dirs.length; ++k) {
                    //    System.out.println("checking dir "+k);
                    int[] d = dirs[k];
                    int cur = a[i][j];
                    int ci = i;
                    int cj = j;
                    while (true) {
                        ci += d[0];
                        cj += d[1];
                        if (ci >= 0 && ci < m && cj >= 0 && cj < n) {
                            cur = cur * 10 + a[ci][cj];
                            //    System.out.println(i+","+j+" got "+cur);
                            if (isprime(cur)) {
                                int nv = freq.getOrDefault(cur, 0) + 1;
                                freq.put(cur, nv);
                                if (nv > maxf) {
                                    maxf = nv;
                                    res = cur;
                                } else if (nv == maxf) {
                                    if (cur > res) {
                                        res = cur;
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }

                }
            }
        }
        return res;
    }

    private Map<Integer, Boolean> pm = new HashMap<>();

    private boolean isprime(int v) {
        if (pm.containsKey(v)) {
            return pm.get(v);
        }
        if (v == 1) {
            return false;
        }
        for (int i = 2; i * i <= v; ++i) {
            if (v % i == 0) {
                //   System.out.println(v+" is not prime");
                pm.put(v, false);
                return false;
            }
        }
        pm.put(v, true);
        //  System.out.println(v+" is prime");
        return true;
    }
}
