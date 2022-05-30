package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Coach;
import com.demon.Fitnes.repository.CoachRepository;
import com.demon.Fitnes.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;

    @Autowired
    public CoachServiceImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach getCoach(String login) {
        return coachRepository.findByLogin(login)
                .orElseThrow();
    }

    @Override
    public List<Coach> getCoaches() {
        return coachRepository.findAllCoaches();
    }

    @Override
    public String addCoach(Coach coach) {
        return coachRepository.insert(coach);
    }

    @Override
    public void updateCoach(Coach coach) {
        coachRepository.update(coach);
    }
}
