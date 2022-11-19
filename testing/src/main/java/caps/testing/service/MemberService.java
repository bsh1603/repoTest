package caps.testing.service;

import caps.testing.domain.Member;
import caps.testing.domain.Team;
import caps.testing.dto.member.ManagerSignUpDto;
import caps.testing.dto.member.MemberSignInRequestDto;
import caps.testing.dto.member.MemberSignUpRequestDto;
import caps.testing.dto.token.TokenResponseDto;
import caps.testing.exception.MemberException;
import caps.testing.exception.MemberExceptionType;
import caps.testing.jwt.JwtTokenProvider;
import caps.testing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TeamService teamService;

    @Transactional
    public Long join_worker(MemberSignUpRequestDto memberSignUpRequestDto) throws MemberException {
        validateDuplicateMember(memberSignUpRequestDto);
        String authentication_code = memberSignUpRequestDto.getAuthentication_code();

        HashSet<String> allCode = memberRepository.findAllCode();

        if(!allCode.contains(authentication_code)){
            log.info("찾지 못함");
            throw new IllegalStateException("코드를 다시 확인해주세요");
        }

        memberRepository.findAllByCodeLike(authentication_code);

        Member manager = memberRepository.findAllByCodeLike(authentication_code).get(0);
        Member member = memberRepository.save(memberSignUpRequestDto.toMember());
        member.setTeam(manager.getTeam());
        member.setTeam_name(manager.getTeam_name());
        member.encodePassword(passwordEncoder);

        return member.getId();
    }

    @Transactional
    public Long join_manager(ManagerSignUpDto managerSignUpDto){
        validateDuplicateManager(managerSignUpDto);

        String team_name = managerSignUpDto.getTeam_name();
        Team setTeam = Team.builder().name(team_name).build();
        Team registered = teamService.register(setTeam);

        Member member = memberRepository.save(managerSignUpDto.toManager());
        member.setTeam(registered);
        member.encodePassword(passwordEncoder);
        member.encodeAuthentication(passwordEncoder);
        return member.getId();
    }

    @Transactional
    public TokenResponseDto login(MemberSignInRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_SIGNUP_EMAIL));
        validateMatchedPassword(requestDto.getPwd(), member.getPwd());

        //TODO : Access Token 과 Refresh Token 을 생성합니다.
        String accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getAdmin().name());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        //TODO : Refresh Token 을 DB에 저장합니다.
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Long findMyId(Member member){
        return member.getId();
    }

    public List<Member> findAllMyTeam(Long id){
        return memberRepository.findAllMyTeam(id);
    }

    public Long findMyTeamId(Long id){
        return memberRepository.findMyTeamId(id);
    }

    public void validateDuplicateMember(MemberSignUpRequestDto memberSignUpRequestDto){
        Optional<Member> findMembers = memberRepository.findByEmail(memberSignUpRequestDto.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 가입한 회원입니다.");
        }
    }

    public void validateDuplicateManager(ManagerSignUpDto managerSignUpDto) {
        Optional<Member> findMembers = memberRepository.findByEmail(managerSignUpDto.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 가입한 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword){
        if(!passwordEncoder.matches(validPassword, memberPassword)){
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }
    }

    private boolean matchedAuthentication(String validCode, String memberCode){
        if(validCode.equals(memberCode)){
            return true;
        }
        return false;
    }
}
