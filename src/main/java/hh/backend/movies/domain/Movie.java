package hh.backend.movies.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long movieId;

  private String title;
  private String genre;

  @Column(name = "publicationYear")
  private int year;

  // CONSTRUCTORS
  public Movie() {
  }

  public Movie(String title, int year, String genre) {
    this.title = title;
    this.year = year;
    this.genre = genre;
  }

  // SETTERS
  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  // GETTERS
  public Long getMovieId() {
    return movieId;
  }

  public String getTitle() {
    return title;
  }

  public int getYear() {
    return year;
  }

  public String getGenre() {
    return genre;
  }

  // TOSTRING
  @Override
  public String toString() {
    return "Movie (" + movieId + "), title:" + title + ", year:" + year + ", genre:" + genre + ".";
  }

}
