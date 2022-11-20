package caps.testing.domain.chat;

import caps.testing.domain.Member;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "chat_room")
@Getter @Setter
@NoArgsConstructor
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ROOM_ID")
    private Long id;

    @Column(name = "CHAT_ROOM_NAME")
    private String name;

    @Column(name = "CHAT_ROOM_TEAM_ID")
    private Long teamId;

//    @OneToMany
//    @JsonManagedReference
//    private List<Member> memberList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "room")
//    @JsonManagedReference
//    private List<Message> messageList = new ArrayList<>();

    @Builder
    public Room(Long id, String name, Long teamId){
        this.id = id;
        this.name = name;
        this.teamId = teamId;
    }
}
