import java.util.Iterator;
import java.util.TreeSet;

class ExamRoom {
    TreeSet<Integer> set = new TreeSet<>();
    int n;

    public ExamRoom(int n) {
        this.n = n;
    }

    public int seat() {
        Iterator<Integer> it = set.iterator();
        if (set.size() >= 2) {
            int prev = it.next();
            int mp = -1;
            int mq = -1;
            int mpq = -1;
            while (it.hasNext()) {
                int cur = it.next();
                int pos = prev + (cur - prev) / 2;
                int maxc = Math.min(pos - prev, cur - pos);
                if (maxc > mpq) {
                    mp = prev;
                    mq = cur;
                    mpq = maxc;
                }
                prev = cur;
            }
            int seat = mp + (mq - mp) / 2; //0..2->2   0..3->1
            int first = set.first();
            if (first >= mpq) {// 0 takes precedence
                seat = 0;
                mpq = first;
            }
            int last = set.last();
            if (n - 1 - last > mpq) {
                seat = n - 1;
                mpq = n - 1 - last;
            }
            if (mpq <= 0) {
                return -1;// can't sit anywhere
            }
            set.add(seat);
            return seat;
        } else if (set.size() == 1) {
            int cur = it.next();
            if (cur < (n - 1 - cur)) {
                set.add(n - 1);
                return n - 1;
            } else {
                set.add(0);
                return 0;
            }
        } else {
            set.add(0);
            return 0;
        }
    }

    public void leave(int p) {
        set.remove(p);
    }
}
