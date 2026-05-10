public class ScoreValidator {
    public int[] scoreValidator(String[] events) {
        int n = events.length;
        int score = 0;
        int counter = 0;
        for(String ev: events){
            if(ev.equals("W")){
                ++counter;
            }else if(ev.equals("WD")){
                ++score;
            }else if(ev.equals("NB")){
                ++score;
            }else{
                score += Integer.parseInt(ev);
            }
            if(counter == 10){
                break;
            }
        }
        return new int[]{score, counter};
    }
}
