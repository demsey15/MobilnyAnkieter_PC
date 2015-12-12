package bohonos.demski.mieldzioc.mobilnyankieter.utilities;

import java.util.GregorianCalendar;

/**
 * Created by Dominik on 2015-05-02.
 */
public class DateAndTimeService {

    /**
     * Jeżeli liczba jest jednocyfrowa dodaje wiodące zero przed nią
     *
     * @param number
     * @return
     */
    public static String addFirstZeros(int number) {
        return (number < 10 && number >= 0) ? "0" + number : String.valueOf(number);
    }

    public static String addFirstTwoZeros(int number) {
        if (number < 10 && number >= 0) {
            return "00" + number;
        } else if (number >= 10 && number < 100) {
            return "0" + number;
        } else return String.valueOf(number);
    }

    /**
     * @return zwraca obecną datę i godzinę w postaci:  "YYYY-MM-DD HH:MM:SS.SSS"
     */
    public static String getToday() {
        GregorianCalendar today = new GregorianCalendar();
        return today.get(GregorianCalendar.YEAR) + "-" +
                addFirstZeros(today.get(GregorianCalendar.MONTH) + 1) + "-" +
                addFirstZeros(today.get(GregorianCalendar.DATE))
                + " " + addFirstZeros(today.get(GregorianCalendar.HOUR_OF_DAY))
                + ":" + addFirstZeros(today.get(GregorianCalendar.MINUTE))
                + ":" + addFirstZeros(today.get(GregorianCalendar.SECOND)) + "." +
                addFirstTwoZeros(today.get(GregorianCalendar.MILLISECOND));
    }

    /**
     * @param date data do konwersji
     * @return zwraca zadaną datę i godzinę w postaci:  "YYYY-MM-DD HH:MM:SS.SSS"
     */
    public static String getDateAsDBString(GregorianCalendar date) {
        return date.get(GregorianCalendar.YEAR) + "-" +
                addFirstZeros(date.get(GregorianCalendar.MONTH) + 1) + "-" +
                addFirstZeros(date.get(GregorianCalendar.DATE))
                + " " + addFirstZeros(date.get(GregorianCalendar.HOUR_OF_DAY))
                + ":" + addFirstZeros(date.get(GregorianCalendar.MINUTE))
                + ":" + addFirstZeros(date.get(GregorianCalendar.SECOND)) + "." +
                addFirstTwoZeros(date.get(GregorianCalendar.MILLISECOND));
    }

    /**
     * Zwraca obiekt GregorianCalendar z zadanego stringa.
     * @param date data w formacie "dd-mm-yyyy hh:mm:ss".
     * @return obiekt GregorianCalendar lub null, jeśli podano błędny format.
     */
    public static GregorianCalendar getDateFromString(String date) {
        try {
            int day = Integer.valueOf(date.substring(0, 2));
            int month = Integer.valueOf(date.substring(3, 5));
            int year = Integer.valueOf(date.substring(6, 10));
            int hour = Integer.valueOf(date.substring(11, 13));
            int minute = Integer.valueOf(date.substring(14, 16));
            int second = Integer.valueOf(date.substring(17));

            return new GregorianCalendar(year, month - 1, day, hour, minute, second);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Zwraca obiekt GregorianCalendar z zadanego stringa.
     * @param date data w formacie "yyyy-mm-dd hh:mm:ss".  (miesiąc od 1 do 12)
     * @return obiekt GregorianCalendar lub null, jeśli podano błędny format.
     */
    public static GregorianCalendar getDateFromStringYYYYMMDDHHMMSS(String date) {
        try {
            int year = Integer.valueOf(date.substring(0, 4));
            int month = Integer.valueOf(date.substring(5, 7));
            int day = Integer.valueOf(date.substring(8, 10));
            int hour = Integer.valueOf(date.substring(11, 13));
            int minute = Integer.valueOf(date.substring(14, 16));
            int second = Integer.valueOf(date.substring(17, 19));

            return new GregorianCalendar(year, month - 1, day, hour, minute, second);
        } catch (Exception e) {
            return null;
        }
    }
}
