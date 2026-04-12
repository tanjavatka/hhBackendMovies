package hh.backend.movies.web;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.backend.movies.domain.MyMovieReview;
import hh.backend.movies.domain.MyMovieReviewRepository;
import hh.backend.movies.domain.RatingEnum;
import hh.backend.movies.domain.WatchingStatus;

@Controller
public class MyMoviesController {

  private final MovieRepository movieRepository;
  private MyMovieReviewRepository myMovieRepo;

  public MyMoviesController(MyMovieReviewRepository myMovieReviewRepository, MovieRepository movieRepository) {
    this.myMovieRepo = myMovieReviewRepository;
    this.movieRepository = movieRepository;
  }

  // Listaa kaikki arvostelut elokuvan tietoineen
  @GetMapping({ "/mymovies", "/" })
  public String getMyMovies(Model model) {
    model.addAttribute("mymovies", myMovieRepo.findAll());
    return "mymovies"; // mymovies.html
  }

  // Edit a movie review
  @GetMapping("/editreview/{id}")
  public String editMovieReview(@PathVariable("id") Long reviewId, Model model) {

    model.addAttribute("moviereview", myMovieRepo.findById(reviewId));

    return "editreview"; // editreview.html
  }

  // Save a movie review
  @PostMapping("/savereview")
  public String saveMovieReview(@RequestParam Long movieId, MyMovieReview myMovieReview) {

    Movie movie = movieRepository.findById(movieId).get();
    myMovieReview.setMovie(movie);

    myMovieRepo.save(myMovieReview);

    return "redirect:/mymovies"; // mymovies.html
  }

  @GetMapping("/addmoviereview")
  public String addMovieReview(Model model) {

    model.addAttribute("moviereview", new MyMovieReview());
    model.addAttribute("movies", movieRepository.findAll());
    model.addAttribute("ratings", RatingEnum.values());
    model.addAttribute("statuses", WatchingStatus.values());

    return "addmyreview"; // addmyreview.html
  }
}
