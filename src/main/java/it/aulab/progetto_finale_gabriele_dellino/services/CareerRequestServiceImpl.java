package it.aulab.progetto_finale_gabriele_dellino.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.aulab.progetto_finale_gabriele_dellino.models.CareerRequest;
import it.aulab.progetto_finale_gabriele_dellino.models.Role;
import it.aulab.progetto_finale_gabriele_dellino.models.User;
import it.aulab.progetto_finale_gabriele_dellino.repositories.CareerRequestRepository;
import it.aulab.progetto_finale_gabriele_dellino.repositories.RoleRepository;
import it.aulab.progetto_finale_gabriele_dellino.repositories.UserRepository;

@Service
public class CareerRequestServiceImpl implements CareerRequestService{

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    @Override
    public boolean isRoleAlreadyAssigned(User user, CareerRequest carrerRequest) {
        List<Long> allUserIds = careerRequestRepository.findAllUserIds();

        if(!allUserIds.contains(user.getId())){
            return false;
        }

        List<Long> request = careerRequestRepository.findByUserId(user.getId());

        return request.stream().anyMatch(roleId -> roleId.equals(carrerRequest.getRole().getId()));
    }

    @Override
    public void save(CareerRequest careerRequest, User user) {
        careerRequest.setUser(user);
        careerRequest.setIsChecked(false);
        careerRequestRepository.save(careerRequest);

        emailService.sendSimpleEmail("adminAulabpost@admin.com","Richiesta per ruolo: "+ careerRequest.getRole().getName(),"C'è una nuova richiesta di collaborazione da parte di "+user.getUsername());
    }

    @Override
    public void careerAccepted(Long requestId) {
        CareerRequest request = careerRequestRepository.findById(requestId).get();

        User user = request.getUser();
        Role role = request.getRole();

        List<Role> roleUser = user.getRoles();
        Role newRole = roleRepository.findByName(role.getName());
        roleUser.add(newRole);

        user.setRoles(roleUser);
        userRepository.save(user);
        request.setIsChecked(true);
        careerRequestRepository.save(request);

        emailService.sendSimpleEmail(user.getEmail(), "Ruolo abilitato", "Ciao, la tua richiesta di collaborazione è stata accettata dalla nostra amministrazione");
    }

    @Override
    public CareerRequest find(Long id) {
        return careerRequestRepository.findById(id).get();
    }

}
