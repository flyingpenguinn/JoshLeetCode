public class ReformatDate {

    private String[] Months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public String reformatDate(String date) {
        String[] ss = date.split(" ");
        String year = ss[2];
        String month = "";
        for (int i = 0; i < Months.length; i++) {
            if (Months[i].equals(ss[1])) {
                month = String.valueOf(i);
                break;
            }
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = ss[0].substring(0, ss[0].length() - 2);
        if (day.length() == 1) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    }
}
