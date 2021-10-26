public class NumberOfValidWordsInSentence {
    private boolean ispunc(char c){
        return c=='!' || c=='.' || c==',';
    }
    private boolean islower(char c){
        return Character.isLowerCase(c);
    }

    private boolean ishyphen(char c){
        return c=='-';
    }

    public int countValidWords(String sentence) {
        String[] ss = sentence.split(" ");
        int count = 0;
        for(String s: ss){
            if(s.isEmpty()){
                continue;
            }
            if(isWord(s.toCharArray())){
                //    System.out.println(s);
                ++count;
            }
        }
        return count;
    }

    private boolean isWord(char[] s){
        int hc = 0;
        int pc = 0;
        int n = s.length;
        for(int i=0; i<n; ++i){
            char c = s[i];
            if(!islower(c) && !ishyphen(c) && !ispunc(c) ){
                return false;
            }
            if(ishyphen(c)){
                if(hc==1){
                    return false;
                }
                if(i==0 || i==n-1){
                    return false;
                }
                if(!islower(s[i-1]) || !islower(s[i+1])){
                    return false;
                }
                ++hc;
            }
            if(ispunc(c)){
                if(pc==1){
                    return false;
                }
                if(i!= n-1){
                    return false;
                }
                ++pc;
            }
        }
        return true;
    }
}
