package ovh.kocproz.markpages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ovh.kocproz.markpages.data.model.UserModel;
import ovh.kocproz.markpages.data.repository.PageRepository;
import ovh.kocproz.markpages.data.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String admin(Model model,
                        @RequestParam(defaultValue = "1") int pagesPage,
                        @RequestParam(defaultValue = "1") int usersPage) {
        model.addAttribute("pagesPage", pagesPage);
        model.addAttribute("pages", pageRepository.findAll(
                new PageRequest(pagesPage - 1, 10, Sort.Direction.ASC, "name")
        ));

        model.addAttribute("usersPage", usersPage);
        model.addAttribute("users", userRepository.findAll(
                new PageRequest(usersPage - 1, 10, Sort.Direction.ASC, "username")
        ));
        return "admin";
    }

    @PostMapping("/useradd")
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (!username.isEmpty() && !password.isEmpty() && !role.isEmpty()) {
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userRepository.save(user);
        }
        return "redirect:/admin";
    }

}