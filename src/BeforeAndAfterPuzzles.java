import java.util.*;

public class BeforeAndAfterPuzzles {


    public List<String> beforeAndAfterPuzzles(String[] phrases) {

        Set<String> r = new HashSet<>();
        for (int i = 0; i < phrases.length; i++) {
            if (i > 0 && phrases[i].equals(phrases[i - 1])) {
                continue;
            }
            for (int j = 0; j < phrases.length; j++) {
                if (i == j) {
                    continue;
                }
                if (j > 0 && j - 1 != i && phrases[j].equals(phrases[j - 1])) {
                    continue;
                }
                String pi = phrases[i];
                String pj = phrases[j];
                if (pi.isEmpty() || pj.isEmpty()) {
                    continue;
                }
                String[] picut = pi.split(" ");
                String[] pjcut = pj.split(" ");
                if (picut[picut.length - 1].equals(pjcut[0])) {
                    String str = pi + pj.substring(pjcut[0].length());
                    r.add(str);
                }
            }
        }
        List<String> rl = new ArrayList<>(r);
        Collections.sort(rl);
        return rl;
    }

    public static void main(String[] args) {
        String[] str = {"a", "a d", "a", "a", "a c d"};
        System.out.println(new BeforeAndAfterPuzzles().beforeAndAfterPuzzles(str));
    }
}
