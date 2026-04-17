package hh.backend.movies.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MyMovieReviewRepository extends CrudRepository<MyMovieReview, Long> {
  List<MyMovieReview> findByReviewId(Long reviewId);

  // tarkistus deletemovie kohtaa varten
  boolean existsByMovie_MovieId(Long movieId);
}
