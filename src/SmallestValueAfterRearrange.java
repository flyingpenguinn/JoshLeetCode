import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallestValueAfterRearrange {
    public long smallestNumber(long num) {
        String str = String.valueOf(num);
        if(num>0 ){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            if(c[0]=='0'){
                // here swap the first non zero with first digit which is 0
                for(int i=0; i<c.length; ++i){
                    if(c[i]>'0'){
                        swap(c, 0, i);
                        break;
                    }
                }
            }
            return Long.valueOf(new String(c));
        }else if(num<0){
            char[] c = str.substring(1).toCharArray();
            Arrays.sort(c);
            reverse(c);
            return -Long.valueOf(new String(c));
        }else{
            return num;
        }

    }

    private void reverse(char[] c){
        int i = 0;
        int j = c.length-1;
        while(i<j){
            swap(c, i++, j--);
        }
    }

    private void swap(char[] c, int i, int j){
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
