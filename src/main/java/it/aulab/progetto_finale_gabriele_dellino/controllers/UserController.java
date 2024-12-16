package it.aulab.progetto_finale_gabriele_dellino.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.aulab.progetto_finale_gabriele_dellino.dtos.ArticleDto;
import it.aulab.progetto_finale_gabriele_dellino.dtos.UserDto;
import it.aulab.progetto_finale_gabriele_dellino.models.Article;
import it.aulab.progetto_finale_gabriele_dellino.models.User;
import it.aulab.progetto_finale_gabriele_dellino.repositories.ArticleRepository;
import it.aulab.progetto_finale_gabriele_dellino.repositories.CareerRequestRepository;
import it.aulab.progetto_finale_gabriele_dellino.services.ArticleService;
import it.aulab.progetto_finale_gabriele_dellino.services.CategoryService;
import it.aulab.progetto_finale_gabriele_dellino.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public String home(Model viewModel) {

        List<ArticleDto> articles = new ArrayList<ArticleDto>();
        for(Article article: articleRepository.findByIsAcceptedTrue()){
            articles.add(modelMapper.map(article,ArticleDto.class));
        }

        Collections.sort(articles,Comparator.comparing(ArticleDto::getPublishDate).reversed());

        List<ArticleDto> lastThreeArticles = articles.stream().limit(3).collect(Collectors.toList());

        viewModel.addAttribute("articles",lastThreeArticles);

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes,
                                HttpServletRequest request,HttpServletResponse response ){
                                    
        User existingUser= userService.findUserByEmail(userDto.getEmail());
        if (existingUser!=null && existingUser.getEmail()!=null&& !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"there is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user",userDto);
            return"auth/register";
        }
        userService.saveUser(userDto,redirectAttributes,request,response);
        redirectAttributes.addFlashAttribute("successMessage","Registrazione avvenuta!");
        return"redirect:/";
    }

    @GetMapping("/search/{id}")
    public String userArticleSearch(@PathVariable("id") Long id, Model viewmModel){
        User user = userService.find(id);
        viewmModel.addAttribute("title","Tutti gli articoli trovati per utente "+ user.getUsername());

        List<ArticleDto> articles = articleService.searchByAuthor(user);

        List<ArticleDto> acceptedArticles = articles.stream().filter(article -> Boolean.TRUE.equals(article.getIsAccepted())).collect(Collectors.toList());

        viewmModel.addAttribute("articles",acceptedArticles);

        return "article/articles";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model viewmModel){
        viewmModel.addAttribute("title","Richiesta ricevuta");
        viewmModel.addAttribute("request", careerRequestRepository.findByIsCheckedFalse());
        viewmModel.addAttribute("categories",categoryService.readAll());
        return "admin/dashboard";
    }

    @GetMapping("/revisor/dashboard")
    public String revisorDashboard(Model viewModel){
        viewModel.addAttribute("title","Articoli da revisionare");
        viewModel.addAttribute("articles", articleRepository.findByIsAcceptedIsNull());
        return "revisor/dashboard";
    }
}
