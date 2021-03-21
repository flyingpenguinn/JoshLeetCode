import base.ArrayUtils;

import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.function.Function;

public class NumberOfOrdersInBacklog {

    private long mod = 1000000007;

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<long[]> buys = new PriorityQueue<>((x, y) -> Long.compare(y[0], x[0]));
        PriorityQueue<long[]> sells = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0]));
        long res = 0;
        for (int[] ord : orders) {
            long price = ord[0];
            long count = ord[1];
            int cur = 0;
            if (ord[2] == 0) {
                cur = process(price, count, sells, buys, true);
            } else {
                cur = process(price, count, buys, sells, false);
            }
            res += cur;
        }
        return (int) (res % mod);
    }

    private int process(long price, long count, PriorityQueue<long[]> pq1, PriorityQueue<long[]> pq2, boolean buy) {
        int res = 0;
        while (count > 0) {
            long[] top = pq1.peek();
            if (top == null) {
                break;
            } else {
                long topprice = top[0];
                boolean isvalid = buy ? price >= topprice : price <= topprice;
                if (!isvalid) {
                    break;
                }
                long consumed = Math.min(count, top[1]);
                count -= consumed;
                res -= consumed;
                if (top[1] > consumed) {
                    pq1.peek()[1] -= consumed;
                } else {
                    pq1.poll();
                }
            }
        }
        if (count > 0) {
            pq2.offer(new long[]{price, count});
            res += count;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfOrdersInBacklog().getNumberOfBacklogOrders(ArrayUtils.read("[[16,24,0],[16,2,1],[23,28,1],[21,6,0],[17,8,0],[25,7,0]]")));
    }
}

class NumberOfOrdersTreeMap {
    private TreeMap<Long, Long> buys = new TreeMap<>();
    private TreeMap<Long, Long> sells = new TreeMap<>();
    private long rem = 0;
    private long mod = 1000000007;

    public int getNumberOfBacklogOrders(int[][] orders) {
        for (int[] ord : orders) {
            long price = ord[0];
            long count = ord[1];
            if (ord[2] == 0) {
                while (count > 0) {
                    Long key = sells.isEmpty() ? null : sells.firstKey();
                    if (key == null || key > price) {
                        break;
                    } else {
                        long sold = Math.min(count, sells.get(key));
                        update(sells, key, -sold);
                        count -= sold;
                    }
                }
                update(buys, price, count);
            } else {
                while (count > 0) {
                    Long key = buys.isEmpty() ? null : buys.lastKey();
                    if (key == null || key < price) {
                        break;
                    } else {
                        long bought = Math.min(count, buys.get(key));
                        update(buys, key, -bought);
                        count -= bought;
                    }
                }
                update(sells, price, count);
            }
        }
        return (int) (rem % mod);
    }

    private void update(TreeMap<Long, Long> tm, long k, long d) {
        rem += d;
        //rem %= mod;
        long nv = tm.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }
}
