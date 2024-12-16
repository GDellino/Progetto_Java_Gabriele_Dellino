package it.aulab.progetto_finale_gabriele_dellino.services;

public interface EmailService {
    void sendSimpleEmail(String to,String subject,String text);
}
