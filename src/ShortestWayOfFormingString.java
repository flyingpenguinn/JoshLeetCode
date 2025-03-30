import java.util.HashSet;
import java.util.Set;

public class ShortestWayOfFormingString {
    public int shortestWay(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        Set<Character> sset = new HashSet<>();
        for (char sc : s.toCharArray()) {
            sset.add(sc);
        }
        for (char tc : t.toCharArray()) {
            if (!sset.contains(tc)) {
                return -1;
            }
        }
        int j = 0;
        int i = 0;
        while(i<tn){
            if (t.charAt(i) == s.charAt(j%sn)) {
                ++i;
            }
            ++j;
        }
        return (int)Math.ceil((j)*1.0/sn);
    }

    public static void main(String[] args) {
        System.out.println(new ShortestWayOfFormingString().shortestWay("xyz", "xzyxz"));
    }
}
