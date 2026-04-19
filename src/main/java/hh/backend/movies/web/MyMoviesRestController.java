package hh.backend.movies.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.movies.domain.MyMovieReview;
import hh.backend.movies.domain.MyMovieReviewRepository;

@RestController
@RequestMapping("/api/reviews")
public class MyMoviesRestController {

  private MyMovieReviewRepository reviewRepository;

  // constructor injection
  public MyMoviesRestController(MyMovieReviewRepository reviewRepo) {
    this.reviewRepository = reviewRepo;
  }

  // Hae kaikki arvostelut
  @GetMapping
  public List<MyMovieReview> getMyReviews() {
    return (List<MyMovieReview>) reviewRepository.findAll();
  }

  // Hae käyttäjän omat arvostelut
  @GetMapping("/user/{username}")
  public List<MyMovieReview> getUserReviews(@PathVariable String username) {
    return reviewRepository.findByUserUsername(username);
  }

  // Tallenna arvostelu
  @PostMapping
  public MyMovieReview saveReview(MyMovieReview review) {
    return reviewRepository.save(review);
  }

  // Poista arvostelu
  @DeleteMapping("/{id}")
  public void deleteReview(@PathVariable Long reviewId) {
    reviewRepository.deleteById(reviewId);
  }

  // Muokkaa arvostelua
  @PutMapping("/{id}")
  public MyMovieReview updateReview(@PathVariable("id") Long reviewId, MyMovieReview review) {
    review.setReviewId(reviewId);
    return reviewRepository.save(review);
  }

}
