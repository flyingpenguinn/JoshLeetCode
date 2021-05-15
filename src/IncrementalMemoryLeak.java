public class IncrementalMemoryLeak {
    public int[] memLeak(int m1, int m2) {
        int time = 1;
        while(m1>=time || m2>=time){
            if(m1>=m2){
                m1 -= time++;
            }else{
                m2 -= time++;
            }
        }
        return new int[]{time, m1, m2};
    }
}
