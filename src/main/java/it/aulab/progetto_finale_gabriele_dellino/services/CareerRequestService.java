package it.aulab.progetto_finale_gabriele_dellino.services;

import it.aulab.progetto_finale_gabriele_dellino.models.CareerRequest;
import it.aulab.progetto_finale_gabriele_dellino.models.User;

public interface CareerRequestService {
    boolean isRoleAlreadyAssigned(User user, CareerRequest carrerRequest);
    void save(CareerRequest careerRequest, User user);
    void careerAccepted(Long requestId);
    CareerRequest find(Long id);
}
