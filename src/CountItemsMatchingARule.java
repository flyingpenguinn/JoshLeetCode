import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountItemsMatchingARule {
    private Map<String, Integer> map = new HashMap<>();

    {
        map.put("type", 0);
        map.put("color", 1);
        map.put("name", 2);
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int res = 0;
        for (List<String> item : items) {
            int index = map.get(ruleKey);
            if (item.get(index).equals(ruleValue)) {
                res++;
            }
        }
        return res;
    }
}
