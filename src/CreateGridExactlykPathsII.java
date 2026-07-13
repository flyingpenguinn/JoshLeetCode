import java.util.Arrays;
import java.util.List;

public class CreateGridExactlykPathsII {
    // set bit i
    private static final String[] ADD = {
            ".............",
            "#............",
            "##...........",
            "###..........",
            "####.........",
            "#####........",
            "######.......",
            "#######......",
            "########.....",
            "#########...."
    };

    // double and pass onto the next
    private static final String[] SKIP = {
            "..##########.",
            "#..#########.",
            "##..########.",
            "###..#######.",
            "####..######.",
            "#####..#####.",
            "######..####.",
            "#######..###.",
            "########..##.",
            "#########..#."
    };

    public List<String> createGrid(int k) {
        int highest = 31 - Integer.numberOfLeadingZeros(k);
        String[] res = new String[2 * (highest + 1)];

        for (int bit = 0; bit <= highest; ++bit) {
            res[2 * bit] = (k & (1 << bit)) != 0
                    ? ADD[bit]
                    : SKIP[bit];

            res[2 * bit + 1] = SKIP[bit];
        }

        return Arrays.asList(res);
    }
}
