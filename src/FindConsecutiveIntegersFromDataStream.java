import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FindConsecutiveIntegersFromDataStream {
    class DataStream {
        int count = 0;
        private int v = 0;
        private int k = -1;

        public DataStream(int value, int k) {
            v = value;
            this.k = k;
        }


        public boolean consec(int num) {
            if (num == v) {
                ++count;
            } else {
                count = 0;
            }
            return count >= k;
        }
    }
}
