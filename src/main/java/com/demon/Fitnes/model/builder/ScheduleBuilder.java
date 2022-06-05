package com.demon.Fitnes.model.builder;

import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.model.Service;

import java.sql.Time;
import java.time.LocalTime;

public final class ScheduleBuilder {
    private Long id;
    private Service service;
    private Coach coach;
    private LocalTime startTime;
    private LocalTime endTime;
    private String weekday;
    private Integer groupNumber;

    private ScheduleBuilder() {
    }

    public static ScheduleBuilder aSchedule() {
        return new ScheduleBuilder();
    }

    public ScheduleBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ScheduleBuilder service(Service service) {
        this.service = service;
        return this;
    }

    public ScheduleBuilder coach(Coach coach) {
        this.coach = coach;
        return this;
    }

    public ScheduleBuilder startTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public ScheduleBuilder endTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public ScheduleBuilder weekday(String weekday) {
        this.weekday = weekday;
        return this;
    }

    public ScheduleBuilder groupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public Schedule build() {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        schedule.setService(service);
        schedule.setCoach(coach);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setWeekday(weekday);
        schedule.setGroupNumber(groupNumber);
        return schedule;
    }
}
