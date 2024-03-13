import java.util.Arrays;

public class MatchAlphaNumericalPatternMatrixI {
    public int[] findPattern(int[][] a, String[] p) {
        int Max = 1000;
        int am = a.length;
        int an = a[0].length;
        int pm = p.length;
        int pn = p[0].length();
        int ri = Max;
        int rj = Max;
        for (int i = 0; i < am; ++i) {
            for (int j = 0; j < an; ++j) {
                int[] map = new int[26];
                int[] rmap = new int[10];
                Arrays.fill(map, -1);
                Arrays.fill(rmap, -1);
                boolean bad = false;
                for (int k = 0; k < pm; ++k) {
                    int ni = i + k;
                    if (ni >= am) {
                        bad = true;
                        break;
                    }
                    for (int l = 0; l < pn; ++l) {
                        int nj = j + l;
                        if (nj >= an) {
                            bad = true;
                            break;
                        }
                        char pv = p[k].charAt(l);
                        if (Character.isDigit(pv)) {
                            int pind = pv - '0';
                            if (pind == a[ni][nj]) {
                                continue;
                            } else {
                                bad = true;
                                break;
                            }
                        } else {
                            int pind = pv - 'a';
                            int aind = a[ni][nj];
                            if (map[pind] == aind && rmap[aind] == pind) {
                                continue;
                            } else if (map[pind] == -1 && rmap[aind] == -1) {
                                map[pind] = aind;
                                rmap[aind] = pind;
                            } else {
                                bad = true;
                                break;
                            }
                        }
                    }
                    if (bad) {
                        break;
                    }
                }
                if (!bad) {
                    if (i < ri) {
                        ri = i;
                        rj = j;
                    } else if (i == ri && j < rj) {
                        ri = i;
                        rj = j;
                    }
                }
            }
        }
        return ri >= Max ? new int[]{-1, -1} : new int[]{ri, rj};
    }
}
