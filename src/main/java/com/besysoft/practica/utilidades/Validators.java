package com.besysoft.practica.utilidades;

import java.time.LocalDate;

public class Validators {



    public static boolean isDateRight(String date){

        boolean digits=date.matches("^\\d{8}$");

        int day=Integer.parseInt(date.substring(0,2)) ;
        int month=Integer.parseInt(date.substring(2,4));
        int year=Integer.parseInt(date.substring(4,8));
        boolean rightDayValue=day>0 && day<=31;
        boolean rightMonthValue=month>0 && month<=12;
        boolean rigthYearValue=year>1900 && year<= LocalDate.now().getYear();

        return digits && rightDayValue && rightMonthValue && rigthYearValue;
    }
}
