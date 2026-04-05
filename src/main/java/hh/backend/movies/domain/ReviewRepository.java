package hh.backend.movies.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<UserMovieReview, Long> {
  List<UserMovieReview> findByStatus(String status);
}
