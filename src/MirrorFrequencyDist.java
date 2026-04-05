import java.util.Map;

public class MirrorFrequencyDist {
    private int Max = (int) 1e9;
    private int Min = -Max;
    private long Mod = (long) (1e9 + 7);

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int mirrorFrequency(String s) {
        int[] cf = new int[26];
        int[] nf = new int[10];
        int n = s.length();

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isAlphabetic(c)) {
                int cind = c - 'a';

                ++cf[cind];

            } else {
                int cind = c - '0';

                ++nf[cind];

            }

        }
        int res = 0;

        for (int cind = 0; cind <= 12; ++cind) {


            int mirror = 25 - cind;
            res += Math.abs(cf[cind] - cf[mirror]);


        }
        for (int cind = 0; cind <= 4; ++cind) {

            int mirror = 9 - cind;
            res += Math.abs(nf[cind] - nf[mirror]);


        }

        return res;

    }

    static void main() {
        System.out.println(new MirrorFrequencyDist().mirrorFrequency("ab1z9"));
    }
}
