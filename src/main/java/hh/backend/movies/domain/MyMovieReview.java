package hh.backend.movies.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class MyMovieReview {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long reviewId;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "userId")
  private User user;

  @NotNull(message = "Required - Select a movie!")
  @ManyToOne
  @JoinColumn(name = "movieId")
  private Movie movie;

  @Enumerated(EnumType.STRING)
  private RatingEnum rating; // 0-5

  @NotNull(message = "Reguired - Select movie status!")
  @Enumerated(EnumType.STRING)
  private WatchingStatus status; // watched / not watched

  private String comment; // esim. Aivan ihana elokuva | Kamala elokuva

  // CONSTRUCTORS
  public MyMovieReview() {
  }

  public MyMovieReview(Movie movie, RatingEnum rating, WatchingStatus status, String comment) {
    this.movie = movie;
    this.rating = rating;
    this.status = status;
    this.comment = comment;
  }

  public MyMovieReview(User user, Movie movie, RatingEnum rating, WatchingStatus status,
      String comment) {
    this.user = user;
    this.movie = movie;
    this.rating = rating;
    this.status = status;
    this.comment = comment;
  }

  // SETTERS
  public void setReviewId(Long reviewId) {
    this.reviewId = reviewId;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public void setRating(RatingEnum rating) {
    this.rating = rating;
  }

  public void setStatus(WatchingStatus status) {
    this.status = status;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  // GETTERS
  public Long getReviewId() {
    return reviewId;
  }

  public User getUser() {
    return user;
  }

  public Movie getMovie() {
    return movie;
  }

  public RatingEnum getRating() {
    return rating;
  }

  public WatchingStatus getStatus() {
    return status;
  }

  public String getComment() {
    return comment;
  }

  // TOSTRING

  // @Override
  // public String toString() {
  // return "User's MovieReview (" + reviewId + "), rating: " + rating
  // + ", status: " + status + ", comment: " + comment + ".";
  // }

  @Override
  public String toString() {
    return "User's MovieReview (" + reviewId + "), user: " + user + ", movie:" +
        movie + ", rating: " + rating + ", status: " + status + ", comment: " + comment + ".";
  }

}
