package it.aulab.progetto_finale_gabriele_dellino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.aulab.progetto_finale_gabriele_dellino.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String name);
}
