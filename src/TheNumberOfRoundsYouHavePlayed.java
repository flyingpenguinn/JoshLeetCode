public class TheNumberOfRoundsYouHavePlayed {
    public int numberOfRounds(String start, String end) {
        int startHour = Integer.valueOf(start.substring(0, 2));
        int startMin = Integer.valueOf(start.substring(3,5));
        int st = startHour*60+startMin;
        int endHour = Integer.valueOf(end.substring(0, 2));
        int endMin = Integer.valueOf(end.substring(3, 5));
        int en = endHour*60+endMin;
        if(en<st){
            en += 24*60;
        }
        int stround = (int) Math.ceil(st*1.0/15.0);
        int enround = (int) Math.floor(en*1.0/15.0);
        return Math.max(enround-stround, 0);// it could become -1 if it's ceil(0.1) - floor(0.1)
    }
}
