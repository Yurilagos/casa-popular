package br.com.dginx.util;

import java.time.LocalDate;
import java.time.Period;

public class Utils {

    public static final Double DOUBLE_0 = Double.valueOf(0d);
    public static final Integer INT_0 = Integer.valueOf(0);
    public static final Integer INT_1 = Integer.valueOf(1);
    public static final Integer INT_2 = Integer.valueOf(2);
    public static final Integer INT_3 = Integer.valueOf(3);
    public static final Integer INT_5 = Integer.valueOf(5);
    public static final Integer INT_6 = Integer.valueOf(6);
    public static final Integer INT_900 = Integer.valueOf(900);
    public static final Integer INT_1500 = Integer.valueOf(1500);


    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
