import java.util.HashMap;
import java.util.Map;

public class MinOperationsToMakeArrayAlternating {
    public int minimumOperations(int[] a) {
        int n = a.length;
        Map<Integer, Integer> om = new HashMap<>();
        Map<Integer, Integer> em = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                em.put(a[i], em.getOrDefault(a[i], 0) + 1);
            } else {
                om.put(a[i], om.getOrDefault(a[i], 0) + 1);
            }
        }
        //    System.out.println(em);
        //    System.out.println(om);
        int emax1 = 0;
        int emax1value = -1;
        int emax2 = 0;
        int omax1 = 0;
        int omax1value = -1;
        int omax2 = 0;
        int evens = 0;
        int odds = 0;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                int count = em.get(a[i]);
                if (emax1 < count) {
                    emax2 = emax1;
                    emax1 = count;
                    emax1value = a[i];
                } else if (emax2 < count && a[i] != emax1value) {
                    emax2 = count;
                }
                ++evens;
            } else {
                int count = om.get(a[i]);
                if (omax1 < count) {
                    omax2 = omax1;
                    omax1 = count;
                    omax1value = a[i];
                } else if (omax2 < count && a[i] != omax1value) {
                    omax2 = count;
                }
                ++odds;
            }
        }
        //   System.out.println(omax1value+" "+emax1value+" "+emax1+" "+omax1+" "+emax2+" "+omax2);
        if (omax1value != emax1value) {
            return evens - emax1 + odds - omax1;
        } else {
            int way1 = evens - emax2 + odds - omax1;
            int way2 = evens - emax1 + odds - omax2;
            return Math.min(way1, way2);
        }
    }
}
