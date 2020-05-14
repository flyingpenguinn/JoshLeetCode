import java.util.HashMap;
import java.util.Map;

public class FindAndReplaceInString {
    public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
        int n=s.length();

        Map<Integer,String> sm = new HashMap<>();
        Map<Integer,String> tm= new HashMap<>();
        for(int i=0;i<indexes.length;i++){
            sm.put(indexes[i],sources[i]);
            tm.put(indexes[i],targets[i]);
        }


        StringBuilder sb= new StringBuilder();
        int i=0;
        while(i<n){
            if(sm.containsKey(i)){
                int k=0;
                String ss= sm.get(i);
                for(;k<ss.length() && i+k<n;k++){
                    if(s.charAt(i+k)!=ss.charAt(k)){
                        break;
                    }
                }
                if(k==ss.length()){
                    sb.append(tm.get(i));
                    i+=k;
                }else{
                    sb.append(s.charAt(i));
                    i++;
                }

            }else{
                sb.append(s.charAt(i));
                i++;
            }

        }
        return sb.toString();
    }
}
