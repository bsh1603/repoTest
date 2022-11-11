package caps.testing.dto.work;

import caps.testing.domain.Work;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkStartDto {

    private LocalDateTime work_start_time;

    public Work toStartWork(){
        return Work.builder()
                .work_start_time(work_start_time)
                .build();
    }
}
