package hh.backend.movies.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;
import hh.backend.movies.domain.MyMovieReviewRepository;
import jakarta.validation.Valid;

@Controller
public class MovieController {
  private MovieRepository movieRepository;
  private MyMovieReviewRepository reviewRepository;

  public MovieController(MovieRepository movieRepo, MyMovieReviewRepository revRepo) {
    this.movieRepository = movieRepo;
    this.reviewRepository = revRepo;

  }

  // list all movies from database
  @GetMapping({ "/movies", "/" })
  public String getMovies(Model model) {

    model.addAttribute("movies", movieRepository.findAll());

    return "movies"; // movies.html
  }

  // Edit a movie
  @GetMapping("/editmovie/{id}")
  public String editMovie(@PathVariable("id") Long movieId, Model model) {

    model.addAttribute("movie", movieRepository.findById(movieId));

    return "editmovie"; // editmovie.html
  }

  // Save a movie
  @PostMapping("/savemovie")
  public String saveMovie(@Valid Movie movie, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "addmovie"; // jos virhe lomakkeen täytössä -> palaa lomakkeeseen
    }

    movieRepository.save(movie);

    return "redirect:/movies"; // movies.html
  }

  // addmovie form aukee
  @GetMapping("/addmovie")
  public String addMovie(Model model) {

    model.addAttribute("movie", new Movie());
    return "addmovie"; // addmovie.html
  }

  // delete movie by id
  @GetMapping("/deletemovie/{id}")
  public String deleteMovie(@PathVariable("id") Long movieId, RedirectAttributes redirectAttributes) {

    // tarkistetaan onko elokuvalla arvosteluja
    if (reviewRepository.existsByMovie_MovieId(movieId)) {
      redirectAttributes.addFlashAttribute("error", "Cannot delete movie with reviews");
      return "redirect:/movies";
    }

    movieRepository.deleteById(movieId);

    return "redirect:/movies"; // movies.html
  }
}
