package com.volvo.vcc.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CongestionTaxCalculator {


    public int getTax(List<LocalDateTime> dateTimeList) {
        if (dateTimeList == null || dateTimeList.isEmpty()) {
            return 0;
        }

        dateTimeList.sort(LocalDateTime::compareTo);
        LocalDateTime intervalStart = dateTimeList.get(0);
        int totalFee = 0;
        int currentMaxFee = 0;

        for (int i = 0; i < dateTimeList.size(); i++) {
            LocalDateTime dateTime = dateTimeList.get(i);
            int nextFee = getTollFee(dateTime);

            long minutes = ChronoUnit.MINUTES.between(intervalStart, dateTime);

            if (minutes > 60) {
                totalFee += currentMaxFee;
                intervalStart = dateTime; // Update intervalStart for the next interval
                currentMaxFee = nextFee; // Reset currentMaxFee for the new interval
            } else {
                if (nextFee > currentMaxFee) {
                    currentMaxFee = nextFee;
                }
            }
        }

        // Add the maximum fee of the last interval
        totalFee += currentMaxFee;

        if (totalFee > 60) totalFee = 60;
        return totalFee;
    }



    public int getTollFee(LocalDateTime date) {
        if (isTollFreeDate(date)) return 0;

        int hour = date.getHour();
        int minute = date.getMinute();

        if (hour == 6 && minute >= 0 && minute <= 29) return 8;
        else if (hour == 6 && minute >= 30 && minute <= 59) return 13;
        else if (hour == 7 && minute >= 0 && minute <= 59) return 18;
        else if (hour == 8 && minute >= 0 && minute <= 29) return 13;
        else if (hour >= 8 && minute >= 30 && minute <= 59) return 8;
        else if (hour >= 9 && hour <= 14 && minute <= 59) return 8;
        else if (hour == 15 && minute >= 0 && minute <= 29) return 13;
        else if (hour == 15 && minute >= 30 && minute <= 59) return 18;
        else if (hour == 16 && minute >= 0 && minute <= 59) return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59) return 13;
        else if (hour == 18 && minute >= 0 && minute <= 29) return 8;
        else return 0;
    }

    private boolean isTollFreeDate(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        int year = dateTime.getYear();
        Month month = dateTime.getMonth();
        int dayOfMonth = dateTime.getDayOfMonth();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }

        if (year == 2013) {
            if ((month == Month.JANUARY && dayOfMonth == 1) ||
                    (month == Month.MARCH && (dayOfMonth == 28 || dayOfMonth == 29)) ||
                    (month == Month.APRIL && (dayOfMonth == 1 || dayOfMonth == 30)) ||
                    (month == Month.MAY && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9)) ||
                    (month == Month.JUNE && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) ||
                    (month == Month.JULY) ||
                    (month == Month.NOVEMBER && dayOfMonth == 1) ||
                    (month == Month.DECEMBER && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {
                return true;
            }
        }

        return false;
    }

}
