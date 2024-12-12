package it.aulab.progetto_finale_gabriele_dellino.dtos;

import java.time.LocalDate;

import it.aulab.progetto_finale_gabriele_dellino.models.Category;
import it.aulab.progetto_finale_gabriele_dellino.models.Image;
import it.aulab.progetto_finale_gabriele_dellino.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String subtitle;
    private String body;
    private LocalDate publishDate;
    private User user;
    private Category category;
    private Image image;
}