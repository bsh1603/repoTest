package caps.testing.domain.chat;

import caps.testing.domain.Member;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "room")
@Getter @Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(name = "ROOM_NAME")
    private String name;

    @OneToMany
    @JsonManagedReference
    private List<Member> memberList = new ArrayList<>();

    @OneToMany
    @JsonManagedReference
    private List<Message> messageList = new ArrayList<>();
}
