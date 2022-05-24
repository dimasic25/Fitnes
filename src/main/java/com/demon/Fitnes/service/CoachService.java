package com.demon.Fitnes.service;

import com.demon.Fitnes.model.Coach;

import java.util.List;

public interface CoachService {

    Coach getCoach(String login);

    List<Coach> getCoaches();
    String addCoach(Coach coach);
    void updateCoach(Coach coach);
}