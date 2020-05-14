import java.util.PriorityQueue;

public class MaximumSwap {


    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        if (n <= 1) {
            return num;
        }
        for (int i = 0; i < n - 1; i++) {
            int numi = str.charAt(i) - '0';
            int numnexti = str.charAt(i + 1) - '0';
            if (numi < numnexti) {
                // find the first digit that is < numnexti, then get the biggest out of numnexti
                int max = numnexti;
                int maxj = i + 1;
                for (int j = i + 2; j < n; j++) {
                    int numj = str.charAt(j) - '0';
                    // must be =. we want to go as right as possible
                    if (numj >= max) {
                        max = numj;
                        maxj = j;
                    }
                }

                // can't just swap with i! like 109.
                // swap with the first that is smaller than max the big number
                for (int j = 0; j <= i; j++) {
                    int numj = str.charAt(j) - '0';
                    if (numj < max) {
                        return Integer.valueOf(swap(str, j, maxj));
                    }
                }
            }

        }
        return num;
    }

    private String swap(String str, int i, int j) {
        char[] sb = str.toCharArray();
        char tmp = sb[i];
        sb[i] = sb[j];
        sb[j] = tmp;
        return new String(sb);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSwap().maximumSwap(2743));
        System.out.println(new MaximumSwap().maximumSwap(10909091));
    }
}

class MaximumSwapPqBased {

    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int[] last = new int[10];

        for (int i = 0; i < n; i++) {
            pq.offer(str.charAt(i) - '0');
            last[str.charAt(i) - '0'] = i;
        }
        for (int i = 0; i < n; i++) {
            int v = str.charAt(i) - '0';
            int qi = pq.poll();
            if (v != qi) {
                // use the lowest digit
                str = swap(i, last[qi], str);
                break;
            }
        }

        return Integer.valueOf(str);
    }

    private String swap(int i, int j, String str) {
        char[] sb = str.toCharArray();
        char tmp = sb[i];
        sb[i] = sb[j];
        sb[j] = tmp;
        return new String(sb);
    }
}
