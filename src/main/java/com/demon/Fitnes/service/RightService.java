package com.demon.Fitnes.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface RightService {

    boolean isUserAuthored(HttpSession session, Model model);
    boolean isUserAdmin(HttpSession session, Model model);
}
