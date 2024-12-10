package it.aulab.progetto_finale_gabriele_dellino.repositories;

import org.springframework.data.repository.ListCrudRepository;

import it.aulab.progetto_finale_gabriele_dellino.models.Category;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {

}
