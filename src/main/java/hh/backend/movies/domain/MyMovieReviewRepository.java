package hh.backend.movies.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MyMovieReviewRepository extends CrudRepository<MyMovieReview, Long> {

  // Listaa kaikki arvostelut (Id:n mukaan)
  List<MyMovieReview> findByReviewId(Long reviewId);

  // listataan arvostelut käyttäjän mukaan
  List<MyMovieReview> findByUserUsername(String username);

  // tarkistus deletemovie kohtaa varten
  boolean existsByMovie_MovieId(Long movieId);
}
