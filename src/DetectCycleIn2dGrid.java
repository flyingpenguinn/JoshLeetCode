import base.ArrayUtils;

public class DetectCycleIn2dGrid {
    // if we visited a cell before and it didnt yield a cycle then there is no cycle here we can return immediately
    public boolean containsCycle(char[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != '2') {
                    if (hasCycle(a, i, j, -1, -1, 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean hasCycle(char[][] a, int i, int j, int ci, int cj, int dist) {
        char oldv = a[i][j];
        a[i][j] = '1';
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length) {
                if (ni == ci && nj == cj) {
                    continue;
                } else if (a[ni][nj] == '1') {
                    if (dist >= 4) {
                        return true;
                    }
                } else if (a[ni][nj] == oldv) {
                    boolean later = hasCycle(a, ni, nj, i, j, dist + 1);
                    if (later) {
                        return true;
                    }
                }
                // ignore ==2 or others
            }
        }
        a[i][j] = '2';
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new DetectCycleIn2dGrid().containsCycle(ArrayUtils.readAsChar("[[b,a,c],[c,a,c],[d,d,c],[b,c,c]]")));
        System.out.println(new DetectCycleIn2dGrid().containsCycle(ArrayUtils.readAsChar("[[a,a,a,a],[a,b,b,a],[a,b,b,a],[a,a,a,a]]")));
        System.out.println(new DetectCycleIn2dGrid().containsCycle(ArrayUtils.readAsChar("[[c,c,c,a],[c,d,c,c],[c,c,e,c],[f,c,c,c]]")));
        System.out.println(new DetectCycleIn2dGrid().containsCycle(ArrayUtils.readAsChar("[[a,b,b],[b,z,b],[b,b,a]]")));
    }
}
