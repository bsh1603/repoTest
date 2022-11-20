package caps.testing.dto.chat;

import caps.testing.domain.chat.Room;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

    private String name;

    public Room toRoom(){
        return Room.builder()
                .name(name)
                .build();
    }

}
