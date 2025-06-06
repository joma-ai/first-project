public class DateOfBirth {
    private int day;
    private int month;
    private int year;
    String dayFormat;
    String monthFormat;
    public DateOfBirth(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        dayFormat = String.valueOf(day);
        monthFormat = String.valueOf(month);
        if (day < 10) {
            dayFormat = "0"+day;
        }
        if (month < 10) {
            monthFormat = "0"+month;
        }
    }

    public String toString() {
        return String.valueOf(year)+monthFormat+dayFormat;
    }
}
