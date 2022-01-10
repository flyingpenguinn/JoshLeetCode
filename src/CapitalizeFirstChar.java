public class CapitalizeFirstChar {
    public String capitalizeTitle(String title) {
        int n = title.length();
        char[] sc = title.toLowerCase().toCharArray();
        int start = 0;
        for(int i=0; i<n; ++i){
            char c = title.charAt(i);
            if(c==' '){
                // start...i-1
                if(i-start>2){
                    sc[start] = Character.toUpperCase(sc[start]);
                }
                start = i+1;
            }
        }
        if(n-start>2){
            sc[start] = Character.toUpperCase(sc[start]);
        }
        return new String(sc);
    }
}
