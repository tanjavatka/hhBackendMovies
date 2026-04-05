package hh.backend.movies.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserMovieReview {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long reviewId;

  // private User user;
  // private Movie movie;

  private int rating; // 1-5
  private String status; // watched, watching, want to watch
  private String comment; // esim. Aivan ihana elokuva | Kamala elokuva

  // CONSTRUCTORS
  public UserMovieReview() {
  }

  public UserMovieReview(int rating, String status, String comment) {
    this.rating = rating;
    this.status = status;
    this.comment = comment;
  }

  // public UserMovieReview(User user, Movie movie, int rating, String status,
  // String comment) {
  // this.user = user;
  // this.movie = movie;
  // this.rating = rating;
  // this.status = status;
  // this.comment = comment;
  // }

  // SETTERS
  public void setRating(int rating) {
    this.rating = rating;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  // GETTERS
  public Long getReviewId() {
    return reviewId;
  }

  // public User getUser() {
  // return user;
  // }

  // public Movie getMovie() {
  // return movie;
  // }

  public int getRating() {
    return rating;
  }

  public String getStatus() {
    return status;
  }

  public String getComment() {
    return comment;
  }

  // TOSTRING

  @Override
  public String toString() {
    return "User's MovieReview (" + reviewId + "), rating: " + rating
        + ", status: " + status + ", comment: " + comment + ".";
  }
  // @Override
  // public String toString() {
  // return "User's MovieReview (" + reviewId + "), user: " + user + ", movie: " +
  // movie + ", rating: " + rating
  // + ", status: " + status + ", comment: " + comment + ".";
  // }

}
