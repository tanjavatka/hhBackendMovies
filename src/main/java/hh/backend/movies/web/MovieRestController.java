package hh.backend.movies.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;

@RestController
@RequestMapping("/api/movies") // kaikkien rest endpointtien alku
public class MovieRestController {

  private MovieRepository movieRepository;

  // constructor injection
  public MovieRestController(MovieRepository movieRepo) {
    this.movieRepository = movieRepo;
  }

  // Hae kaikki elokuvat -> endpoint "/api/movies"
  @GetMapping
  public List<Movie> getMovies() {
    return (List<Movie>) movieRepository.findAll();
  }

  // Hae elokuva id:llä
  @GetMapping("/{id}")
  public Optional<Movie> getMovieById(@PathVariable("id") Long movieId) {
    return movieRepository.findById(movieId);
  }

  // Lisää - tallenna uusi elokuva
  @PostMapping
  public Movie saveNewMovie(Movie newMovie) {
    return movieRepository.save(newMovie);
  }

  // Poista elokuva id:llä
  @DeleteMapping("/{id}")
  public void deleteMovieById(@PathVariable Long movieId) {
    movieRepository.deleteById(movieId);
  }

  // Muokkaa elokuvaa
  @PutMapping("/{id}")
  public Movie updateMovie(@PathVariable("id") Long movieId, Movie updatedMovie) {

    // Asetetaan Id, jotta oikea elokuva päivittyy.
    updatedMovie.setMovieId(movieId);

    // Tallennetaan päivitetty elokuva DB:siin
    return movieRepository.save(updatedMovie);
  }
}
