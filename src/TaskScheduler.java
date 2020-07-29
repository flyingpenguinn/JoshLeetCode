import java.util.*;

/*
LC#621
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    // from high to low freq. arrange up to == n+1 then resort and repeat
    // run high freq as much as possible
    // similar to rearrange string k distance apart
    /*
    traps:
    1. last segment can be a stub
    2. num++: must be num apart, means segment is num+1 in length

     */
    public int leastInterval(char[] a, int interval) {
        if(a==null){
            return 0;
        }
        Map<Character,Integer> m = new HashMap<>();
        int n = a.length;
        for(int i=0; i<n; i++){
            update(m, a[i], 1);
        }
        int seg = interval+1;
        PriorityQueue<Character> pq = new PriorityQueue<>((x,y) -> Integer.compare(m.get(y), m.get(x))); // big first
        for(char ch: m.keySet()){
            pq.offer(ch); // all chars freq high to low
        }
        int res = 0;
        while(!m.isEmpty()){
            int curseg = 0;
            List<Character> polled = new ArrayList<>();
            while(!pq.isEmpty() && curseg <seg){
                Character top = pq.poll();
                polled.add(top);
                update(m, top, -1);
                res++;
                curseg++;
            }
            if(!m.isEmpty() && curseg<seg){
                res += seg-curseg;
            }
            for(char pc: polled){
                if(m.containsKey(pc)){
                    pq.offer(pc); // will reorder based on new counts
                }
            }
        }
        return res;
    }

    private void update(Map<Character,Integer> m, char k, int delta){
        int nv = m.getOrDefault(k, 0)+delta;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }
}
