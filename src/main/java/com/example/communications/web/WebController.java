package com.example.communications.web;



import com.example.communications.dto.users.UsersSaveRequestDto;
import com.example.communications.service.PvpsService;
import com.example.communications.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class WebController {
    private UsersService usersService;
    private PvpsService pvpsService;

    // 회원가입 페이지
    @GetMapping("user/signup")
    public String dispSignup() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("user/signup")
    public String execSignup(UsersSaveRequestDto usersSaveRequestDto) {
        if(usersService.joinUser(usersSaveRequestDto).equals("none")){
            return "redirect:signupFailure";
        }
        return "redirect:login";
    }

    // 회원가입 실패 페이지
    @GetMapping("user/signupFailure")
    public String dispSignupFailure() {
        return "signupFailure";
    }

    // 로그인 페이지
    @GetMapping("user/login")
    public String dispLogin() {
        return "login";
    }

    // 로그인 결과 페이지
    @GetMapping("user/login/result")
    public String dispLoginResult() {
        return "loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("user/logout/result")
    public String dispLogout() {
        return "logout";
    }

    // 접근 거부 페이지
    @GetMapping("user/denied")
    public String dispDenied() {
        return "denied";
    }


    @GetMapping("test")
    public String test() {
        return "test";
    }

    // 랭크 페이지
    @GetMapping("rank")
    public String dispRank(Model model) {
        model.addAttribute("pvps", pvpsService.findAll());
        return "rank";
    }

    @GetMapping("/")
    public String dispMain() {
        return "main";
    }
    
    // 채팅 페이지
    @GetMapping("chat")
    public String dispChat() {
        return "chatClient";
    }
}
