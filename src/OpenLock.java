import java.util.*;

public class OpenLock {
    // could be improved to use 2 ends bfs by growing both begin and end into sets and find their intersection
    Set<String> dead = new HashSet<>();
    Set<String> visited = new HashSet<>();

    class Qitem {
        String s;
        int dist;

        public Qitem(String s, int dist) {
            this.s = s;
            this.dist = dist;
        }
    }

    public int openLock(String[] deadends, String target) {
        for (String d : deadends) {
            dead.add(d);
        }
        String start = "0000";
        if (dead.contains(start)) {
            return -1;
        }
        Deque<Qitem> q = new ArrayDeque<>();
        q.offerLast(new Qitem(start, 0));
        visited.add(start);
        while (!q.isEmpty()) {
            Qitem top = q.pollFirst();
            if (top.s.equals(target)) {
                return top.dist;
            }
            for (int i = 0; i < 4; i++) {
                StringBuilder sb = new StringBuilder(top.s);
                int curchar = top.s.charAt(i) - '0';
                int[] turns = {1, -1};
                for (char j = 0; j < turns.length; j++) {
                    // next char: (cur + move + 10)%10
                    char nextchar = (char) ((curchar + turns[j] + 10) % 10 + '0');
                    sb.setCharAt(i, nextchar);
                    String next = sb.toString();
                    if (!dead.contains(next) && !visited.contains(next)) {
                        visited.add(next);
                        q.offerLast(new Qitem(next, 1 + top.dist));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] dead = {"8888"};
        System.out.println(new OpenLock().openLock(dead, "0009"));
    }

}

