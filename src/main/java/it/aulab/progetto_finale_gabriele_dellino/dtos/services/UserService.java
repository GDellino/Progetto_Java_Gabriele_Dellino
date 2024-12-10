package it.aulab.progetto_finale_gabriele_dellino.dtos.services;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.aulab.progetto_finale_gabriele_dellino.dtos.UserDto;
import it.aulab.progetto_finale_gabriele_dellino.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    void saveUser(UserDto userDto, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response);
    User findUserByEmail(String email); 
}
