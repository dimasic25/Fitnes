package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.model.Schedule;
import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.repository.CoachRepository;
import com.demon.Fitnes.repository.ScheduleRepository;
import com.demon.Fitnes.repository.ServiceRepository;
import com.demon.Fitnes.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ServiceRepository serviceRepository;
    private final CoachRepository coachRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ServiceRepository serviceRepository, CoachRepository coachRepository) {
        this.scheduleRepository = scheduleRepository;
        this.serviceRepository = serviceRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public List<Schedule> getSllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAllSchedules();

        for (Schedule schedule:
             schedules) {
            Service service = serviceRepository.findById(schedule.getService().getId()).get();
            Coach coach = coachRepository.findByLogin(schedule.getCoach().getLogin()).get();

            schedule.setService(service);
            schedule.setCoach(coach);
        }

        return schedules;
    }

    @Override
    public List<Schedule> getClientSchedules(String clientLogin) {
        List<Schedule> clientSchedules = scheduleRepository.findClientSchedules(clientLogin);

        if (clientSchedules.isEmpty()) {
            clientSchedules = scheduleRepository.findAllSchedules();
        }

        for (Schedule schedule:
                clientSchedules) {
            Service service = serviceRepository.findById(schedule.getService().getId()).get();
            schedule.setService(service);

            Coach coach = coachRepository.findByLogin(schedule.getCoach().getLogin()).orElse(null);
            schedule.setCoach(coach);
        }

        return clientSchedules;
    }

    @Override
    public List<Integer> getGroupNumbers() {
        return scheduleRepository.findAllGroupNumbers();
    }

}
