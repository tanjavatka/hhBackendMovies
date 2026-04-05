package hh.backend.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;
import hh.backend.movies.domain.ReviewRepository;
import hh.backend.movies.domain.User;
import hh.backend.movies.domain.UserMovieReview;
import hh.backend.movies.domain.UserRepository;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public CommandLineRunner createDemoRows(MovieRepository movieRepo, UserRepository userRepo,
			ReviewRepository reviewRepo) {
		return (args) -> {

			movieRepo.save(new Movie("Harry Potter", 2001, "Fiction"));

			// userRepo.save(new User("Tanja", "tn", "**32*"));

			// reviewRepo.save(new UserMovieReview(5, "watched", "super hyvä"));
		};
	}
}