package it.aulab.progetto_finale_gabriele_dellino.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

@Controller
public class ExceptionHandlingController {

    @GetExchange("/error/{number}")
    public String accessDenied(@PathVariable int number, Model model){
        if(number==403){
            return "redirect:/?notAuthorized";
        }
        return "redirect:/";
    }
}
