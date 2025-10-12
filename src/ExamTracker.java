import java.util.TreeMap;

class ExamTracker {
    private TreeMap<Integer,Long> psum = new TreeMap<>();
    private long csum = 0;
    public ExamTracker() {

    }

    public void record(int time, int score) {
        csum += score;
        psum.put(time, csum);
    }

    public long totalScore(int start, int end) {
        Integer before = psum.lowerKey(start);
        long sv = 0;
        if(before != null){
            sv = psum.get(before);
        }
        Integer cur = psum.floorKey(end);
        long cv = 0;
        if(cur != null){
            cv = psum.get(cur);
        }
        return cv - sv;
    }
}
