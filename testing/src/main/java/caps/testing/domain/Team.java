package caps.testing.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "team")
@NoArgsConstructor
@Getter @Setter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "TEAM_NAME")
    private String name;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private List<Member> memberList = new ArrayList<>();

    @Builder
    public Team(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
