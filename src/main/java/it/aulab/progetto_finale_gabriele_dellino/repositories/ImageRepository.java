package it.aulab.progetto_finale_gabriele_dellino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.aulab.progetto_finale_gabriele_dellino.models.Image;
import jakarta.transaction.Transactional;

public interface ImageRepository extends JpaRepository<Image, Long>{

    @Transactional
    void deleteByPath(String imageUrl);
}
