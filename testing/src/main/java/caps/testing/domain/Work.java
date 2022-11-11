package caps.testing.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "work")
@Data
@NoArgsConstructor
@Getter @Setter
public class Work {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @DateTimeFormat(pattern = "yyyy-MM-DD-HH-mm")
    @Column(name = "WORK_START_TIME", updatable = false)
    private LocalDateTime work_start_time;

    @DateTimeFormat(pattern = "yyyy-MM-DD-HH-mm")
    @Column(name = "WORK_END_TIME", updatable = false)
    private LocalDateTime work_end_time;

    @Builder
    public Work(Long id, Member member, LocalDateTime work_start_time, LocalDateTime work_end_time) {
        this.id = id;
        this.member = member;
        this.work_start_time = work_start_time;
        this.work_end_time = work_end_time;
    }
}
