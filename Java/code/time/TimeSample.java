package time;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeSample {
    public static void main(String[] args) {
        TimeSample sample = new TimeSample();
        sample.printDayOfWeek();
        sample.printDauOfWeekOfLocales();
    }

    private void printDayOfWeek() {
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        Locale locale = Locale.getDefault();
        for (DayOfWeek day : dayOfWeeks) {
            System.out.print(day.getDisplayName(TextStyle.FULL, locale) + " ");
            System.out.print(day.getDisplayName(TextStyle.SHORT, locale) + " ");
            System.out.println(day.getDisplayName(TextStyle.NARROW, locale));
        }
    }

    private void printDauOfWeekOfLocales() {
        DayOfWeek day = DayOfWeek.SUNDAY;
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.print(locale.getCountry() + " ");
            System.out.print(day.getDisplayName(TextStyle.FULL, locale));
            System.out.print(day.getDisplayName(TextStyle.SHORT, locale));
            System.out.println(day.getDisplayName(TextStyle.NARROW, locale));
        }
    }

}
