package com.demon.Fitnes.service.impl;

import com.demon.Fitnes.repository.ClientRepository;
import com.demon.Fitnes.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

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
            model.addAttribute("isAuth", false);
            return false;
        } else {
            model.addAttribute("isAuth", true);
            model.addAttribute("client", clientRepository.findByLogin(login).get());
            return true;
        }

    }

    @Override
    public boolean isUserAdmin(HttpSession session, Model model) {
        if (isUserAuthored(session, model)) {
            if (clientRepository.findAdminByLogin((String) session.getAttribute("login")).isPresent()) {
                model.addAttribute("isAdmin", true);
                session.setAttribute("isAdmin", true);
                return true;
            } else {
                model.addAttribute("isAdmin", false);
                session.setAttribute("isAdmin", false);
                return false;
            }
        } else {
            return false;
        }
    }
}
