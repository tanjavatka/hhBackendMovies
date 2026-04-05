package hh.backend.movies.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.backend.movies.domain.MovieRepository;

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
}
