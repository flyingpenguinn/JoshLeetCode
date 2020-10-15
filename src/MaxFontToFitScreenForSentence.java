public class MaxFontToFitScreenForSentence {
    interface FontInfo {
        // Return the width of char ch when fontSize is used.
        public int getWidth(int fontSize, char ch);
        // Return Height of any char when fontSize is used.
        public int getHeight(int fontSize);
    }

    public int maxFont(String t, int w, int h, int[] fonts, FontInfo fontInfo) {
        int l = 0;
        int u= fonts.length-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            int fs = fonts[mid];
            int wid = 0;
            boolean bad = fontInfo.getHeight(fs) > h;
            for(int i=0; i<t.length() && !bad; i++){
                wid += fontInfo.getWidth(fs,t.charAt(i));
                if(wid > w){
                    bad = true;
                }
            }
            if(bad){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return u==-1?-1: fonts[u];
    }
}
