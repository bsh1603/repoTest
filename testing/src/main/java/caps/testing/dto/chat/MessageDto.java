package caps.testing.dto.chat;

import caps.testing.domain.chat.Message;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private String content;

    public Message toMessage(){
        return Message.builder()
                .content(content)
                .build();
    }
}
