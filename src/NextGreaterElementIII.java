public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] c = String.valueOf(n).toCharArray();
        int i = c.length-1;
        while(i-1>=0){
            if(c[i]>c[i-1]){
                break;
            }
            i--;
        }
        if(i==0){
            return -1;
        }
        // i>i-1 . now find smallest that is >i-1 from i
        int k = i-1;
        int j = i;
        int tj = i;
        while(j<c.length){
            if(c[j]>c[k]){
                // smallest > value. order doesnt matter as we will reverse anyway
                tj = j;
            }else{
                break;
            }
            j++;
        }
        swap(c, k, tj);
        reverse(c, i, c.length-1);
        long rt= Long.valueOf(new String(c));
        return rt>Integer.MAX_VALUE? -1: (int)rt;
    }

    private void reverse(char[] c, int i, int j){
        while(i<j){
            swap(c, i++, j--);
        }
    }

    private void swap(char[] c, int i, int j){
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new NextGreaterElementIII().nextGreaterElement(12222333));

    }
}
