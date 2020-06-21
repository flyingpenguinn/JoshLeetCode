import base.ArrayUtils;

import java.util.*;

/*
LC#1488
Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.

Given an integer array rains where:

rains[i] > 0 means there will be rains over the rains[i] lake.
rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
Return an array ans where:

ans.length == rains.length
ans[i] == -1 if rains[i] > 0.
ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.

Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)



Example 1:

Input: rains = [1,2,3,4]
Output: [-1,-1,-1,-1]
Explanation: After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day full lakes are [1,2,3]
After the fourth day full lakes are [1,2,3,4]
There's no day to dry any lake and there is no flood in any lake.
Example 2:

Input: rains = [1,2,0,0,2,1]
Output: [-1,-1,2,1,-1,-1]
Explanation: After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day, we dry lake 2. Full lakes are [1]
After the fourth day, we dry lake 1. There is no full lakes.
After the fifth day, full lakes are [2].
After the sixth day, full lakes are [1,2].
It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
Example 3:

Input: rains = [1,2,0,1,2]
Output: []
Explanation: After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
After that, it will rain over lakes [1,2]. It's easy to prove that no matter which lake you choose to dry in the 3rd day, the other one will flood.
Example 4:

Input: rains = [69,0,0,0,69]
Output: [-1,69,1,1,-1]
Explanation: Any solution on one of the forms [-1,69,x,y,-1], [-1,x,69,y,-1] or [-1,x,y,69,-1] is acceptable where 1 <= x,y <= 10^9
Example 5:

Input: rains = [10,20,20]
Output: []
Explanation: It will rain over lake 20 two consecutive days. There is no chance to dry any lake.


Constraints:

1 <= rains.length <= 10^5
0 <= rains[i] <= 10^9
 */
public class AvoidFloodInTheCity {
    // like min refuel stops, we remember the chances and then reckon later
    public int[] avoidFlood(int[] a) {
        int n = a.length;
        TreeSet<Integer> dry = new TreeSet<>();
        Map<Integer, Integer> full = new HashMap<>();
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                if (full.containsKey(a[i])) {
                    int lastfull = full.get(a[i]);
                    Integer dryday = dry.higher(lastfull);
                    if (dryday == null) {
                        return new int[0];
                    } else {
                        r[dryday] = a[i];
                        dry.remove(dryday);
                    }
                }
                full.put(a[i], i);
                r[i] = -1;
            } else {
                dry.add(i);
                r[i] = 1;
            }
        }
        return r;
    }


    public static void main(String[] args) {
        System.out.println(new AvoidFloodInTheCity().avoidFlood(ArrayUtils.read1d("[1,2,0,0,2,1]")));
    }
}

class AvoidFloodAlternative {
    // every time pick the lake that is about to be rained next time
    public int[] avoidFlood(int[] a) {
        int n = a.length;
        Map<Integer, ArrayDeque<Integer>> m = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                Deque<Integer> st1 = m.getOrDefault(o1, new ArrayDeque<>());
                Deque<Integer> st2 = m.getOrDefault(o2, new ArrayDeque());
                int v1 = st1.isEmpty() ? n + 1 : st1.peekFirst();
                int v2 = st2.isEmpty() ? n + 1 : st2.peekFirst();
                return Integer.compare(v1, v2);
            }
        });

        for (int i = 0; i < n; i++) {
            m.computeIfAbsent(a[i], k -> new ArrayDeque()).offerLast(i);
        }

        int[] r = new int[n];
        Set<Integer> pending = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (pending.contains(a[i])) {
                return new int[0];
            } else if (a[i] > 0) {
                pending.add(a[i]);
                Deque<Integer> dq = m.get(a[i]);
                dq.pollFirst();
                // the top of the pq was supposed to be picked but now it rains on it. so we reshuffle
                if (!pq.isEmpty() && a[i] == pq.peek()) {
                    pq.poll();
                }
                pq.offer(a[i]);
                r[i] = -1;
            } else {
                if (pq.isEmpty()) {
                    r[i] = 1;
                } else {
                    r[i] = pq.poll();
                    pending.remove(r[i]);
                    Deque<Integer> dq = m.get(r[i]);
                    dq.pollFirst();
                    pq.offer(r[i]);
                }
            }
        }
        return r;
    }
}
