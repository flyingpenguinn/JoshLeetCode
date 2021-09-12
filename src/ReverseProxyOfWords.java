public class ReverseProxyOfWords {
    public String reversePrefix(String word, char ch) {
        char[] c = word.toCharArray();
        int index = word.indexOf(ch);
        if(index==-1){
            return word;
        }
        reverse(c, 0, index);
        return new String(c);
    }

    private void reverse(char[] c, int i, int j){
        while(i<j){
            swap(c, i, j);
            ++i;
            --j;
        }
    }

    private void swap(char[] c, int i, int j){
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
