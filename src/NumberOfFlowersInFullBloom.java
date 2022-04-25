import java.util.*;

public class NumberOfFlowersInFullBloom {
    private class Person {
        int index;
        int time;
        int sol = 0;

        public Person(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < flowers.length; ++i) {
            int start = flowers[i][0];
            int end = flowers[i][1];
            update(tm, start, 1);
            update(tm, end + 1, -1);
        }
        List<Person> lp = new ArrayList<>();
        for (int i = 0; i < persons.length; ++i) {
            lp.add(new Person(i, persons[i]));
        }
        Collections.sort(lp, (x, y) -> Integer.compare(x.time, y.time));
        int psum = 0;
        int pi = 0;
        int lasttime = 0;
        for (int tk : tm.keySet()) {
            if (pi == lp.size()) {
                break;
            }
            while (pi < lp.size() && lp.get(pi).time < tk && lp.get(pi).time >= lasttime) {
                lp.get(pi).sol = psum;
                ++pi;
            }
            psum += tm.get(tk);
        }
        int[] res = new int[persons.length];
        for (Person p : lp) {
            res[p.index] = p.sol;
        }
        return res;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;

        m.put(k, nv);

    }
}
