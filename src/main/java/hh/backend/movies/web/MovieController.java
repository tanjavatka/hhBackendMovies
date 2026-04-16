package hh.backend.movies.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;
import jakarta.validation.Valid;

@Controller
public class MovieController {
  private MovieRepository movieRepository;

  public MovieController(MovieRepository movieRepo) {
    this.movieRepository = movieRepo;
  }

  // list all movies from database
  @GetMapping("/movies")
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

  @GetMapping("/addmovie")
  public String addMovie(Model model) {

    model.addAttribute("movie", new Movie());
    return "addmovie"; // addmovie.html
  }
}
