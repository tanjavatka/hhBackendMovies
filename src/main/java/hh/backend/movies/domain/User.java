package hh.backend.movies.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "appuser")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  @NotNull(message = "Name is required!")
  private String name;

  @NotNull(message = "Username is required!")
  private String username;

  @NotNull(message = "Password is required!")
  private String password;

  private String role; // USER / ADMIN ??

  // CONSTRUCTOR
  public User() {
  }

  public User(String name, String username, String password, String role) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  // SETTERS

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(String role) {
    this.role = role;
  }

  // GETTERS
  public Long getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  // TOSTRING
  @Override
  public String toString() {
    return "User (userId: " + userId + "), name: " + name + ", username: " + username + ", password: " + password
        + ", role: " + role + ".";
  }

}
