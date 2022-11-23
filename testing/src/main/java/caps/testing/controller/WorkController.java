package caps.testing.controller;

import caps.testing.domain.Work;
import caps.testing.dto.work.WorkEndDto;
import caps.testing.dto.work.WorkStartDto;
import caps.testing.repository.WorkRepository;
import caps.testing.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkRepository workRepository;
    private final WorkService workService;

    @PostMapping("/api/work/start/{member_id}")
    public Long register_start_time(@RequestBody @Valid WorkStartDto workStartDto, @PathVariable @Valid Long member_id){
        return workService.save_startTime(workStartDto, member_id);
    }

    @PostMapping("/api/work/end/{member_id}")
    public String register_end_time(@RequestBody @Valid WorkEndDto workEndDto, @PathVariable @Valid Long member_id){
        workService.save_endTime(workEndDto, member_id);
        return "일 종료";
    }

    @GetMapping("/api/work/today")
    public String get_today_work(){
        workService.calculate_work_time();
        return "오늘 일한 시간 체크";
    }

    @GetMapping("/api/work/{member_id}")
    public Work get_total_work(@PathVariable @Valid Long member_id){
        Optional<Work> workAll = workRepository.findById(member_id);
        List<Work> myWorkList = new ArrayList<>();
        Work work = workAll.get();
//        Map<String, Timestamp> map = new HashMap<>();
//        map.put("일 시작 시간", work.getWork_start_time());
//        map.put("일 종료 시간", work.getWork_end_time());

        return work;
    }
}
