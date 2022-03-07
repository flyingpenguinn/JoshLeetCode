import java.util.ArrayList;
import java.util.List;

public class CellsInRangeOnSpreadsheet {
    public List<String> cellsInRange(String s) {
        String[] ss = s.split(":");
        char cstart = Character.valueOf(ss[0].charAt(0));
        char cend =
                Character.valueOf(ss[1].charAt(0));
        int nstart = ss[0].charAt(1) - '0';
        int nend = ss[1].charAt(1) - '0';
        List<String> res = new ArrayList<>();
        // System.out.println(cstart+" "+cend);
        // System.out.println(nstart+" "+nend);
        for (char c = cstart; c <= cend; ++c) {
            for (int i = nstart; i <= nend; ++i) {
                res.add("" + c + i);
            }
        }
        return res;
    }
}
