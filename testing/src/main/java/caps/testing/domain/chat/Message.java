package caps.testing.domain.chat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "chat_message")
@Getter @Setter
@NoArgsConstructor
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_MESSAGE_ID")
    private Long id;

    @Column(name = "CHAT_MESSAGE_CONTENT")
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "ROOM_ID")
//    @JsonBackReference

    @Builder
    public Message(Long id, String content){
        this.id = id;
        this.content = content;
    }
}
