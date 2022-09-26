import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortThePeople {
    private class People {
        String name;
        int height;

        public People(String name, int height) {
            this.name = name;
            this.height = height;
        }
    }

    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        List<People> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            People p = new People(names[i], heights[i]);
            list.add(p);
        }
        Collections.sort(list, (x, y) -> Integer.compare(y.height, x.height));
        String[] res = new String[n];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i).name;
        }
        return res;
    }
}
