package caps.testing.controller;

import caps.testing.dto.work.WorkEndDto;
import caps.testing.dto.work.WorkStartDto;
import caps.testing.repository.WorkRepository;
import caps.testing.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkRepository workRepository;
    private final WorkService workService;

    @PostMapping("/api/work/start")
    public Long register_start_time(@RequestBody @Valid WorkStartDto workStartDto){
        return workService.save_startTime(workStartDto);
    }

    @PostMapping("api/work/end")
    public String register_end_time(@RequestBody @Valid WorkEndDto workEndDto){
        workService.save_endTime(workEndDto);
        return "일 종료";
    }

    @GetMapping("api/work/today")
    public String get_today_work(){
        workService.calculate_work_time();
        return "오늘 일한 시간 체크";
    }
}
