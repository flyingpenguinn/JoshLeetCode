public class SumOfDigitsAfterConvert {
    public int getLucky(String s, int k) {
        StringBuilder str = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            str.append((int)(c-'a'+1));
        }
        while(k>0){
            int res = 0;
            for(int i=0; i<str.length(); i++){
                res += (str.charAt(i)-'0');
            }
            str = new StringBuilder();
            str.append(res);
            k--;
        }
        return Integer.valueOf(str.toString());
    }
}
