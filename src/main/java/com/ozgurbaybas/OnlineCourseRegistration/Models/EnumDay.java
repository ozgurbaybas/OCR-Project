package com.ozgurbaybas.OnlineCourseRegistration.Models;

public enum EnumDay {
    DAY_MONDAY("monday"),
    DAY_TUESDAY("tuesday"),
    DAY_WEDNESDAY("wednesday"),
    DAY_THURSDAY("thursday"),
    DAY_FRIDAY("friday");

    public final String dayName;

    EnumDay(String day) {
        this.dayName = day;
    }

    public static EnumDay getDay(String day) {
        for (EnumDay enumDay : EnumDay.values()) {
            if (enumDay.dayName.equals(day)) {
                return enumDay;
            }
        }
        throw new RuntimeException("Day is not found.");
    }

    public String getDayName() {
        return dayName;
    }
}
