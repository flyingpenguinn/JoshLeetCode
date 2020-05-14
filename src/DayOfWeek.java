import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayOfWeek {
    public String dayOfTheWeek(int day, int month, int year) {
        int days = 0;
        for (int i = 1970; i < year; i++) {
            days += isLeap(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            days += dayInMonth(i, year);
        }
        days += day - 1;
        int rem = (days + 4) % 7;
        return dayInWeek[rem];
    }

    String[] dayInWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int[] dayInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int dayInMonth(int i, int year) {
        int rt = dayInMonth[i];
        if (i == 2) {
            rt += isLeap(year) ? 1 : 0;
        }
        return rt;
    }

    private boolean isLeap(int i) {
        if (i % 100 == 0) {
            return i % 400 == 0;
        } else {
            return i % 4 == 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new DayOfWeek().dayOfTheWeek(8, 9, 2019));
    }
}

@SuppressWarnings( "deprecation" )
class JavaCheat {
    public String dayOfTheWeek(int day, int month, int year) {
        Date cur = new Date(year - 1900, month - 1, day);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        return simpleDateformat.format(cur);
    }
}