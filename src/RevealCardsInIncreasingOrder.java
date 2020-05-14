import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RevealCardsInIncreasingOrder {
    // use a deque to simulate
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] r = new int[n];
        Arrays.sort(deck);
        int lp = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q.offerLast(i);
        }

        while (true) {
            Integer index = q.pollFirst();
            int v = deck[lp++];
            //  System.out.println(index+" "+v);

            r[index] = v;
            if (!q.isEmpty()) {
                int next = q.pollFirst();
                q.offerLast(next);
            } else {
                break;
            }

        }
        return r;
    }
}
