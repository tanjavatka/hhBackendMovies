package hh.backend.movies.web;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.movies.domain.MyMovieReview;
import hh.backend.movies.domain.MyMovieReviewRepository;
import hh.backend.movies.domain.RatingEnum;
import hh.backend.movies.domain.User;
import hh.backend.movies.domain.UserRepository;
import hh.backend.movies.domain.WatchingStatus;
import jakarta.validation.Valid;

@Controller
public class MyMoviesController {

  private final MovieRepository movieRepository;
  private MyMovieReviewRepository myMovieRepo;
  private UserRepository userRepository;

  public MyMoviesController(MyMovieReviewRepository myMovieReviewRepository,
      MovieRepository movieRepository, UserRepository userRepo) {

    this.myMovieRepo = myMovieReviewRepository;
    this.movieRepository = movieRepository;
    this.userRepository = userRepo;
  }

  // Listaa kaikki arvostelut elokuvan tietoineen
  @GetMapping("/mymovies")
  public String getMyMovies(Model model, Principal principal) {

    String username = principal.getName();
    User user = userRepository.findByUsername(username);

    // jos admin → kaikki arvostelut näkyy
    if (user.getRole().equals("ROLE_ADMIN")) {
      model.addAttribute("mymovies", myMovieRepo.findAll());
    }
    // user -käyttäjä → vain omat arvostelut
    else {
      model.addAttribute("mymovies", myMovieRepo.findByUserUsername(username));
    }

    return "mymovies"; // mymovies.html
  }

  // Edit a movie review
  @GetMapping("/editreview/{id}")
  public String editMovieReview(@PathVariable("id") Long reviewId,
      Model model, Principal principal) {

    MyMovieReview review = myMovieRepo.findById(reviewId).orElse(null);

    // tarkistetaan onko käyttäjän oma arvostelu
    if (review == null || !review.getUser().getUsername().equals(principal.getName())) {
      return "redirect:/mymovies"; // mymovies.html
      // palaa mymovies sivulle sillä ei oikeutta muokata
    }

    model.addAttribute("moviereview", myMovieRepo.findById(reviewId).orElse(null));
    model.addAttribute("ratings", RatingEnum.values());
    model.addAttribute("statuses", WatchingStatus.values());

    return "editreview"; // editreview.html
  }

  // Save a new movie review
  @PostMapping("/savereview")
  public String saveReview(@Valid @ModelAttribute("moviereview") MyMovieReview myMovieReview,
      BindingResult bindingResult, Model model, Principal principal) {

    // VALIDATION: jos error formin täytössä
    // -> error viesti ilmoitus ja jää addmyreview formiin.
    if (bindingResult.hasErrors()) {
      model.addAttribute("movies", movieRepository.findAll());
      model.addAttribute("ratings", RatingEnum.values());
      model.addAttribute("statuses", WatchingStatus.values());

      return "addmyreview";
    }

    String username = principal.getName();
    User user = userRepository.findByUsername(username);

    // turvallisuus - editreview varten.
    if (myMovieReview.getReviewId() != null) {

      MyMovieReview existing = myMovieRepo.findById(myMovieReview.getReviewId()).orElse(null);

      if (existing == null ||
          !existing.getUser().getUsername().equals(username)) {

        return "redirect:/mymovies";
      }
    }

    // asetetaan aina käyttäjä (user)
    myMovieReview.setUser(user);

    myMovieRepo.save(myMovieReview);

    return "redirect:/mymovies"; // mymovies.html
  }

  // Add a new movie review
  @GetMapping("/addmoviereview")
  public String addMovieReview(Model model) {

    model.addAttribute("moviereview", new MyMovieReview());
    model.addAttribute("movies", movieRepository.findAll());
    model.addAttribute("ratings", RatingEnum.values());
    model.addAttribute("statuses", WatchingStatus.values());

    return "addmyreview"; // addmyreview.html
  }

  // Add a movie review by id => EI KÄYTÖSSÄ VIELÄ
  @GetMapping("/addmoviereview/{id}")
  public String addMovieReview(@PathVariable("id") Long movieId, Model model) {

    MyMovieReview review = new MyMovieReview();

    Movie movie = movieRepository.findById(movieId).orElse(null);
    review.setMovie(movie);

    model.addAttribute("moviereview", review);
    model.addAttribute("ratings", RatingEnum.values());
    model.addAttribute("statuses", WatchingStatus.values());

    return "addmyreview"; // addmyreview.html
  }

  // delete movie review by id
  @GetMapping("/deletereview/{id}")
  public String deleteReview(@PathVariable("id") Long reviewId, Principal principal) {

    MyMovieReview review = myMovieRepo.findById(reviewId).orElse(null);

    if (review != null && review.getUser().getUsername().equals(principal.getName())) {
      myMovieRepo.deleteById(reviewId);
    }

    return "redirect:/mymovies"; // mymovies.html
  }
}
