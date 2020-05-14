import java.util.Arrays;

public class BoatsToSavePeople {
    // greedy and two pointer to pack people
    public int numRescueBoats(int[] p, int limit) {
        Arrays.sort(p);
        int i = 0;
        int j = p.length - 1;
        int boat = 0;
        while (i <= j) {
            // to count in last person
            if (p[i] + p[j] <= limit) {
                i++;
            }
            boat++;
            j--;

        }
        return boat;
    }
}
