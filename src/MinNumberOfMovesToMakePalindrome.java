public class MinNumberOfMovesToMakePalindrome {
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        int i = 0;
        int j = n-1;
        char [] sc = s.toCharArray();
        int res = 0;
        while(i<j){
            if(sc[i]==sc[j]){
                ++i;
                --j;
            }else{
                int tj = j;
                while(i<tj && sc[i] != sc[tj]){
                    --tj;
                }
                if(i<tj){
                    while(tj<j){
                        swap(sc, tj, tj+1);
                        ++res;
                        ++tj;
                    }
                    //System.out.println(new String(sc));

                }else{
                    swap(sc, i, i+1);
                    ///System.out.println(new String(sc));
                    ++res;
                }
            }
        }
        //System.out.println(new String(sc));
        return res;
    }

    private void swap(char[] sc, int i, int j){
        char tmp = sc[i];
        sc[i] = sc[j];
        sc[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new MinNumberOfMovesToMakePalindrome().minMovesToMakePalindrome("skwhhaaunskegmdtutlgtteunmuuludii"));
    }
}
