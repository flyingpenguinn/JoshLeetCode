public class ReverseStringII {
    public String reverseStr(String s, int k) {
        int start = -1;
        int end = -1;
        StringBuilder sb = new StringBuilder(s);
        for(int i=0; i<s.length();i++){
            if(i%k == 0 && (i/k)%2==0){
                start = i;
            }else if ((i+1)%k == 0 && ((i+1)/k)%2==1){
                end = i;
            }
            if(start != -1 && end!= -1){
                reverse(sb, start,end);
                start = -1;
                end = -1;
            }
        }
        if(start != -1 && end==-1){
            reverse(sb, start,s.length()-1);
        }
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int start, int end) {
        int i = start;
        int j = end;
        while(i<j){
            swap(sb, i++, j--);
        }
    }

    private void swap(StringBuilder sb, int i, int j){
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
    }
}
