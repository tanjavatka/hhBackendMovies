package hh.backend.movies.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.backend.movies.domain.User;
import hh.backend.movies.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserAuthController {

  private UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserAuthController(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  // näytetään register -formi
  @GetMapping("/register")
  public String registerForm(Model model) {

    model.addAttribute("user", new User());

    return "register"; // register.html
  }

  // tallennetaan uusi USER
  @PostMapping("/register")
  public String saveRegister(@Valid @ModelAttribute User user, BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    // tarkistetaan tuleeko virheitä lomakkeen täytössä
    if (bindingResult.hasErrors()) {
      return "register"; // jos virhe lomakkeen täytössä -> palaa lomakkeeseen
    }

    // tarkistetaan onko username jo käytössä -> virheilmoitus
    if (userRepository.existsByUsername(user.getUsername())) {
      redirectAttributes.addFlashAttribute("error", "Username already exists");
      return "redirect:/register";
    }

    user.setRole("ROLE_USER"); // default rooli

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);

    return "redirect:/login";
  }
}
