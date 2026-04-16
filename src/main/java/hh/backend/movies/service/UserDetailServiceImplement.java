package hh.backend.movies.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.backend.movies.domain.User;
import hh.backend.movies.domain.UserRepository;

@Service
public class UserDetailServiceImplement implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailServiceImplement(UserRepository appUserRepository) {
    this.userRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User currentUser = userRepository.findByUsername(username);

    UserDetails user = new org.springframework.security.core.userdetails.User(
        currentUser.getUsername(),
        currentUser.getPassword(),
        AuthorityUtils.createAuthorityList(currentUser.getRole()));

    return user;
  }
}