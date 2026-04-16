package hh.backend.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.movies.domain.Movie;
import hh.backend.movies.domain.MovieRepository;
import hh.backend.movies.domain.MyMovieReview;
import hh.backend.movies.domain.MyMovieReviewRepository;
import hh.backend.movies.domain.RatingEnum;
import hh.backend.movies.domain.User;
import hh.backend.movies.domain.UserRepository;
import hh.backend.movies.domain.WatchingStatus;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public CommandLineRunner createDemoRows(MovieRepository movieRepo, UserRepository userRepo,
			MyMovieReviewRepository myreviewRepo) {
		return (args) -> {

			Movie movie = movieRepo.save(new Movie("Harry Potter", 2001, "Fiction"));
			Movie movie2 = movieRepo.save(new Movie("Rottatouille", 2008, "Animation"));

			User user = userRepo.save(new User("Tanja", "tn", "**32*", "user"));

			myreviewRepo.save(new MyMovieReview(movie, RatingEnum.Five, WatchingStatus.Watched, "Super hyvä"));
			myreviewRepo.save(new MyMovieReview(movie2, RatingEnum.Five, WatchingStatus.Watched, "Hauska"));

		};
	}
}