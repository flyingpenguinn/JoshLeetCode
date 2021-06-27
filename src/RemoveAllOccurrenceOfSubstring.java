import java.util.Objects;

public class RemoveAllOccurrenceOfSubstring {
    //@TODO make it o(n+m)
    public String removeOccurrences(String s, String part) {
        while(true){
            String ns = s.replaceAll(part, "");
            if(Objects.equals(ns, s)){
                return s;
            }
            s = ns;
        }
    }
}
