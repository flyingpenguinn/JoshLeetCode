import java.util.Map;
import java.util.TreeMap;

public class ConstructStringWithRepeatLimit {
    public String repeatLimitedString(String s, int repeatLimit) {
        TreeMap<Character, Integer> m = new TreeMap<>();
        for (char c : s.toCharArray()) {
            update(m, c, 1);
        }
        StringBuilder sb = new StringBuilder();
        int topallow = repeatLimit;
        while (!m.isEmpty()) {
            char biggest = m.lastKey();
            int bcc = m.getOrDefault(biggest, 0);
            int times = Math.min(bcc, topallow);
            for(int i=0; i<times; ++i){
                sb.append(biggest);
            }
            update(m, biggest, -times);
            Character second = m.lowerKey(biggest);
            if(second == null){
                break;
            }
            sb.append(second);
            update(m, second, -1);
            if(!m.containsKey(biggest) && m.containsKey(second)){
                topallow = repeatLimit -1;
            }else{
                topallow = repeatLimit;
            }
        }

        return sb.toString();
    }

    private void update(Map<Character, Integer> m, char k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ConstructStringWithRepeatLimit().repeatLimitedString("yxxvvuvusrrqqppopponlihigfeeddcbab", 2));
    }
}
