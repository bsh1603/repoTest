package caps.testing.controller;

import caps.testing.domain.Work;
import caps.testing.dto.work.WorkEndDto;
import caps.testing.dto.work.WorkStartDto;
import caps.testing.repository.WorkRepository;
import caps.testing.service.TeamService;
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
    private final TeamService teamService;

    @PostMapping("/api/work/start/{member_id}")
    public Long register_start_time(@RequestBody @Valid WorkStartDto workStartDto, @PathVariable @Valid Long member_id){
        return workService.save_startTime(workStartDto, member_id);
    }

    @GetMapping("/api/work/start/location/{team_id}")
    public Map<String, Double> get_location(@PathVariable @Valid Long team_id){
        return teamService.findLocation(team_id);
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

        return work;
    }

    @GetMapping("/api/work/chart/{member_id}")
    public Map<String, Long> get_work_data(@PathVariable @Valid Long member_id){
        List<String> workDateForChart = workRepository.findWorkDateForChart(member_id);
        List<Long> workTimeForChart = workRepository.findWorkTimeForChart(member_id);
        Map<String, Long> map = new LinkedHashMap<>();

        for (int i = 0; i < workDateForChart.size(); i++) {
            map.put(workDateForChart.get(i), workTimeForChart.get(i));
        }

        return map;
    }
}
