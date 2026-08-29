public class CheckASCIIPalindromic {
    public boolean isPalindromic(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            int asc = (int)c;
            String bin = Integer.toBinaryString(asc);
            while(bin.length()<8){
                bin = "0"+bin;
            }
            sb.append(bin);
        }
        return ispalin(sb.toString());
    }

    private boolean ispalin(String s){
        int i = 0;
        int j = s.length()-1;
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }
}
