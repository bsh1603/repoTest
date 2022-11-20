package caps.testing.dto.member;

import caps.testing.domain.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModifyDto {

    private String name;
    private String email;

    public Member toModify(){
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }
}
