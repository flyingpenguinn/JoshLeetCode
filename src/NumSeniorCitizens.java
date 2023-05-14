public class NumSeniorCitizens {
    public int countSeniors(String[] ps) {
        int res = 0;
        for (String s : ps) {
            String ages = s.substring(11, 13);
            //   System.out.println(ages);
            int age = Integer.valueOf(ages);
            if (age > 60) {
                ++res;
            }
        }
        return res;
    }
}
