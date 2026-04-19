package hh.backend.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            // nämä sivut ei tarvitse loginia näkyäkseen. - "/style.css"
            .requestMatchers("/", "/movies", "/register", "/**/*.css").permitAll() // Enable css when logged out
            // vain kirjautuneet
            .requestMatchers("/mymovies/**").authenticated()
            // toimii vain admin käyttäjälle
            .requestMatchers("/deletemovie/**").hasRole("ADMIN")
            // .requestMatchers("/deleteUser/**").hasRole("ADMIN") -> ei toiminnassa vielä
            .anyRequest().authenticated())
        .formLogin(formlogin -> formlogin
            .defaultSuccessUrl("/mymovies", true)
            .permitAll())
        .logout(logout -> logout
            .logoutSuccessUrl("/movies")
            .permitAll());
    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}