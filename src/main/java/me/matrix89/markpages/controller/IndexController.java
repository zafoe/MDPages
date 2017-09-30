package me.matrix89.markpages.controller;

import me.matrix89.markpages.model.PageModel;
import me.matrix89.markpages.repository.PageRepository;
import me.matrix89.markpages.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Logger logger;

    @GetMapping("/")
    public String index(Model m, Principal principal) {
        if (principal != null) {
            m.addAttribute("pages", pageRepository.findAll());
        } else {
            m.addAttribute("pages", pageRepository.getAllByVisibility("everyone"));
        }

        return "index";
    }

    @GetMapping("/test")
    public @ResponseBody
    List<PageModel> test() {
        return pageRepository.getAllByMaintainer_Id(1);
    }

    @GetMapping("/{a}")
    public String mdPage(@PathVariable String a, Model model, Principal principal) {
        PageModel p = pageRepository.getByName(a);
        if (p == null) {
            return "redirect:/";
        }

        if (principal == null && p.getVisibility().equals("authorized")) {
            return "redirect:/login";
        }

        model.addAttribute("page_title", p.getName());
        model.addAttribute("content", p.getContent());
        return "mdPage";
    }

}