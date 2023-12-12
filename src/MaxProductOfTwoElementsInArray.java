public class MaxProductOfTwoElementsInArray {
    int maxProduct(int[] a) {
        int m1 = -1;
        int m2 = m1;
        for(int ai: a){
            if(ai>m1){
                m2 = m1;
                m1 = ai;
            }else{
                m2 = Math.max(m2, ai);
            }
        }
        return (m1-1)*(m2-1);
    }
}
