package com.example.communications.web;



import com.example.communications.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {
    private UsersService usersService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("users", usersService.findAllDesc());
        return "hello";
    }
}
