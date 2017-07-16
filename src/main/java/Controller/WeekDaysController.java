package Controller;

import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

/**
 * Created by denizyalcin on 16/07/2017.
 */
public /*abstract*/ class WeekDaysController /*implements IWeekDays*/ {
    public String whichDay(Date givenDate) {

        SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE"); // the day of the week
        String weekDay = weekDayFormat.format(givenDate);

        return weekDay;
    }

    public int isWorkingDate(Date givenDate, String givenCurrency) {
        int addDaysForSettlement=99;

        String instructionDay = whichDay(givenDate);

        //String[] workWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        //String[] workWeekAEDorSAR = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};

        if (givenCurrency.equals("AED") || givenCurrency.equals("SAR")) {
            if (instructionDay.equals("Friday")) {
                addDaysForSettlement=2;
            }else if(instructionDay.equals("Saturday")){
                addDaysForSettlement=1;
            }else
                addDaysForSettlement=0;
        } else {
            if (instructionDay.equals("Saturday")) {
                addDaysForSettlement=2;
            }else if(instructionDay.equals("Sunday")){
                addDaysForSettlement=1;
            }else
                addDaysForSettlement=0;
        }
        return addDaysForSettlement;
    }
}