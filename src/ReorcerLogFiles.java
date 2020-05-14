import java.util.Arrays;
import java.util.Comparator;

public class ReorcerLogFiles {
    class LogComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String ranker1 = withoutIdentifier(o1);
            String ranker2 = withoutIdentifier(o2);
            if (isLetter(ranker1) && isDigit(ranker2)) {
                return -1;
            } else if (isLetter(ranker2) && isDigit(ranker1)) {
                return 1;
            } else if (isLetter(ranker1) && isLetter(ranker2)) {
                return ranker1.compareTo(ranker2);
            } else {
                // both digits, dont reorde: java Arrays.sort is stable sorting
                return 0;
            }
        }

        private String withoutIdentifier(String s) {
            return s.substring(s.indexOf(" ") + 1, s.length());
        }

        private boolean isLetter(String s) {
            return Character.isLetter(s.charAt(0));
        }

        private boolean isDigit(String s) {
            return Character.isDigit(s.charAt(0));
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new LogComparator());
        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        System.out.println(Arrays.toString(new ReorcerLogFiles().reorderLogFiles(logs)));
    }

}
