package com.demon.Fitnes.service;

import com.demon.Fitnes.model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();

    List<Schedule> getClientSchedules(String clientLogin);

    List<Schedule> getSchedulesByService(Long serviceId);

    List<Integer> getGroupNumbers();
}
