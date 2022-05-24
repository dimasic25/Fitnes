package com.demon.Fitnes.converter;

public class WeekdayConverter {

    public static String fromInt(Integer weekday) throws Exception {
        switch (weekday) {
            case 1 -> {
                return "Понедельник";
            }
            case 2 -> {
                return "Вторник";
            }
            case 3 -> {
                return "Среда";
            }
            case 4 -> {
                return "Четверг";
            }
            case 5 -> {
                return "Пятница";
            }
            case 6 -> {
                return "Суббота";
            }
            case 7 -> {
                return "Воскресенье";
            }
            default -> throw new Exception("Не удалось конвертировать день недели!");
        }
    }
}
