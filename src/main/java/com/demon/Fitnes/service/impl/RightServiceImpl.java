package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.model.Client;
import com.demon.Fitnes.repository.ClientRepository;
import com.demon.Fitnes.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class RightServiceImpl implements RightService {

    private final ClientRepository clientRepository;

    @Autowired
    public RightServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean isUserAuthored(HttpSession session, Model model) {
        String login = (String) session.getAttribute("login");
        boolean isPresent = clientRepository.findByLogin(login).isPresent();

        if (!isPresent) {
            return false;
        } else {
            model.addAttribute("client", clientRepository.findByLogin(login).get());
            return true;
        }

    }

    @Override
    public boolean isUserAdmin(HttpSession session, Model model) {
        return isUserAuthored(session, model) && Objects.equals((String) session.getAttribute("login"), "admin");
    }
}
