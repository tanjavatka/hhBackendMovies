package hh.backend.movies.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);

  // onko käyttäjä jo varattu
  boolean existsByUsername(String username);
}
