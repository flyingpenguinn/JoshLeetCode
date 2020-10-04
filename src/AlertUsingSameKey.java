import java.util.*;

public class AlertUsingSameKey {
    class Person {
        private String name;
        private int visit;

        public Person(String name, int visit) {
            this.name = name;
            this.visit = visit;
        }
    }

    private int toint(String time) {
        String[] ss = time.split(":");
        int hour = Integer.valueOf(ss[0]);
        int min = Integer.valueOf(ss[1]);
        return hour * 60 + min;
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        Person[] input = new Person[n];
        for (int i = 0; i < n; i++) {
            input[i] = new Person(keyName[i], toint(keyTime[i]));
        }
        Arrays.sort(input, (x, y) -> Integer.compare(x.visit, y.visit));
        Map<String, Deque<Integer>> m = new HashMap<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Deque<Integer> times = m.getOrDefault(input[i].name, new ArrayDeque<>());
            int time = input[i].visit;
            times.offer(time);
            while (!times.isEmpty() && times.peek() < time - 60) {
                times.poll();
            }
            //     System.out.println(input[i].name+" "+times);
            if (times.size() >= 3) {
                res.add(input[i].name);
            }
            m.put(input[i].name, times);
        }
        List<String> rlist = new ArrayList<>(res);
        Collections.sort(rlist);
        return rlist;
    }
}
