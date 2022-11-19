package caps.testing.domain.chat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "message")
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "message_sender")
    private String sender;

    @Column(name = "message_content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    @JsonBackReference
    private Room room;
}
