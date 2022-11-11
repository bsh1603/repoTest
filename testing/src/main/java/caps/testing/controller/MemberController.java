package caps.testing.controller;

import caps.testing.domain.Administration;
import caps.testing.domain.Member;
import caps.testing.domain.User;
import caps.testing.dto.*;
import caps.testing.form.AccountForm;
import caps.testing.repository.MemberRepository;
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

    private final MemberService memberService;
    private final MemberRepository memberRepository;

//    @PostMapping("/api/users")
//    public Member member(){
//        System.out.println("UserApiController 진입 성공");
//
//        Member member = new Member(1L, "테스트", "email@email.com", "pwd",  Administration.ROLE_WORKER, "01010101" ,"auth코드", "팀이름");
//        return member;
//    }

    @PostMapping("/api/signup/worker")
    public Long signUp_worker(@RequestBody @Valid MemberSignUpRequestDto memberSignUpRequestDto){
        return memberService.join_worker(memberSignUpRequestDto);
    }

    @PostMapping("/api/signup/manager")
    public Long singUp_manger(@RequestBody @Valid ManagerSignUpDto managerSignUpDto){
        return memberService.join_manager(managerSignUpDto);
    }

    @PostMapping("/api/login")
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
