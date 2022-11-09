package caps.testing.controller;

import caps.testing.domain.Member;
import caps.testing.dto.*;
import caps.testing.form.AccountForm;
import caps.testing.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @PostMapping("/member/join/worker")
    public Long signUp_worker(@RequestBody @Valid MemberSignUpRequestDto memberSignUpRequestDto){
        return memberService.join_worker(memberSignUpRequestDto);
    }

    @PostMapping("/member/join/manager")
    public Long singUp_manger(@RequestBody @Valid ManagerSignUpDto managerSignUpDto){
        return memberService.join_manager(managerSignUpDto);
    }

    @PostMapping("/member/login")
    public TokenResponseDto login(@RequestBody @Valid MemberSignInRequestDto requestDto) {
        return memberService.login(requestDto);
    }

    @GetMapping("/members")
    public List<Member> list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return members;
    }
}
