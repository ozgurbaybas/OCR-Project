package com.ozgurbaybas.OnlineCourseRegistration.Models;

public enum EnumDay {
    DAY_MONDAY("monday"),
    DAY_TUESDAY("tuesday"),
    DAY_WEDNESDAY("wednesday"),
    DAY_THURSDAY("thursday"),
    DAY_FRIDAY("friday");

    public final String dayName;

    EnumDay(String name) {
        this.dayName = name;
    }

    public static EnumDay getDay(String name) {
        for (EnumDay enumDay : EnumDay.values()) {
            if (enumDay.dayName.equals(name)) {
                return enumDay;
            }
        }
        throw new RuntimeException("Day is not found.");
    }

    public String getDayName() {
        return dayName;
    }
}
