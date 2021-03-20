import java.util.TreeSet;

public class SecondLargestNumberInString {
    public int secondHighest(String s) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = s.length() - 1; i >= 0; i--) {

            if (Character.isDigit(s.charAt(i))) {
                set.add(s.charAt(i) - '0');
            }

        }
        if (set.isEmpty() || set.size() == 1) {
            return -1;
        }
        set.pollLast();
        return set.last();
    }
}
